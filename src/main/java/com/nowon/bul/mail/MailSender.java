package com.nowon.bul.mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nowon.bul.domain.entity.member.MyUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MailSender {

    private final MailService mailService;

    public void sendMail(Authentication authentication, String toAddress, String subject, String content) {
        MyUser myuser = (MyUser) authentication.getPrincipal();
        MemberEmail memberEmail = mailService.findById(myuser.getMemberNo());

        String userName = memberEmail.getEmail();
        String password = memberEmail.getPassword();

        System.out.println(">>>>>>>>>>>>>>" + userName);
        System.out.println(">>>>>>>>>>>>>>" + password);

        String toAddresss = toAddress;  // 받는 사람의 이메일 주소 지정
        
        System.out.println(">>>>>>>>>>>>>>" + toAddresss);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.localhost", "bulti");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        session.setDebug(true);
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddresss));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("메일 전송 성공");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("메일 전송 중 오류 발생: " + e.getMessage());
        }
    }
}
