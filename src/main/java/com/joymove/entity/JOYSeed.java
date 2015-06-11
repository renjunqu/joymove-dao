package com.joymove.entity;


import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;
/**
 * Created by figoxu on 15/5/5.
 */
@Alias("JOYSeed")
public class JOYSeed  extends  JOYBase {
    public static String tableName = "JOY_Seed";

    public JOYSeed() {
    }

    public static int type_coupon_seed = 0;// coupon seed
    public static int status_seed_alive = 0;//this seed could be used
    public static int status_seed_used = 1;//this seed has alreay be used

    public Integer id;
    public String mobileNo;
    public String code;
    public BigDecimal balance;
    public Date createTime;
    public Integer status;
    public Integer type;

}
