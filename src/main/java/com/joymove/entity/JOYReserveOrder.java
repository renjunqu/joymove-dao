package com.joymove.entity;
import org.apache.ibatis.type.Alias;

import java.util.*;
import java.math.*;
@Alias("JOYReserveOrder")
public class JOYReserveOrder {
	public Integer id;
	public String mobileNo;
	public Date startTime;
	public Integer carId;
	public Integer delFlag;
	public String carVinNum;
	public Integer  ifBlueTeeth;
	
	static public int DEL_FLAG = 1;
	static public int NODEL_FLAG = 0;
	static public int EXPIRE_SECONDS = 600;

}
