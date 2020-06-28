package com.payday.loanservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Utils {
    public long getDaysCountFromNow(Date startDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String now = format.format(new Date());
        String startDateAsString =  format.format(startDate);
        Date dateNow = format.parse(now);
        startDate = format.parse(startDateAsString);
        LocalDate nowDate = LocalDate.parse(now);
        LocalDate startDateAsLocalDate = LocalDate.parse(startDateAsString);
        long noOfDaysBetween = ChronoUnit.DAYS.between(startDateAsLocalDate,nowDate); //calculating number of days in between
        return noOfDaysBetween;
    }
}
