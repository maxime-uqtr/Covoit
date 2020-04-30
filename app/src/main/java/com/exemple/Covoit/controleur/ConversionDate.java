package com.exemple.Covoit.controleur;

import androidx.room.TypeConverter;

import java.util.Date;

public class ConversionDate {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String dateToString(Date date){
        int d = date.getDay();
        int m = date.getMonth();
        String day;
        String month;
        if(d < 10){
            day = "0" + d;
        }
        else
            day = String.valueOf(d);
        if(m < 10){
            month = "0" + m;
        }
        else
            month = String.valueOf(m);
        String year = String.valueOf(date.getYear());
        return day + "/" + month + "/" + year;
    }
}