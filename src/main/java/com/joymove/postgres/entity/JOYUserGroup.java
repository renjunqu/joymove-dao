package com.joymove.postgres.entity;

/**
 * Created by qurj on 15/8/3.
 */
public class JOYUserGroup  extends  JOYBase {
    public Long id;
    public String name;
    //0 -- > 公司, 1 -- > 部门
    public Integer type;
    //这个是一个外键, 指它的上级部门，如果是NULL就是木有上级
    public Integer parent;
}
