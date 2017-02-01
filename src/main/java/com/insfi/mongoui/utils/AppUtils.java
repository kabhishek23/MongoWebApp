package com.insfi.mongoui.utils;

import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

public class AppUtils {

	public static JSONObject constructResponse(List<Document> documents) {
		JSONObject result = new JSONObject();

		result.put("documents", documents);
		result.put("count", documents.size());

		return result;
	}

}
