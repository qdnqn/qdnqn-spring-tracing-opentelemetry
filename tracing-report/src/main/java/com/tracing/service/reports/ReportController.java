package com.tracing.service.reports;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tracing.report.Report;
import org.springframework.kafka.core.KafkaTemplate;
import com.tracing.service.reports.ReportNotFoundException;

@RestController
public class ReportController
{
    private final ReportRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private KafkaTemplate<String, Report> kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String topic;

    ReportController(ReportRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reports")
    List<Report> all() {
        logger.info("Listing all users");
        return repository.findAll();
    }

    @PostMapping(value="/reports", consumes={"application/json"})
    public ResponseEntity<Report> newReport(@RequestBody Report newReport) {
        logger.info("Creating new report: {}", newReport.getId());

        Report report = repository.save(newReport);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(report.getId())
                .toUri();

        kafkaTemplate.send(this.topic, report);

        return ResponseEntity.created(uri).body(report);
    }

    @GetMapping("/reports/{id}")
    Report one(@PathVariable Long id) {
        logger.info("Listing info about user: {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    @DeleteMapping("/reports/{id}")
    void deleteReport(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
