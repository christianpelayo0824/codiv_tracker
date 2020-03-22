package io.project.virustracker.repository;

import io.project.virustracker.entity.PHCasesByFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PHCasesByFacilityRepository extends JpaRepository<PHCasesByFacility, Long> {

    List<PHCasesByFacility> findByOrderByCreationDateTimeAsc();
}
