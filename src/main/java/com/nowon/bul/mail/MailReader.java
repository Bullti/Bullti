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
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Component
public class MailReader {
	//private String saveDirectory;
	private static String imaps = "imaps";

	/**
	 * 첨부파일이 저장될 위치 설정
	 * 
	 * @param dir
	 */
//	public void setSaveDirectory(String dir) {
//		this.saveDirectory = dir;
//	}

	/**
	 * Google gmail에 접근하여 지정한 기간 내에 모든 메일 가져오기
	 * 
	 * @param userName
	 * @param password
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws IOException 
	 * @throws MessagingException
	 */

	public List<MailDTO> getMailList(String userName, String password, Date startDate, Date endDate, Model model) throws IOException {
		System.out.println("receiveMailAttachedFile 시작");

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", imaps);
		List<MailDTO> mailList = new ArrayList<>();


		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore(imaps);
			store.connect("imap.gmail.com", userName, password);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			SearchTerm sentDateTerm = new SentDateTerm(ComparisonTerm.GE, startDate);
			SearchTerm andTerm = new AndTerm(sentDateTerm, new SentDateTerm(ComparisonTerm.LE, endDate));
			Message[] arrMessages = inbox.search(andTerm);

		
			for (Message msg : arrMessages) {
				Address[] fromAddress = msg.getFrom();
				MailDTO mail = new MailDTO();
				mail.setFrom(fromAddress[0].toString());
				mail.setSubject(msg.getSubject());
				/*
				 * Object content = msg.getContent(); if (content instanceof MimeMultipart) {
				 * MimeMultipart multipart = (MimeMultipart) content; // text/plain 부분 추출 for
				 * (int i = 0; i < multipart.getCount(); i++) { BodyPart bodyPart =
				 * multipart.getBodyPart(i); if (bodyPart.isMimeType("text/plain")) {
				 * mail.setMessage(bodyPart.getContent().toString()); break; // text/plain 부분을
				 * 찾으면 루프를 종료합니다. } } } else { // 간단한 텍스트 메시지의 경우
				 * mail.setMessage(content.toString()); }
				 */
				mail.setSentDate(msg.getReceivedDate());
				mailList.add(0, mail);

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
			System.out.println("7");
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

		System.out.println("9");
		// System.out.println("receiveMailAttachedFile 종료");
		return mailList;
	}

	public void getMailList(String email, String password, Date startDate, Date endDate, int mailCount, Model model) {

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", imaps);
		List<MailDTO> mailList = new ArrayList<>();

		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore(imaps);
			store.connect("imap.gmail.com", email, password);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			SearchTerm sentDateTerm = new SentDateTerm(ComparisonTerm.GE, startDate);
			SearchTerm andTerm = new AndTerm(sentDateTerm, new SentDateTerm(ComparisonTerm.LE, endDate));

			Message[] arrayMessages = inbox.search(andTerm);

				
			for (int i = 0; i < mailCount; i++) {
				MailDTO mail = new MailDTO();
				mail.setFrom(arrayMessages[i].getFrom()[0].toString());
				mail.setSubject(arrayMessages[i].getSubject());
				//mail.setSentDate(arrayMessages[i].getReceivedDate());
				
				Date sentDate = arrayMessages[i].getReceivedDate();
		        
				String formattedSentDate = formatDate(sentDate); // 사용자 정의 메서드 호출
		        mail.setFormattedSentDate(formattedSentDate);

		        mail.setSentDate(sentDate);
				
		        mailList.add(0, mail);
			}
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
		}
		model.addAttribute("mailList", mailList);
		
	}

	private String formatDate(Date sentDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
	    return sdf.format(sentDate);
	}
}