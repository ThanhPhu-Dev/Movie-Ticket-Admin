package com.dinhthanhphu.movieticketadmin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class SendMailUtils {

    @Autowired
    public JavaMailSender emailSender;

    public void sendEmail(String recipientEmail, String subject, String content)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("cinema@gmail.com", "Cinema District 8");
        helper.setTo(recipientEmail);

        helper.setSubject(subject);

        helper.setText(content, true);

        emailSender.send(message);
    }

    public static String mailRegister(String id, String code){
        StringBuilder content = new StringBuilder("<p>Hello,</p>");
        content.append("<p>Thank you for register to our service<p>");
        content.append("<a href=\""+URLUtils.getSiteURL()+"/registerconfirm?id="+id+"&code="+code+"\">");
        content.append("click here for complete confirmation</a>");
        return content.toString();
    }
}
