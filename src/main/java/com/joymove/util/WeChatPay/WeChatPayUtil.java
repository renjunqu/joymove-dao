package com.joymove.util.WeChatPay;

import java.util.List;
import java.util.LinkedList;

import com.futuremove.cacheServer.utils.HttpPostUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Created by qurj on 15/5/12.
 */
public class WeChatPayUtil {

    final static Logger logger = LoggerFactory.getLogger(WeChatPayUtil.class);



    static public String  API_KEY="8RO2EAEXIIWFPENGQ5J9UESAZXC86P82";
    static public String MCH_ID="1238525102";
    static public String APP_ID="wxd8941e99e565b990";

    public static String getSignStr(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(WeChatPayUtil.API_KEY);


        return  MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
    }

    public static String getXmlElement(String eleName, String xml) {
      try {
          Pattern p1 = Pattern.compile("<" + eleName + "><!\\[CDATA\\[(.*)\\]\\]></" + eleName + ">");
          Matcher m1 = p1.matcher(xml);
          Pattern p2 = Pattern.compile("<" + eleName + ">(.*)</" + eleName + ">");
          Matcher m2 = p2.matcher(xml);

          if(m1.find())
            return m1.group(1);
          else if(m2.find())
              return m2.group(1);
          else
              return null;
          //  Matcher m = p.matcher("aaaaab");
      } catch(Exception e) {
          e.printStackTrace();
      }
        return null;

    }
    public static  String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");


            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");

        return sb.toString();
    }

    public static  String toJsonStr(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < params.size(); i++) {
            sb.append("\""+params.get(i).getName()+"\":");
            sb.append("\""+params.get(i).getValue()+"\"");
            if(i!=params.size()-1)
            sb.append(",");
        }
        sb.append("}");


        return sb.toString();
    }

    public static  String toJsonObj(List<NameValuePair> params) {
        /*


        return sb.toString();
       */
        return null;
    }




    public static  String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }


    public static String genePayStr(String total_fee,String trade_no){

        String nonceStr = WeChatPayUtil.genNonceStr();
        String spbill_create_ip = "123.57.151.176";
        String notify_url = "http://123.57.151.176:8082/joymove/wechat/notify.c";


        List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
        packageParams.add(new BasicNameValuePair("appid", WeChatPayUtil.APP_ID));
        packageParams.add(new BasicNameValuePair("body", "weixin"));
        packageParams.add(new BasicNameValuePair("mch_id", WeChatPayUtil.MCH_ID));
        packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
        packageParams.add(new BasicNameValuePair("notify_url", notify_url));
        packageParams.add(new BasicNameValuePair("out_trade_no",trade_no));
        packageParams.add(new BasicNameValuePair("spbill_create_ip",spbill_create_ip));
        packageParams.add(new BasicNameValuePair("total_fee", total_fee));
        packageParams.add(new BasicNameValuePair("trade_type", "APP"));
        String sign = getSignStr(packageParams);
        packageParams.add(new BasicNameValuePair("sign", sign));
        String xmlstring =toXml(packageParams);
        String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");

        String res = HttpPostUtils.post(url, xmlstring);
        String prepayid = WeChatPayUtil.getXmlElement("prepay_id", res);
        if(prepayid==null)
            return null;


        List<NameValuePair> payparams = new LinkedList<NameValuePair>();
        payparams.add(new BasicNameValuePair("appid", WeChatPayUtil.APP_ID));
        payparams.add(new BasicNameValuePair("noncestr", nonceStr));
        payparams.add(new BasicNameValuePair("package", "Sign=WXPay"));
        payparams.add(new BasicNameValuePair("partnerid",WeChatPayUtil.MCH_ID));
        payparams.add(new BasicNameValuePair("prepayid", prepayid));
        payparams.add(new BasicNameValuePair("timestamp", String.valueOf(System.currentTimeMillis()).substring(0,10)));
        String sign2 = getSignStr(payparams);
        payparams.add(new BasicNameValuePair("sign", sign2));
       // logger.trace(WeChatPayUtil.toJsonStr(payparams));
       return WeChatPayUtil.toJsonStr(payparams);
    }


    public static void main(String [] args){
       String reStr = genePayStr("1","ttt1");
        logger.trace(reStr);
    }





}
