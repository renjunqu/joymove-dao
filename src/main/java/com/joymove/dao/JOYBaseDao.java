package com.joymove.dao;

import com.joymove.entity.JOYBase;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;

import java.util.List;
import java.util.Map;

/**
 * Created by jessie on 2015/6/10.
 */
public interface  JOYBaseDao <E extends JOYBase>{

     List<Map<String,Object>> getPagedRecordList(Map<String,Object> filterMap);

     List<Map<String,Object>> test(Map<String,Object> filterMap);

     List<Map<String,Object>> getExtendInfoPagedList(Map<String,Object> filterMap);

     long countRecord(Map<String,Object> filterMap);
     void deleteByProperties(E t);
     void insertRecord(E t);
     void updateRecord(Map<String,Object> filterMap);
}
