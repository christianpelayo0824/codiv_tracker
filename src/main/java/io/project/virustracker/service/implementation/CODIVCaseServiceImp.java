package io.project.virustracker.service.implementation;

import io.project.virustracker.entity.CODIVCase;
import io.project.virustracker.component.CODIVCaseComponent;
import io.project.virustracker.repository.CODIVCaseRepository;
import io.project.virustracker.service.CODIVCaseService;
import io.project.virustracker.utility.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

        // Check if the current date have data, if empty get the
        // data of the previous date.
        int dateCounter = 0;
        List<CODIVCase> caseList = codivCaseRepository.findByPublishedDate(DateUtil.getCurrentDate());

        while (caseList.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, dateCounter--);
            caseList = codivCaseRepository.findByPublishedDate(DateUtil.DATE_FORMAT_YYYY_MM_DD.format(calendar.getTime()));
        }
        return caseList;
    }

    @Override
    public Map<String, Long> getTotalCased() {
        return codivCaseRepository.totalCased();
    }

}
