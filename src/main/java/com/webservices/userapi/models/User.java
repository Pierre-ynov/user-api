package com.webservices.userapi.models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.userapi.utils.JsonNodeUtil;
import com.webservices.userapi.utils.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("user")
public class User {
	@Id
	private String id;
	private String username;
	private String country;
	private Status accountStatus;
	
	public User () {
		
	}
	
	public User (String username, String country, Status accountStatus) {
		this.id = UUID.randomUUID().toString();
		this.username = username;
		this.country = country;
		this.accountStatus = accountStatus;
	}
	
	public User (ObjectNode json) {
		id = UUID.randomUUID().toString();
		username = JsonNodeUtil.getJsonNodeAsText(json, "username");
		country = JsonNodeUtil.getJsonNodeAsText(json, "country");
		accountStatus = JsonNodeUtil.getJsonNodeAsStatus(json, "accountStatus");
	}
	
	public User updateUser (ObjectNode json) {
		if (json.has("username")) {
			this.setUsername(JsonNodeUtil.getJsonNodeAsText(json, "username"));
		}
		if (json.has("country")) {
			this.setCountry(JsonNodeUtil.getJsonNodeAsText(json, "country"));
		}
		if (json.has("accountStatus")) {
			this.setAccountStatus(JsonNodeUtil.getJsonNodeAsStatus(json, "accountStatus"));
		}
		return this;
	}
	
	public User deleteUser () {
		this.setAccountStatus(Status.SUSPENDED);
		return this;
	}
}
