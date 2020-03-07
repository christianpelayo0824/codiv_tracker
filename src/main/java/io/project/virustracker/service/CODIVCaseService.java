package io.project.virustracker.service;

import io.project.virustracker.entity.CODIVCase;

import java.util.List;

public interface CODIVCaseService {

    boolean writeCoronaCaseList(int startDate, int endDate);

    List<CODIVCase> getCoronaCaseList();

    List<CODIVCase> findCaseByLatestDate();
}
