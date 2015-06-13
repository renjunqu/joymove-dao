package com.joymove.entity;

import com.joymove.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qurj on 15/6/11.
 */
public class JOYBase {

    public JOYBase(){}

    public String toString()  {

        StringBuffer sb = new StringBuffer();
        Field[] fs = this.getClass().getFields();
        sb.append("{\n");
        try {

            for (Field f : fs) {
                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    // do nothing
                } else if (f.get(this)!=null) {
                    if(f.getType().equals(byte[].class)){
                   //     System.out.println("byte[]");
                        BASE64Encoder encoder = new BASE64Encoder();
                        byte [] byteData = (byte[])f.get(this);
                        sb.append("\t " + f.getName() + ": " + "\"" + encoder.encode(byteData) + "\" ;\n");

                    } else if(f.getType().equals(String.class)) {
                        sb.append("\t " + f.getName() + ": \'" + f.get(this) + "\' ;\n");
                    } else {
                        //   System.out.println("hello "+f.getName());
                        sb.append("\t " + f.getName() + ": " + f.get(this) + " ;\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}\n");
        return sb.toString();
    }


    public void fromMap(Map<String,Object> paraMap){
        Field[] fs = this.getClass().getFields();
        try {
            for (Field f : fs) {
                 if (paraMap.containsKey(f.getName())) {
                     Object data = paraMap.get(f.getName());
                     Class<?> dataC = data.getClass();
                     //mybatis bigdecimal, result double
                     if(dataC.equals(BigDecimal.class) && f.getType().equals(Double.class)) {
                         BigDecimal typedData = (BigDecimal)data;
                         f.set(this,typedData.doubleValue());
                     } else {
                         f.set(this, paraMap.get(f.getName()));
                     }
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  Map<String,Object> toMap(){
        Map<String,Object> reMap = new HashMap<String, Object>();
        Field[] fs = this.getClass().getFields();
        try {
            for (Field f : fs) {
                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    // do nothing
                } else if (f.get(this)!=null) {
                   reMap.put(f.getName(),f.get(this));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reMap;
    }

    public JSONObject toJSON(){
        JSONObject jsonObj = new JSONObject();
        Field[] fs = this.getClass().getFields();
        try {
            for (Field f : fs) {
                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    // do nothing
                } else if (f.get(this)!=null) {
                    jsonObj.put(f.getName(),f.get(this));
                } else {
                   jsonObj.put(f.getName(),"");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jsonObj;
    }

    public void setNull(){

        Field[] fs = this.getClass().getFields();
        try {
            for (Field f : fs) {
                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    // do nothing
                } else  {
                    f.set(this,null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataFilterFromHTTPReq(HttpServletRequest req){
        Field[] fs = this.getClass().getFields();
        try {
            for (Field f : fs) {
                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    // do nothing
                } else  {
                       String queryData =  req.getParameter(f.getName());
                    if(!StringUtils.isBlank(queryData)) {
                        if (f.getType().equals(String.class)) {
                            f.set(this, queryData);
                        } else if (f.getType().equals(Integer.class)) {
                            f.set(this, Integer.parseInt(queryData));
                        } else if (f.getType().equals(Double.class)) {
                            f.set(this, Double.parseDouble(queryData));
                        } else if (f.getType().equals(Date.class)) {
                            f.set(this, Date.parse(queryData));
                        }
                    }//StringUtils
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
