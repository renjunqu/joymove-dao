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
import java.util.Date;
import java.util.Map;

/**
 * Created by qurj on 15/6/10.
 */

//记得在前面加上用户自己的select 语句， (一般是left join、right join 以及 inner join 语句)
//记得，一定要将主表的名字 alias 为u
public class SelectExtendInfoPagedList extends Directive {


    public String getName() { return "SelectExtendInfoPagedList"; } //指定指令的名称

    @Override
    public int getType() { return LINE; } //指定指令类型为行指令

    /* (non-Javadoc)
    * @see org.apache.velocity.runtime.directive.Directive#render()
    */
    //接收一个Map类型的 {三个key, 第一个是Entity,第二个是}
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException,
            MethodInvocationException
    {

        Class paraClass;
        String sql = "";
        StringBuffer where = new StringBuffer();
        StringBuffer limit = new StringBuffer();
        String rangeOrder = "order by u.id";

        Node parameterNode = node.jjtGetChild(0);

        Map<String,Object> paraMap = (Map<String,Object>)parameterNode.value(context);

        sql = String.valueOf(paraMap.get("sql"));
        Object  filterObj = paraMap.get("filter");
        String TimeCondtions = TimeScopeFilter.generateConditons(paraMap);



        paraClass = filterObj.getClass();

        if(paraMap.containsKey("start")&& paraMap.containsKey("limit")) {
            limit.append(" limit " + paraMap.get("start")+" , "+paraMap.get("limit"));
        }

        if(paraMap.containsKey("order")) {
            rangeOrder  += " " + String.valueOf(paraMap.get("order"));
        } else {
            rangeOrder += " ASC";
        }


        Field[] fields = paraClass.getFields();
        String fieldName;
        try {

            where.append(" where ");
            for(Field f : fields) {

                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    //不使用静态的属性
                } else {

                    if (f.get(filterObj)!=null) {

                        fieldName = f.getName();
                        Type f_type = f.getType();

                        if(f_type.equals(String.class)) {

                            where.append(" u."+fieldName+" like \'" + f.get(filterObj)+"\' and");

                        } else if(f_type.equals(byte[].class)){
                            BASE64Encoder encoder = new BASE64Encoder();
                            String byteDataStr = encoder.encode((byte[])f.get(filterObj));
                            where.append(" u." + fieldName+" = BASE64_DECODE(\'"+byteDataStr+"\') and");
                        } else if(f_type.equals(Date.class)) {
                            Date d = (Date)f.get(filterObj);
                            Long timeValue = d.getTime();
                            timeValue = timeValue/1000;
                            where.append(" u."+ fieldName+" = FROM_UNIXTIME("+timeValue+",\'%Y-%m-%d %H:%i:%S\') and");
                        }else {
                            where.append(" u."+fieldName +" = "+f.get(filterObj)+" and");
                        }
                    }

                }
            }
            where.append(TimeCondtions);
            if(where.toString().equals(" where ")) {
                where.delete(0,where.length());
            } else {
                where.delete(where.length()-3,where.length());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        String resultString = sql + " " + where.toString() + " group by u.id "+rangeOrder+limit.toString();
        System.out.println(resultString);
        writer.write(resultString);
        return true;
    }

}
