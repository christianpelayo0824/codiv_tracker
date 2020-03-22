package io.project.virustracker.service;

import io.project.virustracker.entity.PHCasesByFacility;
import io.project.virustracker.entity.PHCovidCase;

import java.util.List;

public interface PHCovidCaseService {

    List<PHCasesByFacility> retrieveCovidCaseByFacility();

    PHCovidCase retrievePHCovidCaseSummary();
}
