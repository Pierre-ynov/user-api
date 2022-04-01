package com.webservices.userapi.models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.userapi.utils.JsonNodeUtil;
import com.webservices.userapi.utils.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class User {
	@Id
	private Integer id;
	private String  username;
	private String  country;
	private Status  accountStatus;
	
	public User () {
		
	}
	
	public User (String username, String country, Status accountStatus) {
		this.username = username;
		this.country = country;
		this.accountStatus = accountStatus;
	}
	
	public User (ObjectNode json) {
		id = JsonNodeUtil.getJsonNodeAsInteger(json, "id");
		username = JsonNodeUtil.getJsonNodeAsText(json, "username");
		country = JsonNodeUtil.getJsonNodeAsText(json, "country");
		accountStatus = JsonNodeUtil.getJsonNodeAsStatus(json, "accountStatus");
	}
}
