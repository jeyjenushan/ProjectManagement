package org.ai.backendrevisionproject.Request;

public class CommentRequest {

    private Long issueId;
    private String content;
    public CommentRequest() {
    }

    public CommentRequest(Long issueId, String content) {
        this.issueId = issueId;
        this.content = content;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
