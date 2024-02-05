package com.nowon.bul.naverMail.naverapi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class MailFoldersResponse {
	private String code;
	private String description;
	List<MailFolder> mailFolders;

}
