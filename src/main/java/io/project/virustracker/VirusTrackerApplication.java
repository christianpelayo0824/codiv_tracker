package io.project.virustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class VirusTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirusTrackerApplication.class, args);
    }

}
