package com.joymove.entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by qurj on 15/5/26.
 */
@Alias("JOYFans")
public class JOYFans  extends  JOYBase {

    public static String tableName = "joy_fans";


    public JOYFans() {
    }

    public Integer id;
    public String mobileNo;
    public String email;
}
