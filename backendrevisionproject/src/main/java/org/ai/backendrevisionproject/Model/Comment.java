package org.ai.backendrevisionproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime createdDateTime;

    @ManyToOne
    private User user;


    @ManyToOne
    private Issue issue;

    public Comment() {
    }

    public Comment(Long id, String content, LocalDateTime createdDateTime, User user, Issue issue) {
        this.id = id;
        this.content = content;
        this.createdDateTime = createdDateTime;
        this.user = user;
        this.issue = issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
