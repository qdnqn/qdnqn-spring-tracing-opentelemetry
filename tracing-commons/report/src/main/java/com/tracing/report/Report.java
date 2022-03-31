package com.tracing.report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Report {

    Report() {}

    public Report(Long userId, String report) {
        this.userId = userId;
        this.report = report;
    }

    private @Id @GeneratedValue Long id;
    private Long userId;
    private String report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportId() {
        return userId;
    }

    public void setReportId(Long userId) {
        this.userId = userId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "Report [id=" + id + ", userId=" + userId + ", report=" + report + "]";
    }
}