package com.tracing.service.users;

import com.tracing.report.Report;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReportClient {
    private final RestTemplate restTemplate;

    @Value("${services.report.url}")
    private String reportURL;

    public ReportClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Report postReportForCustomerId(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject reportJsonObject = new JSONObject();
        reportJsonObject.put("id", id);
        reportJsonObject.put("report", "This new generated report.");

        HttpEntity<String> request = new HttpEntity<String>(reportJsonObject.toString(), headers);

        return restTemplate.postForObject(this.reportURL + "/reports", request, Report.class);
    }
}
