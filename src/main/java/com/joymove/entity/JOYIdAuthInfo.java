package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.Arrays;
@Alias("JOYIdAuthInfo")
public class JOYIdAuthInfo  extends  JOYBase {

	public static String tableName = "JOY_IdAuthInfo";

	public JOYIdAuthInfo() {
	}

	public String mobileNo;
	
	public byte[] idAuthInfo;
	public byte[] idAuthInfo_back;
	public String idName;
	public String idNo;
}
