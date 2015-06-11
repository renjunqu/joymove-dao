package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.*;
import java.math.*;
@Alias("JOYPark")
public class JOYPark  extends  JOYBase {
	public static String tableName = "JOY_Park";

	public JOYPark() {
	}

	public Integer    id;
	public BigDecimal positionX;
	public BigDecimal positionY;
	public String      desp;
}
