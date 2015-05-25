package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;
@Alias("JOYDynamicPws")
public class JOYDynamicPws {
	
	public Integer  codeId;
	
	public  String mobileNo;
	
	public  String code;
	
	public  Date createTime;
	
	
}
