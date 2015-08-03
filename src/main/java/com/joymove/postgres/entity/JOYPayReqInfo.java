package com.joymove.postgres.entity;

import java.util.Date;

/**
 * Created by qurj on 15/8/3.
 */
public class JOYPayReqInfo extends  JOYBase {
    public  static  int type_unknown = 0;
    public static int type_zhifubao = 1;
    public  static int type_wx = 2;



    public Long id;
    public String out_trade_no;
    public String mobileNo;
    public Double totalFee;
    public Integer payOverFlag;
    public Integer type;
    public Date createTime;
}
