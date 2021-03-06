package io.project.virustracker.service.fetcher;

import io.project.virustracker.entity.COVIDCase;
import io.project.virustracker.component.COVIDCaseComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class COVIDCaseFetcher {

    private COVIDCaseComponent COVIDCaseComponent;

    @Autowired
    public void setCOVIDCaseComponent(COVIDCaseComponent COVIDCaseComponent) {
        this.COVIDCaseComponent = COVIDCaseComponent;
    }

    /**
     * A scheduled function to fetch data every 3 hours. <br>
     * for scheduling use cron job expression.
     * <p>
     * TODO construct expression that will run every 3 hours
     * Schedules:
     * Every 3 hours - ""
     */
    // @PostConstruct
    //@Scheduled(fixedDelay = 10000)
    public void fetchData() {
        int START_DATE = -2;
        int END_DATE = -2;
        if (COVIDCaseComponent.fetchCoronaVirusCase(START_DATE, END_DATE).size() != 0) {
            for (COVIDCase COVIDCase : COVIDCaseComponent.fetchCoronaVirusCase(START_DATE, END_DATE)) {
                System.out.println("Data Fetch" + COVIDCase.getLastUpdate());
            }
        } else {
            System.out.println("No data for this time");
        }
    }

}
