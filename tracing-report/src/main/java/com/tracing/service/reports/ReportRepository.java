package com.tracing.service.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracing.report.Report;

interface ReportRepository extends JpaRepository<Report, Long> {

}