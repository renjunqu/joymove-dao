package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
@Alias("JOYPowerBar")
public class JOYPowerBar  extends  JOYBase {
	public static String tableName = "JOY_PowerBar";

	public JOYPowerBar() {
	}

	public Integer    id;
	public BigDecimal positionX;
	public BigDecimal positionY;
	public String      desp;
}
