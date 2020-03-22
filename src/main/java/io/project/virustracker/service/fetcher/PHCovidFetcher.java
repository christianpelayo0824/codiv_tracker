package io.project.virustracker.service.fetcher;

import io.project.virustracker.entity.PHCasesByFacility;
import io.project.virustracker.entity.PHCovidCase;
import io.project.virustracker.repository.PHCasesByFacilityRepository;
import io.project.virustracker.repository.PHCovidCaseRepository;
import io.project.virustracker.utility.common.DateUtil;
import io.project.virustracker.utility.common.FileReaderUtil;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@NoArgsConstructor
public class PHCovidFetcher {

    private static Logger logger = Logger.getLogger(PHCovidFetcher.class.getName());

    private static final String PH_COVID_URL_FILE_PATH = "static/folder/ph-covid-url.json";
    private static final String PH_COVID_CASE_SUMMARY_JSON_KEY = "case_summary";
    private static final String PH_COVID_CASE_BY_FACILITY_JSON_KEY = "case_by_facility";
    private static String PH_COVID_URL_CASE_SUMMARY;
    private static String PH_COVID_URL_CASE_BY_FACILITY;

    FileReaderUtil fileReaderUtil;
    PHCasesByFacilityRepository phCasesByFacilityRepository;
    PHCovidCaseRepository phCovidCaseRepository;

    @Autowired
    public PHCovidFetcher(FileReaderUtil fileReaderUtil,
                          PHCasesByFacilityRepository phCasesByFacilityRepository,
                          PHCovidCaseRepository phCovidCaseRepository) {
        this.fileReaderUtil = fileReaderUtil;
        this.phCasesByFacilityRepository = phCasesByFacilityRepository;
        this.phCovidCaseRepository = phCovidCaseRepository;
    }

    @PostConstruct
    public void init() {
        PH_COVID_URL_CASE_SUMMARY = (String) fileReaderUtil.readJSONValueByKey(PH_COVID_URL_FILE_PATH, PH_COVID_CASE_SUMMARY_JSON_KEY);
        PH_COVID_URL_CASE_BY_FACILITY = (String) fileReaderUtil.readJSONValueByKey(PH_COVID_URL_FILE_PATH, PH_COVID_CASE_BY_FACILITY_JSON_KEY);
    }


    /**
     * Service function to retrieve COVID-19 Cases based on <br>
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
     */
    public void retrievePHCovidCaseByFacility() {
        System.out.println("Start retrieving List!");
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PH_COVID_URL_CASE_BY_FACILITY)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray caseMap = jsonObject.getJSONArray("features");

            List<PHCasesByFacility> casesByFacilityList = new ArrayList<>();

            for (int i = 0; i < caseMap.length(); i++) {
                JSONObject caseObject = caseMap.getJSONObject(i).getJSONObject("attributes");
                PHCasesByFacility casesByFacility = new PHCasesByFacility();
                if (caseObject == null) continue;
                casesByFacility.setFid(Long.parseLong(caseObject.get("FID").toString()));
                casesByFacility.setAge(Integer.parseInt(caseObject.get("edad").toString()));
                casesByFacility.setGender(caseObject.get("kasarian").toString());
                casesByFacility.setNationality(caseObject.get("nationalit").toString());
                casesByFacility.setResidence(caseObject.get("residence").toString());
                casesByFacility.setTravelHistory(caseObject.get("travel_hx").toString());
                casesByFacility.setSymptoms(caseObject.get("symptoms").toString());
                casesByFacility.setConfirmed(caseObject.get("confirmed").toString());
                casesByFacility.setFacility(caseObject.get("facility").toString());
                casesByFacility.setStatus(caseObject.get("status").toString());
                casesByFacility.setDate(caseObject.get("petsa").toString());
                casesByFacilityList.add(casesByFacility);
            }
            phCasesByFacilityRepository.saveAll(casesByFacilityList);
            System.out.println("Save List!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Service function to retrieve data consist of:
     * 1. Day - The data being published from the source
     * 2. Confirmed - Confirmed cased of COVID-19
     * 3. PUIs - PUIs cased of COVID-19
     * 4. PUMs - PUMs cased of COVID-19
     * 5. Recovered - Recovered cased of COVID-19
     * 6. Deaths - Deaths cased of COVID-19
     * 7. Tests - Tests cased of COVID-19
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    public void retrievePHCovidCase() {
        logger.info("Start retrieving for PHCovidCase....");
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(PH_COVID_URL_CASE_SUMMARY)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray caseMap = jsonObject.getJSONArray("features");
            JSONObject caseObject = caseMap.getJSONObject(0).getJSONObject("attributes");

            PHCovidCase phCovidCase = new PHCovidCase();
            phCovidCase.setDay(DateUtil.epochTimeToDate(Long.parseLong(caseObject.get("day").toString())));
            phCovidCase.setConfirmed(Long.parseLong(caseObject.get("confirmed").toString()));
            phCovidCase.setPuis(Long.parseLong(caseObject.get("PUIs").toString()));
            phCovidCase.setPums(Long.parseLong(caseObject.get("PUMs").toString()));
            phCovidCase.setRecovered(Long.parseLong(caseObject.get("recovered").toString()));
            phCovidCase.setDeaths(Long.parseLong(caseObject.get("deaths").toString()));
            phCovidCase.setTests(Long.parseLong(caseObject.get("tests").toString()));
            phCovidCaseRepository.save(phCovidCase);
            logger.info("Done retrieving for PHCovidCase.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
