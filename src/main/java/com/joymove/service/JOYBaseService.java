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

    public List<E> getNeededList(Object...args);

    public List<Map<String,Object>> getNeededExtendList(Object...args);



    public E getNeededRecord(E data);
    public long countRecord(Object...args);
    //void updateRecord(Map<String,Object> filterMap);
    public void updateRecord(E value,E filter);
    void deleteByProperties(E t);
    void insertRecord(E t);
    public JOYBaseDao getBaseDao();
    public  Class<E> getEntityClass();
}
