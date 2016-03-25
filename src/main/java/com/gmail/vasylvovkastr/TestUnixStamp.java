package com.gmail.vasylvovkastr;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by vasyl on 24.03.16.
 */
public class TestUnixStamp {
    public static void main(String... args) {
        Calendar calendar = new GregorianCalendar();
        ZoneId tz = ZoneId.of("Etc/UTC");
        calendar.setTimeZone(TimeZone.getTimeZone(tz));
        ZonedDateTime zdt = ZonedDateTime.now();
        zdt.plusDays(1);
        zdt.withZoneSameInstant(tz);
        System.out.println(zdt);
        int year = zdt.getYear();
        int month = zdt.getMonthValue(); // Jan = 0, dec = 11
        int day = zdt.getDayOfMonth();
        long unxdt = new GregorianCalendar(year,month,day).getTimeInMillis();
        long startTime = unxdt + 18*3600*1000;
        long stopTime = unxdt + 33*3600*1000;

        System.out.println(new Date(startTime) + "\t" + new Date(stopTime));
        System.out.println(year+"\t"+month+"\t"+day);
        calendar.set(year, month, day);
        System.out.println(calendar.getTimeInMillis());

        Instant timestamp = Instant.ofEpochMilli(startTime);
        timestamp.atZone(tz);
        System.out.println("Current Timestamp = "+timestamp.getEpochSecond());
        System.out.println(Instant.ofEpochSecond(timestamp.getEpochSecond()));
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time = "+specificTime);
        System.out.println(tz.getId());
    }
}
