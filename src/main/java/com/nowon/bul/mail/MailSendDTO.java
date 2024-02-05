package com.nowon.bul.mail;

import lombok.Getter;

@Getter
public class MailSendDTO {

    private String toAddress;
    private String subject;
    private String content;
}
