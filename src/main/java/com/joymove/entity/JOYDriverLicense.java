package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.Arrays;
import java.util.Date;
@Alias("JOYDriverLicense")
public class JOYDriverLicense {

	public String mobileNo;
	
	public byte[] driverAuthInfo;
	
	public String driverLicenseNumber;
	
	public Date   expireTime;
	
}
