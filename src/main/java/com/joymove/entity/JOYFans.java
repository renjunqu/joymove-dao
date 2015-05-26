package com.joymove.entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by qurj on 15/5/26.
 */
@Alias("JOYFans")
public class JOYFans {
    public Integer id;
    public String mobileNo;
    public String email;
}
