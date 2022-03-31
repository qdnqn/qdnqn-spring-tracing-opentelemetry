package com.tracing.service.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tracing.report.Report;

@Configuration
class DatabasePreset {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePreset.class);

    @Bean
    CommandLineRunner initDatabase(ReportRepository repository) {

        return args -> {
            logger.info("Preloading " + repository.save(new Report(1L, "Stole ring and gave it as gift.")));
            logger.info("Preloading " + repository.save(new Report(2L, "Got the ring as the gift")));
        };
    }
}