package com.example.android.to_do_list_2_0.room;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {

    @TypeConverter
    public static Date getTimeDate(String value) {

        if (value != null) {
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(value);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @TypeConverter
    public static String getTimeString(Date value){
        if(value != null){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            return dateFormat.format(value);
        }
        return null;
    }
}
