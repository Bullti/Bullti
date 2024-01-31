package com.nowon.bul.mail;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MailDTO {

	private String from;
    private String subject;
    private Date sentDate;
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
