package io.project.virustracker;

import com.mysql.cj.jdbc.admin.TimezoneDump;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class VirusTrackerApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("Spring boot application running in UTC timezone :" + new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(VirusTrackerApplication.class, args);
    }

}
