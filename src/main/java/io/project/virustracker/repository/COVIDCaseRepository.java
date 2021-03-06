package io.project.virustracker.repository;

import io.project.virustracker.entity.COVIDCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface COVIDCaseRepository extends JpaRepository<COVIDCase, Long> {

    /**
     * This function retrieve list of CoronaCase object <br>
     * provided by a String of Date in a form of <br>w
     * "yyyy-MM-dd"
     *
     * @param date yyyy-MM-dd
     * @return List<CoronaCase>
     */
    List<COVIDCase> findByPublishedDate(String date);

    @Query(nativeQuery = true,
            value = "SELECT SUM(confirmed) confirmed," +
                    "SUM(deaths) deaths," +
                    "SUM(recovered) recovered" +
                    " FROM covidcase")
    Map<String, Long> totalCased();

}
