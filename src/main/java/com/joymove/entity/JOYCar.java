package com.joymove.entity;

import java.util.*;
import java.math.*;
import org.apache.ibatis.type.Alias;

@Alias("JOYCar")
public class JOYCar {
	
	static public int STATE_FREE = 0;
	static public int STATE_BUSY = 1;
	static public int STATE_RESERVE = 2;
	
	
	
	public Integer    id;
	public BigDecimal positionX;
	public BigDecimal positionY;
	public Integer     state;
	public String      desp;
	public String      mobileNo;
}
