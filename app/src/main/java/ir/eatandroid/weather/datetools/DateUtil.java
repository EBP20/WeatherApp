package ir.eatandroid.weather.datetools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * Created by elham on 12/13/2017.
 */

public class DateUtil {

    public static int getDayOfMonth(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return 1;
        }
         try {
             Date date = null;
             SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
             simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
             try {
                 date = simpleDateFormat.parse(dateTime);
             }
             catch (ParseException e) {
                 e.printStackTrace();
             }
             Calendar greCal = new GregorianCalendar();
             greCal.setTime(date);

             if(isPersian){
                 JalaliCalendar jalCal = new JalaliCalendar();
                // jalCal.setMiladiDate(greCal.get(Calendar.YEAR), greCal.get(Calendar.MONTH), greCal.get(Calendar.DAY_OF_MONTH));
                 jalCal.getJalaliDate(greCal.get(Calendar.YEAR), greCal.get(Calendar.MONTH), greCal.get(Calendar.DAY_OF_MONTH));
                 return  jalCal.getJday();
             }
             return greCal.get(Calendar.DAY_OF_MONTH);
         }
         catch (NullPointerException e) {
             e.printStackTrace();
             return 1;
         }
    }

    public static int getDayOfWeek(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return GregorianMonth.Jan.getValue();
        }
        try {
            Date date = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                date = simpleDateFormat.parse(dateTime);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar greCal = new GregorianCalendar();
            greCal.setTime(date);
            return greCal.get(Calendar.DAY_OF_WEEK);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return GregorianDayOfWeek.Sun.getValue();
        }
    }

    public static int getMonth(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return GregorianMonth.Jan.getValue();
        }
        try {
            Date date = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                date = simpleDateFormat.parse(dateTime);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar greCal = new GregorianCalendar();
            greCal.setTime(date);

            if(isPersian){
                JalaliCalendar jalCal = new JalaliCalendar();
              //  jalCal.setMiladiDate(greCal.get(Calendar.YEAR), greCal.get(Calendar.MONTH), greCal.get(Calendar.DAY_OF_MONTH));
                jalCal.getJalaliDate(greCal.get(Calendar.YEAR), greCal.get(Calendar.MONTH), greCal.get(Calendar.DAY_OF_MONTH));
                return  jalCal.getJmonth() ;
            }
            return greCal.get(Calendar.MONTH);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return GregorianMonth.Jan.getValue();
        }
    }

    public static int getYear(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return new GregorianCalendar().getTime().getYear();
        }
        try {
            Date date = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                date = simpleDateFormat.parse(dateTime);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar greCal = new GregorianCalendar();
            greCal.setTime(date);

            if(isPersian){
                JalaliCalendar jalCal = new JalaliCalendar();
                jalCal.getJalaliDate(greCal.get(Calendar.YEAR), greCal.get(Calendar.MONTH), greCal.get(Calendar.DAY_OF_MONTH));
                return  jalCal.getJyear();
            }
            return greCal.get(Calendar.YEAR);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return new GregorianCalendar().getTime().getYear();
        }
    }

    public static String getStringMonth(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return GregorianMonth.Jan.toString();
        }
        try {
           if(isPersian){
               switch(PersianMonth.forValue(getMonth(dateTime,format,isPersian) - 1)){
                   case Farvardin:
                       return "فروردین";
                   case Ordibehesht:
                       return "اردیبهشت";
                   case Khordad:
                       return "خرداد";
                   case Tir:
                       return "تیر";
                   case Mordad:
                       return "مرداد";
                   case Shahrivar:
                       return "شهریور";
                   case Mehr:
                       return "مهر";
                   case Aban:
                       return "آبان";
                   case Azar:
                       return "آذر";
                   case Dey:
                       return "دی";
                   case Bahman:
                       return "بهمن";
                   case Esfand:
                       return "اسفند";
                   default:
                       return "فروردین";
               }
           }

            switch(GregorianMonth.forValue(getMonth(dateTime,format,isPersian))){
                case Apr:
                    return GregorianMonth.Apr.toString();
                case Aug:
                    return GregorianMonth.Aug.toString();
                case Dec:
                    return GregorianMonth.Dec.toString();
                case Feb:
                    return GregorianMonth.Feb.toString();
                case Jan:
                    return GregorianMonth.Jan.toString();
                case Jul:
                    return GregorianMonth.Jul.toString();
                case Jun:
                    return GregorianMonth.Jun.toString();
                case May:
                    return GregorianMonth.May.toString();
                case Mar:
                    return GregorianMonth.Mar.toString();
                case Nov:
                    return GregorianMonth.Nov.toString();
                case Oct:
                    return GregorianMonth.Oct.toString();
                case Sep:
                    return GregorianMonth.Sep.toString();
                default:
                    return GregorianMonth.Jan.toString();
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return GregorianMonth.Jan.toString();
        }
    }

    public static String getStringDayOfWeek(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return GregorianDayOfWeek.Sun.toString();
        }
        try {
            if(isPersian){
                switch(GregorianDayOfWeek.forValue(getDayOfWeek(dateTime,format,isPersian) - 1)) {
                    case Fri:
                        return "جمعه";
                    case Sat:
                        return "شنبه";
                    case Sun:
                        return "یکشنبه";
                    case Mon:
                        return "دوشنبه";
                    case Tue:
                        return "سه شنبه";
                    case Wed:
                        return "چهارشنبه";
                    case Thu:
                        return "پنج شنبه";
                    default:
                        return "یکشنبه";
                }
            }
            switch(GregorianDayOfWeek.forValue(getDayOfWeek(dateTime,format,isPersian) - 1)) {
                case Fri:
                    return GregorianDayOfWeek.Fri.toString();
                case Sat:
                    return GregorianDayOfWeek.Sat.toString();
                case Sun:
                    return GregorianDayOfWeek.Sun.toString();
                case Mon:
                    return GregorianDayOfWeek.Mon.toString();
                case Tue:
                    return GregorianDayOfWeek.Tue.toString();
                case Wed:
                    return GregorianDayOfWeek.Wed.toString();
                case Thu:
                    return GregorianDayOfWeek.Thu.toString();
                default:
                    return GregorianDayOfWeek.Sun.toString();
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return GregorianDayOfWeek.Sun.toString();
        }
    }

    public static String getLongStringDate(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return "";
        }
        try {
            return  getStringDayOfWeek(dateTime,format,isPersian) + "  " +
                    getDayOfMonth(dateTime,format,isPersian) + "  " +
                    getStringMonth(dateTime,format,isPersian) + "  " +
                    getYear(dateTime,format,isPersian);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLongStringDateFromMilis(String milis,String format,boolean isPersian){

        if(ValidationTools.isEmptyOrNull(milis)){
            return "";
        }
        try {
            String dateTime = getDateTime(milis,format);
            return  getStringDayOfWeek(dateTime,format,isPersian) + "  " +
                    getDayOfMonth(dateTime,format,isPersian) + "  " +
                    getStringMonth(dateTime,format,isPersian) + "  " +
                    getYear(dateTime,format,isPersian);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getShortStringDate(String dateTime,String format,boolean isPersian){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return "";
        }
        try {
            return  getStringDayOfWeek(dateTime,format,isPersian) + "  " +
                    getDayOfMonth(dateTime,format,isPersian) + "  " +
                    getStringMonth(dateTime,format,isPersian);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getShortStringDateFromMilis(String milis,String format,boolean isPersian){

        if(ValidationTools.isEmptyOrNull(milis)){
            return "";
        }
        try {
            String dateTime = getDateTime(milis,format);
            return  getStringDayOfWeek(dateTime,format,isPersian) + "  " +
                    getDayOfMonth(dateTime,format,isPersian) + "  " +
                    getStringMonth(dateTime,format,isPersian);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDateTime(String milisecond , String format){
        if(ValidationTools.isEmptyOrNull(milisecond)){
            return "";
        }
        try {
            Date date = new Date(getMiliSecondFromJSONDate(milisecond));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat.format(date);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getMiliSecondGregorianDateTime(String dateTime,String format){
        if(ValidationTools.isEmptyOrNull(dateTime)){
            return 0;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = simpleDateFormat.parse(dateTime);
            return date.getTime();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
        catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getMiliSecondPersianDateTime(int year,int month,int day){
        JalaliCalendar jalCal = new JalaliCalendar();
        jalCal.getGregorianDate(year,month + 1,day);
        return  getMiliSecondGregorianDateTime(jalCal.getMyear() + "-" + (jalCal.getMmonth() + 1) + "-" + jalCal.getMday(),"yyyy-MM-dd");
    }

    public static String getStringJSONDateTime(String milisecond){
        return "/Date(" + (milisecond) + ")/";
    }

    public static long getMiliSecondFromJSONDate(String jsonDate){
        long time = 0 ;
        long timeEx = 0 ;
        jsonDate = jsonDate.replaceAll("^/Date\\(", "");

        if (jsonDate.contains("+")) {
            time = Long.parseLong(jsonDate.substring(0, jsonDate.indexOf('+')));
            String returnValue = jsonDate.substring(jsonDate.indexOf("+") + 1);
            String hour = String.valueOf(returnValue.charAt(0)) + String.valueOf(returnValue.charAt(1));
            String min = String.valueOf(returnValue.charAt(2)) + String.valueOf(returnValue.charAt(3));
            timeEx = (Integer.parseInt(hour) * 60 * 60 * 1000) + (Integer.parseInt(min) * 60 * 1000);
        } else if (jsonDate.contains("-") && !String.valueOf(jsonDate.charAt(0)).equals("-")) {
            time = Long.parseLong(jsonDate.substring(0, jsonDate.indexOf('-')));
            String returnValue = jsonDate.substring(jsonDate.indexOf("-") + 1);
            String hour = String.valueOf(returnValue.charAt(0)) + String.valueOf(returnValue.charAt(1));
            String min = String.valueOf(returnValue.charAt(2)) + String.valueOf(returnValue.charAt(3));
            timeEx = (Integer.parseInt(hour) * 60 * 60 * 1000) + (Integer.parseInt(min) * 60 * 1000);
        } else if(jsonDate.contains(")")) {
            jsonDate = jsonDate.substring(0,jsonDate.length()-1);
            jsonDate = jsonDate.substring(0,jsonDate.length()-1);
            time = Long.parseLong(jsonDate);
            timeEx = 0;
        }else{
            time = Long.parseLong(jsonDate);
            timeEx = 0;
        }
        return  time + timeEx;
    }

    public static TimeComponents getTimeDifference(String firstDate,String secondDate){
        if(ValidationTools.isEmptyOrNull(firstDate) || ValidationTools.isEmptyOrNull(secondDate)){
            return new TimeComponents();
        }
        try {
            Date fDate = new Date(getMiliSecondFromJSONDate(firstDate));
            Date sDate = new Date(getMiliSecondFromJSONDate(secondDate));
            long different = sDate.getTime() - fDate.getTime();
            TimeComponents timeComponents = new TimeComponents();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            timeComponents.setDay(different / daysInMilli);
            different = different % daysInMilli;

            timeComponents.setHour(different / hoursInMilli);
            different = different % hoursInMilli;

            timeComponents.setMinute(different / minutesInMilli);
            different = different % minutesInMilli;

            timeComponents.setSecond(different / secondsInMilli);
            return timeComponents;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
