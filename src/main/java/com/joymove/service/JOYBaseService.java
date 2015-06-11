package com.joymove.service;

import com.joymove.dao.JOYBaseDao;
import com.joymove.entity.JOYBase;

import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/6/11.
 */
public interface  JOYBaseService  <E extends JOYBase>  {


    public List<E> getNeededList(E data,Integer start,Integer limit,String order);
    public List<E> getNeededList(E data,Integer start,Integer limit);
    public List<E> getNeededList(E data);
    public E getNeededRecord(E data);
    //void updateRecord(Map<String,Object> filterMap);
    public void updateRecord(E value,E filter);
    void deleteByProperties(E t);
    void insertRecord(E t);




    public JOYBaseDao getBaseDao();
    public  Class<E> getEntityClass();
}
