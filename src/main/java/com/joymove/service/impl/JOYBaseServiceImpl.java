package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.entity.JOYBase;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYBaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/6/11.
 */
public abstract class JOYBaseServiceImpl<E extends  JOYBase> implements JOYBaseService<E> {

    public JOYBaseServiceImpl() {

    }

    public  void insertRecord(E t) {
        JOYBaseDao dao = this.getBaseDao();
        dao.insertRecord(t);
    }

    public  void deleteByProperties(E t) {
        JOYBaseDao dao  = this.getBaseDao();
        dao.deleteByProperties(t);
    }


    public List<E> getNeededList(E data,Integer start,Integer limit, String order) {
        List<E> reList = new ArrayList<E>();
        try {
            Class<E>  entityClass = this.getEntityClass();
            JOYBaseDao dao  = this.getBaseDao();
          //  System.out.println("hello E " + entityClass.getName());
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("filter",data);
            if(start!=null)
                dataMap.put("start",start);
            if(limit!=null)
                dataMap.put("limit",limit);
            if(order!=null)
                dataMap.put("order",order);

            List<Map<String, Object>> reMapList = dao.getPagedRecordList(dataMap);
       //     System.out.println("list length is "+reMapList.size());
            if(reMapList.size()>0) {
                for(int i=0;i<reMapList.size();i++) {
                    E entity = entityClass.newInstance();
                    entity.fromMap(reMapList.get(i));
                    reList.add(entity);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        return reList;
    }

    public long countRecord(E data){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("filter",data);
        JOYBaseDao dao  = this.getBaseDao();
        return dao.countRecord(dataMap);
    }



    public List<E> getNeededList(E data,Integer start,Integer limit) {
        return this.getNeededList(data,start,limit,null);
    }

    public List<E> getNeededList(E data) {
        return getNeededList(data,null,null);
    }

    public List<Map<String,Object>> getExtendInfoPagedList(String sql,E data,Integer start,Integer limit,String order) {
        List<Map<String,Object>>  reMapList = new ArrayList<Map<String,Object>>();
        try {
            Class<E>  entityClass = this.getEntityClass();
            JOYBaseDao dao  = this.getBaseDao();
            //  System.out.println("hello E " + entityClass.getName());
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("sql",sql);
            dataMap.put("filter",data);
            if(start!=null)
                dataMap.put("start",start);
            if(limit!=null)
                dataMap.put("limit",limit);
            if(order!=null)
                dataMap.put("order",order);
            reMapList = dao.getExtendInfoPagedList(dataMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reMapList;
    }

    public List<Map<String,Object>> getListWithTimeScope(E dataFilter,Map<String, Object> likeCondition) throws Exception {
        E dFillter = dataFilter;
        if(dataFilter==null) {
            dFillter = this.getEntityClass().newInstance();
        }
        likeCondition.put("filter",dFillter);
        JOYBaseDao dao = this.getBaseDao();
        return dao.getPagedRecordList(likeCondition);
    }

    public   List<Map<String,Object>> getListWithTimeScope(Map<String, Object> likeCondition) throws  Exception {
        return this.getListWithTimeScope(null, likeCondition);
    }

    public long countRecordWithTimeScope(E dataFilter,Map<String, Object> likeCondition) throws  Exception {
        E dFillter = dataFilter;
        if(dataFilter==null) {
            dFillter = this.getEntityClass().newInstance();
        }
        likeCondition.put("filter",dFillter);
        JOYBaseDao dao = this.getBaseDao();
        return dao.countRecord(likeCondition);
    }

    public long countRecordWithTimeScope(Map<String, Object> likeCondition) throws  Exception {
           return this.countRecordWithTimeScope(null,likeCondition);
    }





    public List<Map<String,Object>> getExtendInfoPagedList(String sql,E data,Integer start,Integer limit){
           return this.getExtendInfoPagedList(sql,data,start,limit,null);
    }
    public List<Map<String,Object>> getExtendInfoPagedList(String sql,E data){
        return this.getExtendInfoPagedList(sql,data,null,null,null);
    }


    public E getNeededRecord(E data) {
        E reObj = null;
        try {
            List<E> reList = null;
            Class<E>  entityClass = this.getEntityClass();
            JOYBaseDao dao  = this.getBaseDao();
          //  System.out.println("hello E " + entityClass.getName());
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("filter",data);
            List<Map<String, Object>> reMapList = dao.getPagedRecordList(dataMap);
            if(reMapList.size()>0) {
                reObj = this.getEntityClass().newInstance();
                reObj.fromMap(reMapList.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        return reObj;
    }

    //void updateRecord(Map<String,Object> filterMap);
    public void updateRecord(E value,E filter) {
        try {
            Class<E>  entityClass = this.getEntityClass();
            JOYBaseDao dao  = this.getBaseDao();
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("value",value);
            if(filter!=null)
                dataMap.put("filter",filter);
            dao.updateRecord(dataMap);
        }
        catch (Exception e) {
            e.printStackTrace();

        }


    }




}
