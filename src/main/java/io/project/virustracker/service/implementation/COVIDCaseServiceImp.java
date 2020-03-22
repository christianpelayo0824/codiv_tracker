package io.project.virustracker.service.implementation;

import io.project.virustracker.entity.COVIDCase;
import io.project.virustracker.component.COVIDCaseComponent;
import io.project.virustracker.repository.COVIDCaseRepository;
import io.project.virustracker.service.COVIDCaseService;
import io.project.virustracker.utility.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class COVIDCaseServiceImp implements COVIDCaseService {

    private COVIDCaseRepository COVIDCaseRepository;

    private COVIDCaseComponent COVIDCaseComponent;

    @Autowired
    public void setCOVIDCaseComponent(COVIDCaseComponent COVIDCaseComponent) {
        this.COVIDCaseComponent = COVIDCaseComponent;
    }

    @Autowired
    public void setCOVIDCaseRepository(COVIDCaseRepository COVIDCaseRepository) {
        this.COVIDCaseRepository = COVIDCaseRepository;
    }


    @Override
    public boolean writeCoronaCaseList(int startDate, int endDate) {
        List<COVIDCase> COVIDCaseList = COVIDCaseComponent.fetchCoronaVirusCase(startDate, endDate);
        if (COVIDCaseList.size() != 0) {
            COVIDCaseRepository.saveAll(COVIDCaseList);
            return true;
        }
        return false;
    }

    @Override
    public List<COVIDCase> getCoronaCaseList() {
        return COVIDCaseRepository.findAll();
    }

    @Override
    public List<COVIDCase> findCaseByLatestDate() {

        // Check if the current date have data, if empty get the
        // data of the previous date.
        int dateCounter = 0;
        List<COVIDCase> caseList = COVIDCaseRepository.findByPublishedDate(DateUtil.getCurrentDate());

        while (caseList.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, dateCounter--);
            caseList = COVIDCaseRepository.findByPublishedDate(DateUtil.DATE_FORMAT_YYYY_MM_DD.format(calendar.getTime()));
        }
        return caseList;
    }

    @Override
    public Map<String, Long> getTotalCased() {
        return COVIDCaseRepository.totalCased();
    }

}
