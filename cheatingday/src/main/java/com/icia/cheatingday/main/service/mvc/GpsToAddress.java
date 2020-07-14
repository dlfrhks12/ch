/*
 * package com.icia.cheatingday.main.service.mvc;
 * 
 *//**
	 * GpsToAddress - GPS 위도 경도로 한글 주소로 변환 - google maps API
	 *//*
		 * 
		 * import java.io.BufferedReader; import java.io.InputStreamReader; import
		 * java.net.*;
		 * 
		 * import org.json.simple.JSONArray; import org.json.simple.JSONObject; import
		 * org.json.simple.JSONValue; import org.springframework.stereotype.*;
		 * 
		 * 
		 * @Service public class GpsToAddress { double latitude; double longitude;
		 * String regionAddress;
		 * 
		 * public GpsToAddress(double latitude, double longitude) throws Exception {
		 * this.latitude = latitude; this.longitude = longitude; this.regionAddress =
		 * getRegionAddress(getJSONData(getApiAddress())); }
		 * 
		 * private String getApiAddress() { String apiURL =
		 * "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," +
		 * longitude + "&language=ko"; return apiURL; }
		 * 
		 * private String getJSONData(String apiURL) throws Exception { String
		 * jsonString = new String(); String buf; URL url = new URL(apiURL);
		 * URLConnection conn = url.openConnection(); BufferedReader br = new
		 * BufferedReader(new InputStreamReader( conn.getInputStream(), "UTF-8")); while
		 * ((buf = br.readLine()) != null) { jsonString += buf; } return jsonString; }
		 * 
		 * private String getRegionAddress(String jsonString) { JSONObject jObj =
		 * (JSONObject) JSONValue.parse(jsonString); JSONArray jArray = (JSONArray)
		 * jObj.get("results"); jObj = (JSONObject) jArray.get(0); return (String)
		 * jObj.get("formatted_address"); }
		 * 
		 * public String getAddress() { return regionAddress; } }
		 */