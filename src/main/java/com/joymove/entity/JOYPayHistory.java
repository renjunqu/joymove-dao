package com.joymove.entity;

import java.util.Date;

/**
 * Created by jessie on 2015/6/13.
 */
public class JOYPayHistory extends  JOYBase {

    public static String tableName = "JOY_PayHistory";

    public static int unknown_type = 0;
    public static int zhifubao_type = 1;
    public  static int weixin_type = 2;
    public  static int pay_for_unknown = 0;
    public  static  int pay_for_deposit = 1;
    public  static int pay_for_rent = 2;
    public  Integer id;
    public  Double balance;
    public  Integer type;
    public  Date rentTime;
    public  String mobileNo;
    public  Integer orderId;
    //支付的目的
    public  Integer target;

}
