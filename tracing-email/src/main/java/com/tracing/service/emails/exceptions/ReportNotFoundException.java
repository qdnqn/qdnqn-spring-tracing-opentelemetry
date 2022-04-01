package com.tracing.service.emails;

class EmailNotFoundException extends RuntimeException {

    EmailNotFoundException(Long id) {
        super("Could not find report " + id);
    }
}