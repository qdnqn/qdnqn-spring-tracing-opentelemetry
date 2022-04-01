package com.tracing.email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Email {

    Email() {}

    public Email(Long emailId, String email, String content) {
        this.emailId = emailId;
        this.email = email;
        this.content = content;
    }

    private @Id @GeneratedValue Long id;
    private Long emailId;
    private String email;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Email [id=" + id + ", emailId=" + emailId + ", email=" + email + ", content=" +content+ "]";
    }
}