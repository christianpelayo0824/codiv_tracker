package io.project.virustracker.service;

import io.project.virustracker.entity.COVIDCase;

import java.util.List;
import java.util.Map;

public interface COVIDCaseService {

    boolean writeCoronaCaseList(int startDate, int endDate);

    List<COVIDCase> getCoronaCaseList();

    List<COVIDCase> findCaseByLatestDate();

    Map<String, Long> getTotalCased();
}
