package com.joymove.service;

import java.util.Map;

/**
 * Created by qurj on 15/5/28.
 */
public interface JOYWXJSService {
     String getWXJSCrendentail(int data_type,String json_key) throws Exception;
     String getSignature(Map map) throws  Exception;

}
