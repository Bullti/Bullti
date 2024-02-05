package com.nowon.bul.naverMail.naverapi.request;

import lombok.Data;

@Data
public class LoginDTO {
	
	private String response_type;
	private String client_id;
	private String redirect_uri;
	private String worksId;
	
}
