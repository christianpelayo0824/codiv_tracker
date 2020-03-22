package io.project.virustracker.resource;

import io.project.virustracker.entity.PHCasesByFacility;
import io.project.virustracker.entity.PHCovidCase;
import io.project.virustracker.service.PHCovidCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ph-covid-case")
public class PHCovidCaseResource {

    PHCovidCaseService covidCaseService;

    @Autowired
    public PHCovidCaseResource(PHCovidCaseService covidCaseService) {
        this.covidCaseService = covidCaseService;
    }

    /**
     * REST End point for retrieval of Philippines COVID-19 <br>
     * cases based on the facility.
     *
     * @return ResponseEntity<List < PHCasesByFacility>>
     */
    @GetMapping(value = "/retrieveCasesByFacility", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PHCasesByFacility>> retrieveCasesByFacility() {
        return new ResponseEntity<>(covidCaseService.retrieveCovidCaseByFacility(), HttpStatus.OK);
    }

    /**
     * REST End point for retrieval of latest summary for Philippines <br>
     * COVID-19 Cases.
     *
     * @return ResponseEntity<PHCovidCase>
     */
    @GetMapping(value = "/retrieveCasesSummary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PHCovidCase> retrieveCasesSummary() {
        return new ResponseEntity<>(covidCaseService.retrievePHCovidCaseSummary(), HttpStatus.OK);
    }
}
