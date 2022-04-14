package rsreu.workcours.nbaprediction.datetime;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateTimeWorker {
    public static Date stringToDate(String strDate) {
        return Date.valueOf(strDate);
    }
    public static Time stringToTime(String strTime) {
        return Time.valueOf(strTime);
    }
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }
    public static String timeToString(Time time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(time);
    }
    public static Date localDateToDate(LocalDate date) {
        return Date.valueOf(date);
    }

    private static java.util.Date stringToDateTime(String dateTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = df.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Date stringToDateTimeSQL(String dateTime) {
        java.util.Date date = stringToDateTime(dateTime);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
}
