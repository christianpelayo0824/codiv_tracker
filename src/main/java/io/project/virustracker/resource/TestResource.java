package io.project.virustracker.resource;

import io.project.virustracker.service.fetcher.PHCovidFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestResource {

    @Autowired
    PHCovidFetcher covidFetcher;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test() {
        covidFetcher.retrievePHCovidCase();
        covidFetcher.retrievePHCovidCaseByFacility();
        return new ResponseEntity<>("DONE!", HttpStatus.OK);
    }
}
