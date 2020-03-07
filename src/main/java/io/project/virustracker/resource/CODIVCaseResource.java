package io.project.virustracker.resource;


import io.project.virustracker.entity.CODIVCase;
import io.project.virustracker.service.CODIVCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/corona")
public class CODIVCaseResource {

    CODIVCaseService codivCaseService;

    @Autowired
    public void setCodivCaseService(CODIVCaseService codivCaseService) {
        this.codivCaseService = codivCaseService;
    }

    @GetMapping(path = "/fetchHistoricalData/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchHistoricalData(@PathVariable("startDate") int startDate,
                                                 @PathVariable("endDate") int endDate) {
        return new ResponseEntity<>(codivCaseService.writeCoronaCaseList(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping(path = "/coronaCaseLatest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CODIVCase>> getCoronaCaseList() {
        return new ResponseEntity<>(codivCaseService.findCaseByLatestDate(), HttpStatus.OK);
    }


}
