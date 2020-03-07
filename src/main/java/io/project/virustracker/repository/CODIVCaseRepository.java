package io.project.virustracker.repository;

import io.project.virustracker.entity.CODIVCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CODIVCaseRepository extends JpaRepository<CODIVCase, Long> {

    /**
     * This function retrieve list of CoronaCase object <br>
     * provided by a String of Date in a form of <br>w
     * "yyyy-MM-dd"
     *
     * @param date yyyy-MM-dd
     * @return List<CoronaCase>
     */
    List<CODIVCase> findByPublishDate(String date);
}
