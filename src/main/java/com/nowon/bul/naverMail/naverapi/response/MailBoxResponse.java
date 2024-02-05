package com.nowon.bul.naverMail.naverapi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailBoxResponse {
	
	private List<Mail> mails;
	private int unreadCount;
	private String folderName;
	private int totalCount;
	private String responseMetaData;
	private int listCount;

}
