package com.joymove.util.BaiduOCR;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.mongodb.util.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/***
 * 调用百度OCR服务
 * @author wx
 *
 */
public class BaiduOCRUtil {

	public static String APIKEY = "00438d10416bb4e54d14148a769efdda";
	public static String httpUrl = "http://apis.baidu.com/apistore/idlocr/ocr";


	static public String ocr(File imageFile) {
		JSONObject resultObj = new JSONObject();
		//resultObj.put("retData", new JSONArray());
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("apikey", APIKEY);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("image", imageFile);
			params.put("imagetype", 2);
			params.put("languagetype", "CHN_ENG");
			params.put("detecttype", "LocateRecognize");
			params.put("clientip", "123.57.151.176");
			params.put("fromdevice", "pc");

			String result = UnicodeTool.decodeUnicode(HttpUtil.uploadFile(httpUrl,
					headers, params));
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(result);
			if(jsonObject.containsKey("retData")){
				resultObj.put("retData", jsonObject.get("retData"));
			}
			resultObj.put("ret", "0");
		} catch (Exception e) {
			resultObj.put("ret", "1");
			e.printStackTrace();
		}
		JSONObject xx = getIDPhoto((JSONArray) resultObj.get("retData"),imageFile);
		return resultObj.toJSONString();
	}


	static public JSONObject ocr(String base64Str) {
		JSONObject resultObj = new JSONObject();
		resultObj.put("retData", new JSONArray());
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("apikey", APIKEY);

			Map<String, String> params = new HashMap<String, String>();

			params.put("image", base64Str);
			params.put("imagetype", 1+"");
			params.put("languagetype", "CHN_ENG");
			params.put("detecttype", "LocateRecognize");
			params.put("clientip", "123.57.151.176");
			params.put("fromdevice", "pc");

			String result = UnicodeTool.decodeUnicode(HttpUtil.post(httpUrl, headers, params));
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(result);
			Integer errNum = Integer.parseInt(String.valueOf(jsonObject.get("errNum")));
			if(errNum==0) {
				resultObj.put("result","10000");
				//resultObj.put("retData",jsonObject.get("retData"));
				JSONArray rectDatas = (JSONArray)jsonObject.get("retData");
                resultObj.put("name","--未识别成功--");
				resultObj.put("id","--未识别成功--");
				for(Object rect : rectDatas) {
					JSONObject rectObj = (JSONObject) rect;
					int index = -1;
					String word = (String) rectObj.get("word");
					if ((index = word.indexOf("姓名")) != -1) {
						resultObj.put("name", word.substring(index + 2));
					}
					if ((index = word.indexOf("公民身份号码")) != -1) {
						resultObj.put("id", word.substring(index+6));
					}
				}
			} else {
				resultObj.put("result","10001");
				resultObj.put("errMsg",jsonObject.get("errMsg"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultObj.put("errMsg",e.toString());
			resultObj.put("result", "10001");
		}
		return resultObj;
	}

	/**
	 * 获取身份证头像
	 * @param
	 * @return
	 */
	static public  JSONObject getIDPhoto(JSONArray rectDatas, File srcImgFile){
		JSONObject resultObj = new JSONObject();
		int top =0, left = 0, bottom = 0, right = 0;
		for(Object rect : rectDatas){
			JSONObject rectObj = (JSONObject) rect;
			int index = -1;
			String word =(String)rectObj.get("word");
			if((index = word.indexOf("姓名"))!= -1){
				resultObj.put("name", word.substring(index+2));
				top = Integer.parseInt((String) ((JSONObject)rectObj.get("rect")).get("top"));
			}
			if((index = word.indexOf("姓别"))!= -1){
				resultObj.put("sex", word.substring(index+2,index+3));
			}
			if((index = word.indexOf("民族"))!= -1){
				resultObj.put("nation", word.substring(index+2));
			}
			if((index = word.indexOf("出生"))!= -1){
				resultObj.put("birth", word.substring(index+2));
			}
			if((index = word.indexOf("公民身份号码"))!= -1){
				resultObj.put("id", word.substring(index+6));
				bottom = (int) (Integer.parseInt((String) ((JSONObject)rectObj.get("rect")).get("top"))*0.9);
				int tempLeft = Integer.parseInt((String) ((JSONObject)rectObj.get("rect")).get("left"));
				int tempHeight = Integer.parseInt((String) ((JSONObject)rectObj.get("rect")).get("height"));
				int tempW = Integer.parseInt((String) ((JSONObject)rectObj.get("rect")).get("width"));
				right = (int) (1.5 * tempHeight + tempW + tempLeft);
				left = (int) (right - (bottom - top)/10.0 * 9);
				try {
					BufferedImage srcImg = ImageIO.read(srcImgFile);
					BufferedImage targetImg = srcImg.getSubimage(left, top, right-left, bottom- top);
					File target = new File(srcImgFile.getParent(), "_"+srcImgFile.getName());
					ImageIO.write(targetImg, "jpg", target);
					resultObj.put("photo", target);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}


		return resultObj;
	}

}
