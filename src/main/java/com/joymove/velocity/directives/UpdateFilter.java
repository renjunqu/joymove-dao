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
public class UpdateFilter  extends Directive {

    public String getName() { return "UpdateFilter"; } //指定指令的名称

    @Override
    public int getType() { return LINE; } //指定指令类型为行指令

    /* (non-Javadoc)
    * @see org.apache.velocity.runtime.directive.Directive#render()
    */
    //接收一个Map类型的 {第一个是Entity}
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException,
            MethodInvocationException
    {

        StringBuffer where = new StringBuffer();
        StringBuffer values = new StringBuffer();
        String prefix = "update ";
        where.append(" where ");
        Node parameterNode = node.jjtGetChild(0);

        Map<String,Object> paraMap = (Map<String,Object>)parameterNode.value(context);




        Object  valueObj = paraMap.get("value");
        Class  paraClass = valueObj.getClass();

        Object filterObj = null;
        Field[] fields = paraClass.getFields();


        if(paraMap.containsKey("filter")) {
            filterObj = paraMap.get("filter");
        }

        String fieldName;
        try {

            for(Field f : fields) {

                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    //不使用静态的属性
                    if(f.getName()=="tableName") {
                        String tableName = String.valueOf(f.get(filterObj));
                        prefix+=tableName+"  set ";
                    }
                } else {
                    fieldName = f.getName();
                    Type f_type = f.getType();
                    //generates o1 = v1

                        if(f_type.equals(String.class)) {

                            if(f.get(valueObj)!=null)
                               values.append(" "+fieldName+" = \'" + f.get(valueObj)+"\' ,");
                            if(filterObj!=null && f.get(filterObj)!=null)
                                where.append(" "+fieldName+" like \'" + f.get(filterObj)+"\' and");

                        } else if(f_type.equals(byte[].class)){
                            BASE64Encoder encoder = new BASE64Encoder();
                            if(f.get(valueObj)!=null) {
                                String byteDataStr = encoder.encode((byte[]) f.get(valueObj));
                                values.append(" " + fieldName + " = BASE64_DECODE(\'" + byteDataStr + "\') ,");
                            }
                            if(filterObj!=null && f.get(filterObj)!=null) {
                                String byteDataStr = encoder.encode((byte[])f.get(filterObj));
                                where.append(" " + fieldName+" = BASE64_DECODE(\'"+byteDataStr+"\') and");
                            }
                        } else if(f_type.equals(Date.class)) {
                            if(f.get(valueObj)!=null) {
                                Date d = (Date)f.get(valueObj);
                                Long timeValue = d.getTime();
                                timeValue = timeValue/1000;
                                values.append(" " + fieldName + " = FROM_UNIXTIME(" +timeValue + ",\'%Y-%m-%d %H:%i:%S\') ,");
                            }
                            if(filterObj!=null && f.get(filterObj)!=null) {
                                Date d = (Date)f.get(filterObj);
                                Long timeValue = d.getTime();
                                timeValue = timeValue/1000;
                                where.append(" " + fieldName + " = FROM_UNIXTIME(" +timeValue + ",\'%Y-%m-%d %H:%i:%S\') and");
                            }
                        }else {
                            if(f.get(valueObj)!=null)
                                values.append(" " + fieldName + " = " + f.get(valueObj) + " ,");
                            if(filterObj!=null && f.get(filterObj)!=null)
                                where.append(" " + fieldName + " = " + f.get(filterObj) + " and");
                        }



                }
            }
            System.out.println("where is "+where);
            System.out.println("values is "+values);
            if(where.toString().equals(" where ")) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
                where.delete(0,where.length());
            } else {
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<");
                where.delete(where.length()-3,where.length());
            }

            values.delete(values.length() - 1, values.length());



        } catch (Exception e) {
            e.printStackTrace();
        }
        String resultString =  prefix + values.toString() + " " + where.toString();
        System.out.println(resultString);
        writer.write(resultString);
        return true;
    }

}
