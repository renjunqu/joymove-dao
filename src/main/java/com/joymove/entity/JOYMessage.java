package com.joymove.entity;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.json.simple.JSONObject;
@Alias("JOYMessage")
public class JOYMessage {
	 
	public Integer id;

	public String type;

	public String content;

	public Date createTime;

	public String mobileNo;

	public JSONObject toJSON(){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type",this.type);
		jsonObj.put("content",this.content);
		jsonObj.put("createTime",this.createTime.getTime()/1000);
		return jsonObj;
	}




}
