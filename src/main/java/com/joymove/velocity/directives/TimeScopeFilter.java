package com.joymove.velocity.directives;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.beans.Introspector;


/**
 * Created by qurj on 15/6/10.
 */
public class TimeScopeFilter  extends Directive {

    public String getName() { return "TimeScopeFilter"; } //指定指令的名称

    @Override
    public int getType() { return LINE; } //指定指令类型为行指令

    public static Pattern timePattern = Pattern.compile("(min|max)([a-zA-Z]*)(Time|Date)");


    public static  String generateConditons(Map<String,Object> paraMap)
    {
        StringBuffer timeScopeConditions = new StringBuffer();
        Iterator<Map.Entry<String,Object>> iter = paraMap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String,Object> entry = iter.next();
            String name = entry.getKey();
            Matcher timeMatcher = timePattern.matcher(name);

            if(timeMatcher.matches()) {
                String prefix = timeMatcher.group(1);
                String columnName =  name.substring(3);
                columnName = Introspector.decapitalize(columnName);
                Date time = (Date)entry.getValue();
                Long timeValue = time.getTime();
                timeValue = timeValue/1000;
                String conditionStr = "";
                conditionStr = " TIMEDIFF(FROM_UNIXTIME("+timeValue+",'%Y-%m-%d %H:%i:%S'), "+columnName+")";
                if(prefix.equals("min")) {
                    conditionStr += "<=0";
                } else if(prefix.equals("max")) {
                    conditionStr += ">=0";
                }
                conditionStr+=" and";
                timeScopeConditions.append(conditionStr);
            }//if matcher
        }// hasNext
        return  timeScopeConditions.toString();
    }

    /* (non-Javadoc)
    * @see org.apache.velocity.runtime.directive.Directive#render()
    */
    //接收一个Map类型的
    //if has minXXXTime or minXXXDate, then generate  TIMEDIFF(FROM_UNIXTIME("+minXXXTime+",'%Y-%m-%d %H:%i:%S'),xXXXTime) < = 0
    //if has maxXXXTime or maxXXXDate, then generate  TIMEDIFF(FROM_UNIXTIME("+maxXXXTime+",'%Y-%m-%d %H:%i:%S'),xXXXTime) >= 0
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException,
            MethodInvocationException
    {
        System.out.println("hello men");
        Node parameterNode = node.jjtGetChild(0);
        Map<String,Object> paraMap = (Map<String,Object>)parameterNode.value(context);
        String resultString = TimeScopeFilter.generateConditons(paraMap);
        System.out.println(resultString);
        writer.write(resultString);
        return true;
    }




    public static void main(String[] args){
        Map<String,Object> ttt = new HashMap<String, Object>();
        ttt.put("maxCreateTime",new Date(System.currentTimeMillis()));
        ttt.put("minCreateTime",new Date(System.currentTimeMillis()));
        ttt.put("minCreateDate",new Date(System.currentTimeMillis()));
        ttt.put("maxCreateDate",new Date(System.currentTimeMillis()));
        System.out.println(TimeScopeFilter.generateConditons(ttt));


    }
}
