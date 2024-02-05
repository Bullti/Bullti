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
    private String formattedSentDate;
    
    //날짜변경
	public void setFormattedSentDate(String formattedSentDate) {
		this.formattedSentDate = formattedSentDate;
		
	}
	
	//제목길이 
	 public void setSubject(String subject) {
	        final int MAX_SUBJECT_LENGTH = 30;
	        if (subject.length() > MAX_SUBJECT_LENGTH) {
	            this.subject = subject.substring(0, MAX_SUBJECT_LENGTH) + "...";
	        } else {
	            this.subject = subject;
	        }
	    }

}
