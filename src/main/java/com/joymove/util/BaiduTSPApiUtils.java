package com.joymove.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.futuremove.cacheServer.utils.*;

public class BaiduTSPApiUtils {
	static public  String ak="wGThsw4y1wBqBmQDqHZEguvg"; 
	
	public static void getDriveInfo(double ori_lat,double ori_lgn,double des_lat,double des_lgn,String cityName) {
		try {
			String url="http://api.map.baidu.com/telematics/v3/navigation?output=json&";//
			String params = "ak="+ak;
			url+=params+"&";
			String origin = "origin="+ori_lgn+","+ori_lat;
			url+=origin+"&";
			String destination = "destination="+des_lgn+","+des_lat;
			url+=destination+"&";
			String city = "region="+cityName;
			url+=city;
			//url = URLEncoder.encode(url,"utf-8");
			//logger.trace(HttpGetUtils.get(url));
			String jsonStr = HttpGetUtils.get(url);
			JSONParser parser=new JSONParser();
			JSONObject reObj = (JSONObject) parser.parse(jsonStr);
			JSONObject apiResult = (JSONObject) ((JSONArray) reObj.get("results")).get(0);
			/*
			logger.trace(reObj.get("returnType"));
			logger.trace(reObj.get("status"));
			logger.trace(apiResult.get("duration"));
			logger.trace(apiResult.get("distance"));
		    */
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String []args){
		BaiduTSPApiUtils.getDriveInfo(39.90828800,116.41791300, 39.90809000,116.41156200, "北京");
	}

}
