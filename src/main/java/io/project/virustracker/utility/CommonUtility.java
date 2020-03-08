package io.project.virustracker.utility;

import io.project.virustracker.utility.common.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonUtility {

    public static class CoronaUtil {
        public static String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/[date].csv?raw=True";
        public static String CSV_HEADER_PROVINCE_AND_STATE = "Province/State";
        public static String CSV_HEADER_COUNTRY_AND_REGION = "Country/Region";
        public static String CSV_HEADER_LAST_UPDATE = "Last Update";
        public static String CSV_HEADER_CONFIRMED = "Confirmed";
        public static String CSV_HEADER_DEATHS = "Deaths";
        public static String CSV_HEADER_RECOVERED = "Recovered";
    }
}
