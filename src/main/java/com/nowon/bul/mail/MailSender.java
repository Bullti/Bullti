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

import org.springframework.stereotype.Service;

@Service
public class MailSender {

    public static void sendMail() {
        final String username = "dptmf921008@gmail.com";
        final String password = "nabuzgdoguboxeph";
        String toAddress = "cjstk78854@naver.com"; // 받는 사람의 이메일 주소 지정

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject("[제목] 메일 테스트 발송");
            message.setText("[본문] 메일 테스트 발송");

            Transport.send(message);

            System.out.println("메일 전송 성공");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("메일 전송 중 오류 발생: " + e.getMessage());
        }
    }
}
