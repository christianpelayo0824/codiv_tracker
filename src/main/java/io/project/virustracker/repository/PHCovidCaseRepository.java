package io.project.virustracker.repository;

import io.project.virustracker.entity.PHCovidCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PHCovidCaseRepository extends JpaRepository<PHCovidCase, Long> {

    PHCovidCase findFirstByOrderByCreationDateTimeDesc();
}
