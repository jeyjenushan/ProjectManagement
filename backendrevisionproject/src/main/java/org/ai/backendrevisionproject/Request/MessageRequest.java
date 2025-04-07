package org.ai.backendrevisionproject.Request;

public class MessageRequest {

    private Long projectId;
    private String content;
    private Long userId;

    public MessageRequest() {
    }

    public MessageRequest(Long projectId, String content, Long userId) {
        this.projectId = projectId;
        this.content = content;
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
