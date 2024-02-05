package com.nowon.bul.naverMail.naverapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailFolder {
	private int folderId;          //0,
    private String folderType;          //"S",
    private String folderName;          //"받은메일함",
    private int unreadMailCount;          //15356,
    private int mailCount;          //15540,
    private long usage;          //371519346
	

}
