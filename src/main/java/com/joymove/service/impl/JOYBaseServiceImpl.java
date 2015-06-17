package com.joymove.service.impl;

import com.joymove.dao.JOYBaseDao;
import com.joymove.entity.JOYBase;
import com.joymove.entity.JOYOrder;
import com.joymove.entity.JOYUser;
import com.joymove.service.JOYBaseService;
import org.bouncycastle.crypto.params.KeyParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qurj on 15/6/11.
 */
public abstract class JOYBaseServiceImpl<E extends  JOYBase> implements JOYBaseService<E> {


    final static Logger logger = LoggerFactory.getLogger(JOYBaseServiceImpl.class);


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








    public E getNeededRecord(E data) {
        E reObj = null;
        try {
            List<E> reList = null;
            Class<E>  entityClass = this.getEntityClass();
            JOYBaseDao dao  = this.getBaseDao();
          //  logger.trace("hello E " + entityClass.getName());
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


    public  void  parseIntArg(List<Object> intArgs,Map<String,Object> paraMap) {
        int size = intArgs.size();
        if(size==0) {
            return;
        } else if(size==2) {
            paraMap.put("start",intArgs.get(1));
            paraMap.put("limit",10);
        } else if(size==4) {
             paraMap.put("start",intArgs.get(1));
             paraMap.put("limit",intArgs.get(3));
        }
    }

    public  void  parseStrArg(List<Object> strArgs,Map<String,Object> paraMap) {
        int size = strArgs.size();
        if(size==0) {
            return;
        } else if(size==2) {
            int order = (Integer)strArgs.get(0);
            if(order==0) {
                paraMap.put("sql", strArgs.get(1));
            } else {
                paraMap.put("order", strArgs.get(1));
            }
        } else if(size==4) {
            paraMap.put("sql",strArgs.get(1));
            paraMap.put("order",strArgs.get(3));
        }

    }

    public  void  parseEntityArg(List<Object> entityArgs,Map<String,Object> paraMap) {
        int size = entityArgs.size();
        if(size==0) {
            return;
        } else if(size==2) {
            paraMap.put("filter",entityArgs.get(1));
        } else if(size==4) {
            paraMap.put("filter",entityArgs.get(1));
            paraMap.put("minFilter",entityArgs.get(3));
        } else if(size==6) {
            paraMap.put("filter",entityArgs.get(1));
            paraMap.put("minFilter",entityArgs.get(3));
            paraMap.put("maxFilter",entityArgs.get(5));
        }

    }

    public Map<String,Object> parseArgs(Object...args) {

        List<Object> strArgs    = new ArrayList<Object>();
        List<Object> intArgs    = new ArrayList<Object>();
        List<Object> entityArgs = new ArrayList<Object>();
        Map<String,Object> paraMap = new HashMap<String, Object>();

        for(int i=0;i<args.length;i++) {
            Object argObj = args[i];

            if(argObj.getClass().equals(Integer.class)) {
                intArgs.add(i);
                intArgs.add(argObj);
            } else if(argObj.getClass().equals(String.class)) {
                strArgs.add(i);
                strArgs.add(argObj);
            } else if(argObj.getClass().equals(this.getEntityClass())) {
                entityArgs.add(i);
                entityArgs.add(argObj);
            } else if(argObj instanceof  Map) {
                paraMap.putAll((Map<String,Object>)argObj);
            }
        }

        parseEntityArg(entityArgs, paraMap);
        parseIntArg(intArgs, paraMap);
        parseStrArg(strArgs, paraMap);
        return paraMap;
    }

    //动态个数参数
    public List<E> getNeededList(Object...args) {
        List<E> reList = new ArrayList<E>();
        JOYBaseDao dao = this.getBaseDao();
        Class<E> entityClass = this.getEntityClass();
        try {
            List<Map<String, Object>> reMapList = dao.getPagedRecordList(parseArgs(args));
            //     logger.trace("list length is "+reMapList.size());
            if (reMapList.size() > 0) {
                for (int i = 0; i < reMapList.size(); i++) {
                    E entity = entityClass.newInstance();
                    entity.fromMap(reMapList.get(i));
                    reList.add(entity);
                }
            }
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return reList;
    }

    public List<Map<String,Object>> getNeededExtendList(Object...args) {
        JOYBaseDao dao = this.getBaseDao();
        return  dao.getExtendInfoPagedList(parseArgs(args));
            //     logger.trace("list length is "+reMapList.size());
    }

    public long countRecord(Object...args) {
        JOYBaseDao dao = this.getBaseDao();
        return  dao.countRecord(parseArgs(args));
        //     logger.trace("list length is "+reMapList.size());
    }
}
