package com.joymove.util;

import com.joymove.entity.JOYNCar;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by jessie on 2015/6/6.
 */
public class SimpleJSONUtil {
    public static JSONObject beanToJSONObject(Object beanObject) throws  Exception {
        JSONObject Reobj = new JSONObject();
        Field[] fields = beanObject.getClass().getDeclaredFields();
        for(Field f : fields) {
              if(java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                  //不使用静态的属性
              } else {
                    f.setAccessible(true);
                    if(f.get(beanObject)!=null) {
                      Reobj.put(f.getName(),f.get(beanObject));
                    }
              }
        }
        return Reobj;
    }
    public static JSONArray listToJSONArray(List beanList) throws Exception {
        JSONArray reArray = new JSONArray();
        if(beanList.size()>0) {
            for(int i=0;i<beanList.size();i++)
                reArray.add(SimpleJSONUtil.beanToJSONObject(beanList.get(i)));
        }
        return reArray;
    }

    public static JSONObject fromMap(Map<String,Object> map) {
        JSONObject json = new JSONObject();
        Iterator iter =map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String,Object> mapEntry  = (Map.Entry<String,Object>)iter.next();
            if(String.valueOf(mapEntry.getValue()).equals("null")||mapEntry.getValue()==null) {
                json.put(mapEntry.getKey(),"");
            } else {
                json.put(mapEntry.getKey(),mapEntry.getValue());
            }
        }
        return  json;
    }

    public static void main(String [] args) {
        List<JOYNCar> ncarList = new ArrayList<JOYNCar>();
        JOYNCar ncar = new JOYNCar();
        ncar.vinNum = "sdfdsf";
        ncarList.add(ncar);
        ncar = new JOYNCar();
        ncar.vinNum = "testsetset";
        ncarList.add(ncar);

        try {
            JSONObject ttt = new JSONObject();
           ttt.put("root",SimpleJSONUtil.listToJSONArray(ncarList));
            System.out.println(ttt);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
