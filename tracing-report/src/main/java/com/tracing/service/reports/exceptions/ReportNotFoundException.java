package com.tracing.service.reports;

class ReportNotFoundException extends RuntimeException {

    ReportNotFoundException(Long id) {
        super("Could not find report " + id);
    }
}