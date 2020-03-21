package io.project.virustracker.service.fetcher;

import io.project.virustracker.entity.PHCodivCase;
import io.project.virustracker.utility.common.DateUtil;
import io.project.virustracker.utility.common.FileReaderUtil;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class PHCodivFetcher {

    private static final String PH_CODIV_URL_FILE_PATH = "static/folder/ph-codiv-url.json";
    private static String PH_CODIV_URL_CASE_SUMMARY;
    private static String PH_CODUV_URL_CASE_BY_FACILITY;

    FileReaderUtil fileReaderUtil;

    @Autowired
    public PHCodivFetcher(FileReaderUtil fileReaderUtil) {
        this.fileReaderUtil = fileReaderUtil;
    }

    @PostConstruct
    public void init() {
        PH_CODIV_URL_CASE_SUMMARY = (String) fileReaderUtil.readJSONValueByKey(PH_CODIV_URL_FILE_PATH, "case_summary");
        PH_CODUV_URL_CASE_BY_FACILITY = (String) fileReaderUtil.readJSONValueByKey(PH_CODIV_URL_FILE_PATH, "case_by_facility");
    }

    /**
     * Service function to retrieve data consist of:
     * 1. Day - The data being published from the source
     * 2. Confirmed - Confirmed cased of CODIV-19
     * 3. PUIs - PUIs cased of CODIV-19
     * 4. PUMs - PUMs cased of CODIV-19
     * 5. Recovered - Recovered cased of CODIV-19
     * 6. Deaths - Deaths cased of CODIV-19
     * 7. Tests - Tests cased of CODIV-19
     *
     * @return status
     */
    public boolean retrievePHCodivCase() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PH_CODIV_URL_CASE_SUMMARY)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray caseMap = jsonObject.getJSONArray("features");
            JSONObject caseObject = caseMap.getJSONObject(0).getJSONObject("attributes");

            PHCodivCase phCodivCase = new PHCodivCase();
            phCodivCase.setDay(DateUtil.epochTimeToDate(Long.parseLong(caseObject.get("day").toString())));
            phCodivCase.setConfirmed(Long.parseLong(caseObject.get("confirmed").toString()));
            phCodivCase.setPuis(Long.parseLong(caseObject.get("PUIs").toString()));
            phCodivCase.setPums(Long.parseLong(caseObject.get("PUMs").toString()));
            phCodivCase.setRecovered(Long.parseLong(caseObject.get("recovered").toString()));
            phCodivCase.setDeaths(Long.parseLong(caseObject.get("deaths").toString()));
            phCodivCase.setTests(Long.parseLong(caseObject.get("tests").toString()));
            System.out.println(phCodivCase.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Service function to retrieve CODIV-19 Cases based on <br>
     * the facility. Data consist of:
     * <p>
     * 1. fid
     * 2. age
     * 3. gender
     * 4. nationality
     * 5. residence
     * 6. travelHistory
     * 7. symptoms
     * 8. confirmed
     * 9. facility
     * 10. status
     * 11. date
     *
     * @return boolean
     */
    public boolean retrievePHCodivCaseByFacility() {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PH_CODUV_URL_CASE_BY_FACILITY)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray caseMap = jsonObject.getJSONArray("features");

            int counter = 0;
            for (int i = 0; caseMap.length() >= 0; i++) {
                JSONObject caseObject = caseMap.getJSONObject(i).getJSONObject("attributes");
                System.out.println(counter + " | " + caseObject);
                counter++;
            }
        } catch (Exception ignore) {

        }

        return true;
    }
}
