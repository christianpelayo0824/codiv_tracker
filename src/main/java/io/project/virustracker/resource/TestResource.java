package io.project.virustracker.resource;

import io.project.virustracker.service.fetcher.PHCodivFetcher;
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
    PHCodivFetcher phCodivFetcher;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test() {
        return new ResponseEntity <>(phCodivFetcher.retrievePHCodivCaseByFacility(), HttpStatus.OK);
    }
}
