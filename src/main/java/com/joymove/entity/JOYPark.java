package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.*;
import java.math.*;
@Alias("JOYPark")
public class JOYPark {
	
	public Integer    id;
	public BigDecimal positionX;
	public BigDecimal positionY;
	public String      desp;
}
