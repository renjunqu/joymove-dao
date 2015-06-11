package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;
@Alias("JOYDynamicPws")
public class JOYDynamicPws extends  JOYBase  {

	public static String tableName = "JOY_DynamicPws";

	public JOYDynamicPws() {
	}

	public Integer  id;
	
	public  String mobileNo;
	
	public  String code;
	
	public  Date createTime;
	
	
}
