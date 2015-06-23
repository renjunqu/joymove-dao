package com.joymove.entity;

import java.util.Date;

/**
 * Created by qurj on 15/6/23.
 */
public class JOYCarCluster extends  JOYBase {

    public static String tableName = "JOY_CarCluster";

    public Integer id;
    public Double latitude;
    public Double longitude;
    public String desp;
    public Date createDate;
}
