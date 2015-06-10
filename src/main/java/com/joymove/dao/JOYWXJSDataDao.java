package com.joymove.dao;


import com.joymove.entity.JOYWXJSData;

import java.util.List;

/**
 * Created by qurj on 15/5/28.
 */
public interface JOYWXJSDataDao {

    void insertNewWXJSData(JOYWXJSData data);
    List<JOYWXJSData> getNeededWXJSData(JOYWXJSData data);
}
