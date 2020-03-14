package io.project.virustracker.utility;

public class CommonUtility {

    public static class CoronaUtil {
        public static String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/[date].csv?raw=True";
        public static int CSV_HEADER_PROVINCE_AND_STATE = 0;
        public static int CSV_HEADER_COUNTRY_AND_REGION = 1;
        public static int CSV_HEADER_LAST_UPDATE = 2;
        public static int CSV_HEADER_CONFIRMED = 3;
        public static int CSV_HEADER_DEATHS = 4;
        public static int CSV_HEADER_RECOVERED = 5;
    }
}
