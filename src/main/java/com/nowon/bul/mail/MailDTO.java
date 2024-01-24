package com.nowon.bul.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MailDTO {

	private String from;
    private String subject;
    private String sentDate;
    private String message;
    private String attachments;

    // 생성자, getter, setter 등 필요한 메서드들 추가

    // 예시로 필드들을 출력하는 toString 메서드
    @Override
    public String toString() {
        return "MailDTO{" +
                "from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", sentDate='" + sentDate + '\'' +
                ", message='" + message + '\'' +
                ", attachments='" + attachments + '\'' +
                '}';
    }
}
