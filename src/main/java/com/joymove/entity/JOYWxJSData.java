package com.joymove.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by qurj on 15/5/28.
 */
@Alias("JOYWXJSData")
public class JOYWXJSData {
    public static int type_access_token = 1;
    public static int type_jsapi_ticket = 2;
    public Integer id;
    public Integer type;
    public Date expireTime;
    public String detailData;
}
