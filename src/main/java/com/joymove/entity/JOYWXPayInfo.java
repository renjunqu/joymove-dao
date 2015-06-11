package com.joymove.entity;

/**
 * Created by qurj on 15/5/12.
 */
import org.apache.ibatis.type.Alias;

@Alias("JOYWXPayInfo")
public class JOYWXPayInfo extends  JOYBase  {
    public static String tableName = "JOY_WXPayInfo";

    public JOYWXPayInfo() {
    }

    public Integer id;
    public String out_trade_no;
    public String mobileNo;
    public Double totalFee;
    public Integer payOverFlag;



}
