package org.ai.backendrevisionproject.Service;

public interface EmailService {

    void sendEmailWithToken(String userEmail,String Link) throws Exception;
}
