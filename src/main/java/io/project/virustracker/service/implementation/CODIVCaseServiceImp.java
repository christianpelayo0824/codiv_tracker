package io.project.virustracker.service.implementation;

import io.project.virustracker.entity.CODIVCase;
import io.project.virustracker.fetcher.CODIVCaseComponent;
import io.project.virustracker.repository.CODIVCaseRepository;
import io.project.virustracker.service.CODIVCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class CODIVCaseServiceImp implements CODIVCaseService {

    private CODIVCaseRepository codivCaseRepository;

    private CODIVCaseComponent codivCaseComponent;

    @Autowired
    public void setCodivCaseComponent(CODIVCaseComponent codivCaseComponent) {
        this.codivCaseComponent = codivCaseComponent;
    }

    @Autowired
    public void setCodivCaseRepository(CODIVCaseRepository codivCaseRepository) {
        this.codivCaseRepository = codivCaseRepository;
    }


    @Override
    public boolean writeCoronaCaseList(int startDate, int endDate) {
        List<CODIVCase> codivCaseList = codivCaseComponent.fetchCoronaVirusCase(startDate, endDate);
        if (codivCaseList.size() != 0) {
            codivCaseRepository.saveAll(codivCaseList);
            return true;
        }
        return false;
    }

    @Override
    public List<CODIVCase> getCoronaCaseList() {
        return codivCaseRepository.findAll();
    }

    @Override
    public List<CODIVCase> findCaseByLatestDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Calendar cal = Calendar.getInstance();
        return codivCaseRepository.findByPublishDate("03-06-2020");
    }

}
