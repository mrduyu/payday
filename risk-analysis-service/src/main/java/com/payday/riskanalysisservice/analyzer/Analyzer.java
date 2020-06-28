package com.payday.riskanalysisservice.analyzer;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.payday.riskanalysisservice.models.IndividualLoanRequest;
import com.payday.riskanalysisservice.models.LoanRequest;
import com.payday.riskanalysisservice.proxies.IncomeInformationProxy;
import com.payday.riskanalysisservice.proxies.KafkaProxy;
import com.payday.riskanalysisservice.proxies.KycInformationProxy;
import com.payday.riskanalysisservice.proxies.LoanProxy;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.OperationsException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Analyzer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Analyzer.class);

    public void Analyze() throws ParseException {
        try {
            HashMap<String, List<IndividualLoanRequest>> unAnalyzedIndividualLoanRequests = new LoanProxy().GetUnAnalyzedLoans();
            double defaultInterestRate = 0.1;
            if (unAnalyzedIndividualLoanRequests != null && unAnalyzedIndividualLoanRequests.size() > 0) {
                for (Map.Entry<String, List<IndividualLoanRequest>> entry : unAnalyzedIndividualLoanRequests.entrySet()) {
                    String nationalId = entry.getKey();
                    List<IndividualLoanRequest> loanRequests = entry.getValue();
                    double sumOfLoans = 0.0;
                    double totalPreviouslyGrantedLoan = new LoanProxy().
                            GetPreviousGrantedLoanTotalAmountsByNationalId(nationalId);
                    String kycInformation = new KycInformationProxy().getKycInformation(nationalId);
                    double monthlyIncome = new IncomeInformationProxy().getCustomerMonthlyIncome(nationalId);
                    Date startDate = getStartDateLastEmployer(kycInformation);
                    String emailAddress = getCustomerEmailAddress(kycInformation);

                    long daysCount = getDaysDifferenceFromNow(startDate);

                    for (IndividualLoanRequest individualLoanRequest : loanRequests) {
                        double requestedLoanAmount = individualLoanRequest.getRequestedAmount();
                        sumOfLoans = requestedLoanAmount + totalPreviouslyGrantedLoan;
                        if (requestedLoanAmount - (sumOfLoans * defaultInterestRate) >= 0 && daysCount > 90) {
                            LOGGER.info("INFO-LOG: Loan request approved. Sending message to Kafka. Email address:", emailAddress);
                            new KafkaProxy().sendMessageToKafka("Hi, your loan request has been approved.", emailAddress);
                            new LoanProxy().updateLoanRequestAfterAnalysis(individualLoanRequest.getLoanId(), true);
                        } else {
                            LOGGER.info("INFO-LOG: Loan request rejected. Sending message to Kafka. Email address:", emailAddress);
                            new KafkaProxy().sendMessageToKafka("Hi, your loan request has been rejected.", emailAddress);
                            new LoanProxy().updateLoanRequestAfterAnalysis(individualLoanRequest.getLoanId(), false);
                        }
                    }
                }
            } else {
                LOGGER.info("INFO-LOG: No data found to apply risk analysis.");
            }
        } catch (OperationsException operationsException) {
            LOGGER.info("INFO-ERROR: Loan request result update operation failed! Exception:" + operationsException.toString());
        } catch (Exception ex) {
            LOGGER.info("INFO-ERROR: Exception occured when applying risk analysis. Exception:" + ex.toString());
        }
    }


    private Date getStartDateLastEmployer(String kycInformation) throws ParseException {
        JSONObject kycInformationObject = new JSONObject(kycInformation);
        String startDateAsStr = kycInformationObject.getString("startDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(startDateAsStr);
        return startDate;
    }

    private String getCustomerEmailAddress(String kycInformation) throws ParseException {
        JSONObject kycInformationObject = new JSONObject(kycInformation);
        String emailAddress = kycInformationObject.getString("emailAddress");
        return emailAddress;
    }

    private long getDaysDifferenceFromNow(Date startDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String now = format.format(new Date());
            String startDateAsString = format.format(startDate);
            Date dateNow = format.parse(now);
            startDate = format.parse(startDateAsString);
            LocalDate nowDate = LocalDate.parse(now);
            LocalDate startDateAsLocalDate = LocalDate.parse(startDateAsString);
            long noOfDaysBetween = ChronoUnit.DAYS.between(startDateAsLocalDate, nowDate); //calculating number of days in between
            return noOfDaysBetween;
        } catch (Exception ex) {
            return 0;
        }
    }
}
