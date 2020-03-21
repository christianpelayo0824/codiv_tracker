package io.project.virustracker.utility.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    // Date Format
    public static final String FORMAT_TIME_FORMAT_WITH_SEPARATOR_TEMPLATE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_TIME_FORMAT_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_MM_DD_YYYY = "MM-dd-yyyy";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final SimpleDateFormat DATE_FORMAT_MM_DD_YYYY = new SimpleDateFormat(FORMAT_MM_DD_YYYY);
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(FORMAT_TIME_FORMAT_TEMPLATE);
    public static final SimpleDateFormat DATE_TIME_FORMAT_WITH_SEPARATOR =
            new SimpleDateFormat(FORMAT_TIME_FORMAT_WITH_SEPARATOR_TEMPLATE);

    private static final TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone("UTC");


    /**
     * This function return the current date.
     *
     * @return String
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        df.setTimeZone(TIME_ZONE_UTC);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * This function handle for formatting a String of date to <br>
     * to a specific format that is required.
     * <p>
     * TODO Optimize this function to handle any format
     *
     * @param date   - String date that is needed to format
     * @param format - Format of the date that is needed.
     * @return - String
     */
    public static String formatStringDate(String date, String format) {
        try {
            Date dateInstance = DATE_FORMAT_MM_DD_YYYY.parse(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(dateInstance);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    // TODO add this function to formatStringDate function
    public static String parseDate(String stringDate) {
        try {
            Date date = DateUtil.DATE_TIME_FORMAT_WITH_SEPARATOR.parse(stringDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return DateUtil.DATE_TIME_FORMAT.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Provided by start date and end date interval, this function <br>
     * will return list of dates that is in between with those <br>
     * interval.
     *
     * @param startDate - (0) as the current date.
     * @param endDate   - (-3) as the previous date.
     * @return List<String>
     */
    public static List<String> dateInterValParser(int startDate, int endDate) {
        List<String> dateList = new ArrayList<>();
        for (int i = startDate; i >= endDate; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, i);
            dateList.add(DateUtil.DATE_FORMAT_MM_DD_YYYY.format(calendar.getTime()));
        }
        return dateList;
    }

    /**
     * Utility function to convert unix time to Date
     *
     * @param epochTime - long epoch time
     * @return String
     */
    public static String epochTimeToDate(long epochTime) {
        Date date = new Date(epochTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_TIME_FORMAT_TEMPLATE);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return simpleDateFormat.format(date);
    }
}
