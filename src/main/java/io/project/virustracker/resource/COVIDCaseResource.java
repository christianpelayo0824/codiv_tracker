package io.project.virustracker.resource;


import io.project.virustracker.entity.COVIDCase;
import io.project.virustracker.service.COVIDCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This will be the class of all rest-end point that is <br>
 * that expose the functionality about COVID Cases.
 *
 * @author christian
 */
@RestController
@RequestMapping(path = "/corona")
public class COVIDCaseResource {

    COVIDCaseService COVIDCaseService;

    @Autowired
    public void setCOVIDCaseService(COVIDCaseService COVIDCaseService) {
        this.COVIDCaseService = COVIDCaseService;
    }

    /**
     * This rest end point handle for fetching <br>
     * data.
     *
     * @param startDate - (0) integer, Set as current date.
     * @param endDate   - (-1) integer, Set as a previous date.
     * @return Response<?>
     */
    @GetMapping(path = "/fetchHistoricalData/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchHistoricalData(@PathVariable("startDate") int startDate,
                                                 @PathVariable("endDate") int endDate) {
        return new ResponseEntity<>(COVIDCaseService.writeCoronaCaseList(startDate, endDate), HttpStatus.OK);
    }

    /**
     * This rest end point handle for retrieving <br>
     * latest data from the database. This will <br>
     * return a list of COVIDCase Object in a json <br>
     * form.
     *
     * @return ResponseEntity<Long>
     */
    @GetMapping(path = "/coronaCaseLatest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<COVIDCase>> getCoronaCaseList() {
        return new ResponseEntity<>(COVIDCaseService.findCaseByLatestDate(), HttpStatus.OK);
    }

    /**
     * This rest end point will return the total <br>
     * cases of COVID Virus.
     * 1. CONFIRMED
     * 2. DEATH
     * 3. RECOVERED
     *
     * @return
     */
    @GetMapping(path = "/totalCase", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> totalCase() {
        return new ResponseEntity<>(COVIDCaseService.getTotalCased(), HttpStatus.OK);
    }
}
