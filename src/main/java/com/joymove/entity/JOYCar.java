package com.joymove.entity;

import java.util.*;
import java.math.*;
import org.apache.ibatis.type.Alias;

@Alias("JOYCar")
public class JOYCar {
	
	static public int STATE_FREE = 0;
	static public int STATE_BUSY = 1;
	static public int STATE_RESERVE = 2;
	static public int HAS_BT = 1;
	static public int NON_BT = 0;
	
	
	
	public Integer    id;
	public BigDecimal positionX;
	public BigDecimal positionY;
	public Integer     state;
	public String      desp;
	public String      mobileNo;
	public Integer     ifBlueTeeth;
}
