package com.webservices.userapi.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeUtil {
	
	public static String getJsonNodeAsText (ObjectNode json, String filter) {
		String   res  = null;
		JsonNode node = json.get(filter);
		if (node != null) {
			res = node.asText();
		}
		return res;
	}
	
	public static Status getJsonNodeAsStatus (ObjectNode json, String filter) {
		String tmp = getJsonNodeAsText(json, filter);
		try {
			return Status.valueOf(tmp);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
