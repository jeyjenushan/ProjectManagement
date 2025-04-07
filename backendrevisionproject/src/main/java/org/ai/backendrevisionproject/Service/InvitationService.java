package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.Invitation;

public interface InvitationService {

    public void sendInvitation(String email,Long projectId) throws Exception;
    public Invitation acceptInvitation(String token, Long userId)throws Exception;
    public String getTokenByUserMail(String userEmail);
    void deleteByToken(String token);


}
