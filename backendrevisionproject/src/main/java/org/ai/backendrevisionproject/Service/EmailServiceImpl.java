package org.ai.backendrevisionproject.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{


    @Autowired
   private JavaMailSender mailSender;


    @Override
    public void sendEmailWithToken(String userEmail, String Link) throws Exception {

        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        String subject="Join Project Team Invitation";
        String text="Click the link to join the project team : "+Link;
        mimeMessageHelper.setTo(userEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text,true);
        try{
            mailSender.send(mimeMessage);
        }catch (Exception e){
            throw new Exception("Failed to send email");
        }



    }
}
