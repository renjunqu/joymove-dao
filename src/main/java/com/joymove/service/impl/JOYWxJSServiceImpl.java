package com.joymove.service.impl;

import com.futuremove.cacheServer.utils.HttpGetUtils;
import com.joymove.dao.JOYWXJSDataDao;
import com.joymove.entity.JOYWXJSData;
import com.joymove.service.JOYWXJSService;
import com.joymove.util.DigestUtil;
import com.joymove.util.WeChatPay.MD5;
import com.mongodb.util.JSON;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by qurj on 15/5/28.
 */
@Service("JOYWXJSService")
public class JOYWXJSServiceImpl implements JOYWXJSService {

    final static Logger logger = LoggerFactory.getLogger(JOYWXJSServiceImpl.class);

    @Autowired
    private JOYWXJSDataDao joywxjsDataDao;

    public static String  appid = "wx0a6bcdeb9d86bdff";
    public static String secret = "5f44ddfba3c75f53c1ef82edc2c910c4";
    public  static JSONObject getAccessTokenFromWX() {
        try {
            String getURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                    + JOYWXJSServiceImpl.appid + "&secret=" + JOYWXJSServiceImpl.secret;
            logger.trace("getURL: " + getURL);
            String retJStr = HttpGetUtils.get(getURL);
            logger.trace("retJStr: " + retJStr);
            JSONParser parser = new JSONParser();
            JSONObject jobj = (JSONObject)parser.parse(retJStr);
            String access_token = String.valueOf(jobj.get("access_token"));
            String expires_in   = String.valueOf(jobj.get("expires_in"));
            logger.trace("access_token: "+access_token );
            logger.trace("expires_in: " + expires_in);
            if(access_token!= null && expires_in != null)
                return jobj;
        } catch(Exception e) {
            e.toString();
        }
        return null;
    }

    public  static JSONObject getJSAPITicketFromWX(String access_token) {
        try {
            String getURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
            String retJStr = HttpGetUtils.get(getURL);
            JSONParser parser = new JSONParser();
            JSONObject jobj = (JSONObject)parser.parse(retJStr);
            Integer errcode = Integer.parseInt(String.valueOf(jobj.get("errcode")));
            if(errcode==0) {
                return jobj;
            }

        } catch(Exception e) {
            e.toString();
        }
        return null;
    }

    public static String getWaitSignStr(Map map) {
        String result = "";
        Iterator iter =  map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            result += key +"=" + val + "&";
        }
        return result.substring(0,result.length()-1);
    }

    public static String getWXJSSignature(String ticket,Map <String,Object>toSignData){

        Map toSignMap = new TreeMap<String,Object>();
        toSignMap.putAll(toSignData);
        toSignMap.put("jsapi_ticket",ticket);
        String waitSign = JOYWXJSServiceImpl.getWaitSignStr(toSignMap);
        String result = DigestUtil.encode("SHA1", waitSign);
        return result;
    }





    public String getWXJSCrendentail(int data_type,String json_key) throws Exception {
        JOYWXJSData dataRequirement = new JOYWXJSData();
        dataRequirement.type = data_type;
        dataRequirement.expireTime = new Date(System.currentTimeMillis());
        List<JOYWXJSData> dataList = joywxjsDataDao.getNeededWXJSData(dataRequirement);
        if(dataList.size()==0) {
            JSONObject getFromWX = null;
            if(data_type==JOYWXJSData.type_access_token) {
                getFromWX = JOYWXJSServiceImpl.getAccessTokenFromWX();
            } else if(data_type==JOYWXJSData.type_jsapi_ticket) {
                String accessToken_pre = this.getWXJSCrendentail(JOYWXJSData.type_access_token,"access_token");
                if(accessToken_pre != null)
                    getFromWX = JOYWXJSServiceImpl.getJSAPITicketFromWX(accessToken_pre);
            }
            if(getFromWX!=null) {
                JOYWXJSData toSaveToken = new JOYWXJSData();
                toSaveToken.type = data_type;
                toSaveToken.expireTime = new Date(System.currentTimeMillis() +
                        Long.parseLong(String.valueOf(getFromWX.get("expires_in")))*1000);
                toSaveToken.detailData = getFromWX.toJSONString();
                joywxjsDataDao.insertNewWXJSData(toSaveToken);
                return String.valueOf(getFromWX.get(json_key));
            }
        } else {
            JOYWXJSData tokenJson = dataList.get(0);
            JSONParser parser = new JSONParser();
            JSONObject tokenJobj = (JSONObject)parser.parse(tokenJson.detailData);
            return String.valueOf(tokenJobj.get(json_key));
        }
        return null;
    }




    public String getSignature(Map map) throws  Exception{
        return JOYWXJSServiceImpl.getWXJSSignature(
                this.getWXJSCrendentail(JOYWXJSData.type_jsapi_ticket, "ticket"),
                map
                );

    }

    public static void main(String [] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:**/test.xml");
            System.out.println("get context over");
            JOYWXJSService wxJSService = (JOYWXJSService) context.getBean("JOYWXJSService");
            String ticket = wxJSService.getWXJSCrendentail(JOYWXJSData.type_jsapi_ticket, "ticket");
            System.out.println("get access token over, at is "+ticket);
            Map map = new JSONObject();
            map.put("noncestr","Wm3WZYTPz0wzccnW");
            //jsapi_ticket=
            // sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg
            map.put("timestamp","1414587457");
            map.put("url","http://mp.weixin.qq.com?params=value");
            System.out.println(wxJSService.getSignature(map));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
