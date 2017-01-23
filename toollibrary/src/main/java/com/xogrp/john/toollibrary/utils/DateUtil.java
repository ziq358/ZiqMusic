package com.xogrp.john.toollibrary.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by john on 23/01/2017.
 */

public class DateUtil {

    public static final DateFormat DATE_FORMAT_MMddyyyy = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    public static final DateFormat DATE_FORMAT_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static final DateFormat DATE_FORMAT_MM_dd_yyyy = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
    public static final DateFormat DATE_FORMAT_Mdyyyy = new SimpleDateFormat("M/d/yyyy", Locale.US);
    public static final DateFormat DATE_FORMAT_MMMyy = new SimpleDateFormat("MMM ''yy", Locale.US);
    public static final DateFormat DATE_FORMAT_yyyy_MM_dd_T_HHmmss_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    public static final DateFormat DATE_FORMAT_yyyy_MM_dd_T_HHmmss = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
    public static final DateFormat DATE_FORMAT_yyyy_MM_dd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    public static final DateFormat DATE_FORMAT_h_mm_a = new SimpleDateFormat("h:mm a", Locale.US);
    public static final DateFormat DATE_FORMAT_M_d_yyyy = new SimpleDateFormat("M.d.yyyy", Locale.US);
    public static final DateFormat DATE_FORMAT_MMM_yyyy = new SimpleDateFormat("MMM yyyy", Locale.US);
    public static final DateFormat DATE_FORMAT_E_MMM_yyyy = new SimpleDateFormat("E, MMM d", Locale.US);
    public static final DateFormat DATE_FORMAT_E_week = new SimpleDateFormat("E", Locale.US);

    private static Calendar clearTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static String getHoursFormat(Date date){
        int hours = date.getHours();
        String anteOrPost = hours >= 12 ? " pm":" am";
        hours = hours == 0 ? 24 : hours;
        return String.format("%s%s",String.valueOf(hours > 12 ? hours - 12 : hours), anteOrPost);
    }

    public static String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }
}
