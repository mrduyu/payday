package com.payday.riskanalysisservice.task;

import com.payday.riskanalysisservice.analyzer.Analyzer;
import com.payday.riskanalysisservice.proxies.KafkaProxy;
import com.payday.riskanalysisservice.proxies.LoanProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
public class AnalyzeRiskScheduledTask {
    private static final Logger log = LoggerFactory.getLogger(AnalyzeRiskScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 300000)
    public void ProcessDBItems() throws ParseException, InterruptedException {
        log.info("INFO-LOG: Analyze batch started at: {}", dateFormat.format(new Date()));
        AnalyzeRisk();
    }

    @Async
    public CompletableFuture<String> AnalyzeRisk() throws InterruptedException, ParseException {
        new Analyzer().Analyze();
        log.info("INFO-LOG: Analyze batch completed at: {}", dateFormat.format(new Date()));
        return CompletableFuture.completedFuture("Done");
    }
}