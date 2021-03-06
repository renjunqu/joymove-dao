package com.joymove.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.json.simple.JSONObject;
@Alias("JOYIntegration")
public class JOYIntegration extends  JOYBase  {

	public static String tableName = "JOY_Integration";


	public JOYIntegration() {
	}

	public Integer id;
	
	public String mobileNo;
	
	public Integer jifen;
	
	public String jifenDesc;
	
	public Integer statusMark;
	
	public Date jifenDate;

	
	



	public JSONObject toJSON(){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jifen",this.jifen);
		jsonObj.put("jifenDesc",this.jifenDesc);
		//SimpleDateFormat   dateFormatter   =   new   SimpleDateFormat   ("yyyy-MM-dd   HH:mm:ss     ");   
		//String dateStr = dateFormatter.format(new   Date(System.currentTimeMillis()));
		jsonObj.put("startTime",this.jifenDate.getTime()/1000);

		return jsonObj;
	}
}
