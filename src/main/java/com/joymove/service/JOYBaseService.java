package com.joymove.service;

import com.joymove.dao.JOYBaseDao;
import com.joymove.entity.JOYBase;
import com.joymove.entity.JOYOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/6/11.
 */
public interface  JOYBaseService  <E extends JOYBase>  {


    public List<E> getNeededList(E data,Integer start,Integer limit,String order);
    public List<E> getNeededList(E data,Integer start,Integer limit);
    public List<E> getNeededList(E data);

    List<Map<String,Object>> getExtendInfoPagedList(String sql,E data,Integer start,Integer limit,String order);
    List<Map<String,Object>> getExtendInfoPagedList(String sql,E data,Integer start,Integer limit);
    List<Map<String,Object>> getExtendInfoPagedList(String sql,E data);

    public List<Map<String,Object>> getListWithTimeScope(E data,Map<String, Object> likeCondition) throws  Exception;

    public List<Map<String,Object>> getListWithTimeScope(Map<String, Object> likeCondition) throws  Exception;

    public long countRecordWithTimeScope(E data,Map<String, Object> likeCondition) throws  Exception;
    public long countRecordWithTimeScope(Map<String, Object> likeCondition) throws  Exception;



    public E getNeededRecord(E data);
    public long countRecord(E data);
    //void updateRecord(Map<String,Object> filterMap);
    public void updateRecord(E value,E filter);
    void deleteByProperties(E t);
    void insertRecord(E t);




    public JOYBaseDao getBaseDao();
    public  Class<E> getEntityClass();
}
