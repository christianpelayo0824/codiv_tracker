package io.project.virustracker.component;

import io.project.virustracker.entity.CODIVCase;
import io.project.virustracker.utility.CommonUtility;
import io.project.virustracker.utility.common.DateUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * CoronaVirusDataService will do the functionality <br>
 * for providing all the related functions of <br>
 * Corona Virus Cases.
 * <p>
 * 1. Fetching data from source.
 * https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_daily_reports/
 *
 * @author Christian Pelayo. 02 Feb 2020
 */
@Component
public class CODIVCaseComponent {


    /**
     * Provided with the start date and end date <br>
     * interval, the function return the map of url. <br>
     * with the key of date.
     *
     * @param startDate - Specify when the data start. For example 0, <br>
     *                  as the current day.
     * @param endDate   - Specify when the data end. For example -4, <br>
     *                  as the last day.
     * @return - Map<String, List<String>>
     */
    public static Map<String, String> retrieveURLMap(int startDate, int endDate) {
        Map<String, String> urlMap = new HashMap<>();
        for (String date : DateUtil.dateInterValParser(startDate, endDate)) {
            urlMap.put(date, CommonUtility.CoronaUtil.CORONA_VIRUS_URL.replace("[date]", date));
        }
        return urlMap;
    }


    /**
     * This function handle the retrieving the date. <br>
     * This is applicable for retrieving Historical and <br>
     * Realtime Data from the source.
     *
     * @param startDate First interval date (0) - For the <br>
     *                  current date.
     * @param endDate   Last interval date (-4) - For the <br>
     *                  four previous date.
     * @return List<CoronaCase>
     */
    public List<CODIVCase> fetchCoronaVirusCase(int startDate, int endDate) {
        try {
            List<CODIVCase> CODIVCaseList = new ArrayList<>();
            Map<String, String> urlMap = CODIVCaseComponent.retrieveURLMap(startDate, endDate);
            for (Map.Entry<String, String> urlList : urlMap.entrySet()) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlList.getValue())).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // If the status code is success iterate the Return CSV data
                // to the CoronaCase Entity.
                if (response.statusCode() == 200) {
                    StringReader reader = new StringReader(response.body());
                    Iterable<CSVRecord> coronaCases = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
                    for (CSVRecord caseInstance : coronaCases) {
                        CODIVCase codivCase = new CODIVCase();
                        codivCase.setProvince(caseInstance.get(CommonUtility.CoronaUtil
                                .CSV_HEADER_PROVINCE_AND_STATE));
                        codivCase.setCountry(caseInstance.get(CommonUtility.CoronaUtil
                                .CSV_HEADER_COUNTRY_AND_REGION));
                        codivCase.setLastUpdate(DateUtil.parseDate(caseInstance.get(CommonUtility
                                .CoronaUtil.CSV_HEADER_LAST_UPDATE)));
                        codivCase.setConfirmed(Long.parseLong(caseInstance.get(CommonUtility.CoronaUtil
                                .CSV_HEADER_CONFIRMED)));
                        codivCase.setDeaths(Long.parseLong(caseInstance.get(CommonUtility.CoronaUtil
                                .CSV_HEADER_DEATHS)));
                        codivCase.setRecovered(Long.parseLong(caseInstance.get(CommonUtility.CoronaUtil
                                .CSV_HEADER_RECOVERED)));
                        codivCase.setPublishedDate(DateUtil.formatStringDate(urlList.getKey(), DateUtil.FORMAT_YYYY_MM_DD));
                        CODIVCaseList.add(codivCase);
                    }
                }
            }
            return CODIVCaseList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
