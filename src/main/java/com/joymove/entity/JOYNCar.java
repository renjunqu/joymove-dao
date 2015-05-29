package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/*
 *  new car, dynamic data inside cache server
 *  the old JOYCar just used to test
 * */
@Alias("JOYNCar")
public class JOYNCar {

	public int id;
	//the primary key
	public String vinNum;
	//the register time
	public Date registerTime;
	//rsa pub key
	public String RSAPubKey;
	//rsa pri key
	public String RSAPriKey;
	//register state
	public Integer registerState;
	//license num 
	public String licensenum;
	//lock state
	public Integer lockState;
	//if has blue teech device
	// 0 --> non blue teech
	// 1 --> has blue teech
	public Integer     ifBlueTeeth;


}
