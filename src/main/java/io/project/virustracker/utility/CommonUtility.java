package io.project.virustracker.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonUtility {

    private static final String FORMAT_TIME_FORMAT_WITH_SEPARATOR_TEMPLATE = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String FORMAT_TIME_FORMAT_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_MM_DD_YYYY = "MM-dd-yyyy";
    private static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final SimpleDateFormat DATE_FORMAT_MM_DD_YYYY = new SimpleDateFormat(FORMAT_MM_DD_YYYY);
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(FORMAT_TIME_FORMAT_TEMPLATE);
    public static final SimpleDateFormat DATE_TIME_FORMAT_WITH_SEPARATOR = new SimpleDateFormat(FORMAT_TIME_FORMAT_WITH_SEPARATOR_TEMPLATE);

    public static String parseDate(String stringDate) {
        try {
            Date date = DATE_TIME_FORMAT_WITH_SEPARATOR.parse(stringDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return DATE_TIME_FORMAT.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<String> dateInterValParser(int startDate, int endDate) {
        List<String> dateList = new ArrayList<>();
        for (int i = startDate; i >= endDate; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, i);
            dateList.add(DATE_FORMAT_MM_DD_YYYY.format(calendar.getTime()));
        }
        return dateList;
    }

    public static class CoronaUtil {
        public static String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/[date].csv?raw=True";
        public static String CSV_HEADER_PROVINCE_AND_STATE = "Province/State";
        public static String CSV_HEADER_COUNTRY_AND_REGION = "Country/Region";
        public static String CSV_HEADER_LAST_UPDATE = "Last Update";
        public static String CSV_HEADER_CONFIRMED = "Confirmed";
        public static String CSV_HEADER_DEATHS = "Deaths";
        public static String CSV_HEADER_RECOVERED = "Recovered";
        public static String CSV_HEADER_LATITUDE = "Latitude";
        public static String CSV_HEADER_LONGITUDE = "Longitude";
    }
}
