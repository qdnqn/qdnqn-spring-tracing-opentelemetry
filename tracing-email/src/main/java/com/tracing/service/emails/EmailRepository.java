package com.tracing.service.emails;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracing.email.Email;

interface EmailRepository extends JpaRepository<Email, Long> {

}