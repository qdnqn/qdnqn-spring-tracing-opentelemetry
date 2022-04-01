package com.tracing.service.emails;

import java.net.URI;
import java.util.List;

import com.tracing.report.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tracing.email.Email;
import com.tracing.service.emails.EmailNotFoundException;

@RestController
public class EmailController
{
    private final EmailRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    EmailController(EmailRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "reports", groupId = "group")
    public void listenReports(Report report) {
        logger.info("Got report with: {}", report.getId());
        System.out.println("Received Message: " + report);
    }

    @GetMapping("/emails/{id}")
    Email one(@PathVariable Long id) {
        logger.info("Listing email with: {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new EmailNotFoundException(id));
    }

    @DeleteMapping("/emails/{id}")
    void deleteEmail(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
