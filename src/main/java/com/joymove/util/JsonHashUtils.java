package com.joymove.util;

import java.io.BufferedReader;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonHashUtils {

	final static Logger logger = LoggerFactory.getLogger(JsonHashUtils.class);


	public static boolean checkJsonProps(Hashtable<String,Object>hash,String []props){
	    for(int i =0;i<props.length;i++){
	    	
	    	if(hash.containsKey(props[i]) && (String.valueOf(hash.get(props[i]))).length() > 0)
	    		continue;
	    	else
	    		return false;
	    }
		return true;
	}
	
	public static Hashtable<String, Object> strToJSONHash(BufferedReader reader) throws Exception {
		
		 JSONParser parser=new JSONParser();
		 StringBuffer jb = new StringBuffer();
		 String line = null;
		
		 try {
			 while ((line = reader.readLine()) != null)
				  jb.append(line);
			  String jstr = jb.toString();
			  /*
			  logger.trace("show str first .................");
			  logger.trace(jstr);
			  logger.trace("...............................");
			  */
			  Map json = (Map)parser.parse(jstr);
			  Hashtable<String, Object> jsonObj = new Hashtable<String, Object>();
			  Iterator iter = json.entrySet().iterator();
			  //logger.trace("==iterate result==");
			  while(iter.hasNext()){
			      Map.Entry entry = (Map.Entry)iter.next();
			      logger.trace(entry.getKey() + "=>" + entry.getValue());
			      jsonObj.put(entry.getKey().toString(), entry.getValue());
			 }
			 jsonObj.put("originStr", jstr);
			 return jsonObj;
		 } catch(Exception e){
			 throw e;
		 }
	}

	public static void main(String []args) {
		Long t = (long) 1;
		String m = "sdfsdf";
		String q = String.valueOf("sdfsfsd");
	}
}
