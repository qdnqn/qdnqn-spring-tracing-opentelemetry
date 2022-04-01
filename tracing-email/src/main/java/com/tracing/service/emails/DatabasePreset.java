package com.tracing.service.emails;

import com.tracing.report.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tracing.email.Email;

@Configuration
class DatabasePreset {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePreset.class);

    @Bean
    CommandLineRunner initDatabase(EmailRepository repository) {

        return args -> {
            logger.info("Preloading " + repository.save(new Email(1L, "bilbo@email.com.", "{preloaded-report}")));
            logger.info("Preloading " + repository.save(new Email(2L, "frodo@email.com", "{preloaded-report}")));
        };
    }
}