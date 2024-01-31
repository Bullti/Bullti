package com.nowon.bul.mail;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MailReader {
	private String saveDirectory;

	/**
	 * 첨부파일이 저장될 위치 설정
	 * 
	 * @param dir
	 */
	public void setSaveDirectory(String dir) {
		this.saveDirectory = dir;
	}

	/**
	 * Google gmail에 접근하여 지정한 기간 내에 모든 메일 가져오기
	 * 
	 * @param userName
	 * @param password
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws MessagingException
	 */

	public List<MailDTO> getMailList(String userName, String password, Date startDate, Date endDate, Model model) {
	    System.out.println("receiveMailAttachedFile 시작");

	    Properties props = System.getProperties();
	    props.setProperty("mail.store.protocol", "imaps");
	    List<MailDTO> mailList = new ArrayList<>();

	    try {
	        Session session = Session.getDefaultInstance(props, null);
	        Store store = session.getStore("imaps");
	        store.connect("imap.gmail.com", userName, password);

	        Folder inbox = store.getFolder("INBOX");
	        inbox.open(Folder.READ_ONLY);

	        SearchTerm sentDateTerm = new SentDateTerm(ComparisonTerm.GE, startDate);
	        SearchTerm andTerm = new AndTerm(sentDateTerm, new SentDateTerm(ComparisonTerm.LE, endDate));

	        Message[] arrayMessages = inbox.search(andTerm);
	        
	        System.out.println("본인이");
	        for (Message msg : arrayMessages) {
	            Address[] fromAddress = msg.getFrom();
	            MailDTO mail = new MailDTO();
	            mail.setFrom(fromAddress[0].toString());
	            mail.setSubject(msg.getSubject());
	            mail.setSentDate(msg.getReceivedDate());
	            mailList.add(mail);

	            // 첨부파일
				/*
				 * if (contentType.contains("multipart")) { Multipart multiPart = (Multipart)
				 * msg.getContent(); for (int partCount = 0; partCount < multiPart.getCount();
				 * partCount++) { MimeBodyPart part = (MimeBodyPart)
				 * multiPart.getBodyPart(partCount); if
				 * (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) { // 첨부파일 있을 경우 지정
				 * 폴더로 저장 String fileName = part.getFileName(); attachFiles += fileName + ", ";
				 * part.saveFile(saveDirectory + File.separator + fileName); } else { // 메일 내용
				 * 저장 messageContent = part.getContent().toString(); } } if
				 * (attachFiles.length() > 1) { attachFiles = attachFiles.substring(0,
				 * attachFiles.length() - 2); } } else if (contentType.contains("text/plain") ||
				 * contentType.contains("text/html")) { Object content = msg.getContent(); if
				 * (content != null) { messageContent = content.toString(); } }
				 */
	        }
	        System.out.println("해결을해야지");
	        // disconnect
	        inbox.close(false);
	        store.close();
	    } catch (NoSuchProviderException e) {
	        e.printStackTrace();
	        System.out.println("NoSuchProviderException: " + e.getMessage());
	        e.printStackTrace(System.out);
	        System.exit(1);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        System.out.println("MessagingException: " + e.getMessage());
	        e.printStackTrace(System.out);
	        System.exit(2);
		} /*
			 * catch (IOException ex) { ex.printStackTrace();
			 * System.out.println("IOException: " + ex.getMessage());
			 * ex.printStackTrace(System.out); }
			 */

	    //System.out.println("receiveMailAttachedFile 종료");
	    return mailList;
	}
}