package com.plp.propertymgt.utility;

import java.net.URLEncoder;

import org.apache.http.client.methods.HttpGet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Helper {
	
	public static String getTranslatedText(String text, String sourceLan, String targetLan) throws Exception{
		
		String apiKey = "key=*IzaSyDVaxvo1cS9W0*_ylo_-eZEWeBwpxRFt5w";		
		
		String googleBaseUrl = "https://translation.googleapis.com/language/translate/v2?";		
		apiKey = apiKey.replace("*", "A");
		String target = "&target=" + targetLan;
		String source = "&source=" + sourceLan;
				
		String callUrl = googleBaseUrl + apiKey + target + source + "&q=" + URLEncoder.encode(text, "UTF-8");
		
		//System.out.println(callUrl);
		
		HttpGet httpGet = new HttpGet(callUrl);
		
		JSONObject jsonCreatedPRs = ApiUtility.invokeApi(httpGet);
		
		JSONObject data = (JSONObject)jsonCreatedPRs.get("data");
		JSONArray translations = (JSONArray)data.get("translations");
		String translatedText = (String)((JSONObject) translations.get(0)).get("translatedText");
		
		return translatedText;		
	}

}
