	package com.nowon.bul.mail;
	
	import java.io.File;
	import java.io.IOException;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Date;
import java.util.List;
import java.util.Properties;
	
	import javax.mail.Address;
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
	    public List<MailDTO> receiveMailAttachedFile(String userName, String password, Date startDate, Date endDate) {
	    	
	    	System.out.println("receiveMailAttachedFile 시작");
	    	
	        Properties props = System.getProperties();
	        props.setProperty("mail.store.protocol", "imaps");
	        try {
	            Session session = Session.getDefaultInstance(props, null);
	            Store store = session.getStore("imaps");
	            store.connect("imap.gmail.com", userName, password);
	            
	            System.out.println("Connected to Gmail IMAP server");
	
	            // 받은편지함을 INBOX 라고 한다.
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);
	            
	            System.out.println("Opened INBOX folder");
	            
	            SearchTerm sentDateTerm = new SentDateTerm(ComparisonTerm.GE, startDate);
	            SearchTerm andTerm = new AndTerm(sentDateTerm, new SentDateTerm(ComparisonTerm.LE, endDate));
	
	            // 검색한 기간에 해당하는 메일 읽어오기
	            Message[] arrayMessages = inbox.search(andTerm);
	            
	            System.out.println("Total messages found: " + arrayMessages.length);
	            
	            for (int i = arrayMessages.length; i > 0; i--) {
	            	
	            	System.out.println("Processing message #" + (i + 1));
	            	
	                Message msg = arrayMessages[i - 1];
	                Address[] fromAddress = msg.getFrom();
	                // 메일 내용 변수에 담기
	                String from = fromAddress[0].toString();
	                String subject = msg.getSubject();
	                String sentDate = msg.getSentDate().toString();
	                String receivedDate = msg.getReceivedDate().toString();
	                String contentType = msg.getContentType();
	                String messageContent = "";
	                String attachFiles = "";
	
	                // 첨부파일
	                if (contentType.contains("multipart")) {
	                    Multipart multiPart = (Multipart) msg.getContent();
	                    int numberOfParts = multiPart.getCount();
	                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
	                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	                            // 첨부파일 있을 경우 지정 폴더로 저장
	                            String fileName = part.getFileName();
	                            attachFiles += fileName + ", ";
	                            part.saveFile(saveDirectory + File.separator + fileName);
	                        } else {
	                            // 메일 내용 저장
	                            messageContent = part.getContent().toString();
	                        }
	                    }
	                    if (attachFiles.length() > 1) {
	                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
	                    }
	                } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
	                    Object content = msg.getContent();
	                    if (content != null) {
	                        messageContent = content.toString();
	                    }
	                }
	
	                // 읽어온 메일 콘솔창 출력
	                System.out.println("Message #" + (i + 1) + ":");
	                System.out.println("\t From: " + from);
	                System.out.println("\t Subject: " + subject);
	                System.out.println("\t Received: " + sentDate);
	                System.out.println("\t Message: " + messageContent);
	                System.out.println("\t Attachments: " + attachFiles);
	            }
	
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
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            System.out.println("IOException: " + ex.getMessage());
	            ex.printStackTrace(System.out);
	        }
	        
	        System.out.println("receiveMailAttachedFile 종료");
			return null;
	    }
	}