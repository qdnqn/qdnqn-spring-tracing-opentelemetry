package com.tracing.service.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tracing.user.User;

@Configuration
class DatabasePreset {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePreset.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            logger.info("Preloading " + repository.save(new User(1L, "burglar")));
            logger.info("Preloading " + repository.save(new User(1L, "thief")));
        };
    }
}