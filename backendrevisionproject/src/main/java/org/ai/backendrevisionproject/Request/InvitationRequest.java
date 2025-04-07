package org.ai.backendrevisionproject.Request;

public class InvitationRequest {

    private Long projectId;
    private String email;

    public InvitationRequest() {
    }

    public InvitationRequest(Long projectId, String email) {
        this.projectId = projectId;
        this.email = email;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
