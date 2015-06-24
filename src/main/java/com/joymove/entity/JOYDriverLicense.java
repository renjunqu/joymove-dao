package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;
@Alias("JOYDriverLicense")
public class JOYDriverLicense extends  JOYBase  {
	public static String tableName = "JOY_DriverLicense";


	public JOYDriverLicense() {
	}

	//缺少一个id

	Integer id;

	public String mobileNo;
	
	public String driverAuthInfo;

	public String driverAuthInfo_back;
	
	public String driverLicenseNumber;
	
	public Date   expireTime;
	
}
