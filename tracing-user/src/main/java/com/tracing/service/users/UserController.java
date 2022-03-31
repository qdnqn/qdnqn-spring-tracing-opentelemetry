package com.tracing.service.users;

import com.tracing.service.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tracing.user.User;
import com.tracing.service.users.UserNotFoundException;
import com.tracing.report.Report;

import java.util.List;

@RestController
public class UserController
{
    private final UserRepository repository;
    private com.tracing.service.users.ReportClient reportClient;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${services.report.url}")
    private String reportURL;

    UserController(UserRepository repository, com.tracing.service.users.ReportClient reportClient) {
        this.repository = repository;
        this.reportClient = reportClient;
    }

    @GetMapping("/users")
    List<User> all() {
        logger.info("Listing all users");
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        logger.info("Listing info about user: {}", id);

        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @ResponseBody
    @GetMapping("/users/{id}/generateReport")
    Report generateReport(@PathVariable Long id) {
        logger.info("Creating new report for user: {}", id);

        Report report = this.reportClient.postReportForCustomerId(id);
        return report;
    }


    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
