package io.project.virustracker.service.implementation;

import io.project.virustracker.entity.PHCasesByFacility;
import io.project.virustracker.entity.PHCovidCase;
import io.project.virustracker.repository.PHCasesByFacilityRepository;
import io.project.virustracker.repository.PHCovidCaseRepository;
import io.project.virustracker.service.PHCovidCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PHCovidCaseServiceImp implements PHCovidCaseService {

    PHCasesByFacilityRepository casesByFacilityRepository;
    PHCovidCaseRepository covidCaseRepository;

    @Autowired
    public PHCovidCaseServiceImp(PHCasesByFacilityRepository casesByFacilityRepository, PHCovidCaseRepository covidCaseRepository) {
        this.casesByFacilityRepository = casesByFacilityRepository;
        this.covidCaseRepository = covidCaseRepository;
    }

    @Override
    public List<PHCasesByFacility> retrieveCovidCaseByFacility() {
        return casesByFacilityRepository.findByOrderByCreationDateTimeAsc();
    }

    @Override
    public PHCovidCase retrievePHCovidCaseSummary() {
        return covidCaseRepository.findFirstByOrderByCreationDateTimeDesc();
    }
}
