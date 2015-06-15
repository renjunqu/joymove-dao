package com.joymove.service;

import com.joymove.entity.JOYUser;
import com.joymove.entity.JOYWXJSData;

import java.util.Map;

/**
 * Created by qurj on 15/5/28.
 */
public interface JOYWXJSService  extends  JOYBaseService<JOYWXJSData>  {

    public String getSignature(Map map) throws  Exception;

}
