package com.kamantas.mob.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONResponseParser {

	private JSONObject response;
	
	public JSONResponseParser(String response) throws JSONException {
		this.response = new JSONObject(response);
	}
	
}
