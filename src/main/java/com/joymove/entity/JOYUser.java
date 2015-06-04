package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.*;

@Alias("JOYUser")
public class JOYUser {
	//还未认证
	public static int auth_state_pending = 0;
	public static int auth_state_ok      = 1;
	public static int auth_state_ing     = 2;
	public static int auth_state_failed  = 3;
    public  int    id;
	public String mobileNo;
	public String username;
	public String userpwd;
	public String email;
	public Date registerTime;
	public String gender;
	public String addr;
	public String  age;
	public Integer failedTimes;
	public Date   lockedTime;
	public String pushKey;
	public String authToken;
	public Date   lastActiveTime;
	public Integer id5PassFlag;
	public Integer authenticateId;
	public Integer authenticateDriver;
	public BigDecimal deposit;
	public String   homeAddr;
	public BigDecimal homeLatitude;
	public BigDecimal homeLongitude;
	public String corpAddr;
	public BigDecimal corpLatitude;
	public BigDecimal corpLongitude;
	public String     face_info;
	public String     voice_info;
	
}
