package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
@Alias("JOYInterPOI")
public class JOYInterPOI  extends  JOYBase {

	public static String tableName = "JOY_InterPOI";


	public JOYInterPOI() {
	}

	//推荐兴趣点
	public Integer id;
	public String title;
	public String name;
	public BigDecimal latitude;
	public BigDecimal longitude;
	
}
