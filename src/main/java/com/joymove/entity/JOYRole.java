package com.joymove.entity;

/**
 * Created by qurj on 15/8/3.
 */
public class JOYRole {
    public Integer id;
    public String desp;
    //这个角色所属的用户组
    //默认为NULL，指全局角色,如果一个用户木有特定用户组的角色，那么它一定拥有一个
    //全局角色
    public Integer groupId;
}
