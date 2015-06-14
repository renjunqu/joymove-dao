package com.joymove.entity;

/**
 * Created by qurj on 15/5/12.
 */
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("JOYPayReqInfo")
public class JOYPayReqInfo extends  JOYBase  {
    public static String tableName = "JOY_PayReqInfo";


    public  static  int type_unknown = 0;
    public static int type_zhifubao = 1;
    public  static int type_wx = 2;

    public JOYPayReqInfo() {
    }

    public Integer id;
    public String out_trade_no;
    public String mobileNo;
    public Double totalFee;
    public Integer payOverFlag;
    public Integer type;
    public Date createTime;
}
