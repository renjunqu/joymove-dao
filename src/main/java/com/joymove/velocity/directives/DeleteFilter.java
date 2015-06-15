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
public class DeleteFilter extends Directive {

    public String getName() { return "DeleteFilter"; } //指定指令的名称

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
        StringBuffer fromTable = new StringBuffer();

        Node parameterNode = node.jjtGetChild(0);

        Object  filterObj = parameterNode.value(context);
        Class  paraClass = filterObj.getClass();


        Field[] fields = paraClass.getFields();
        String fieldName;
        try {
            fromTable.append("delete from ");
            where.append(" where ");
            for(Field f : fields) {

                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    //不使用静态的属性
                    if(f.getName()=="tableName") {
                        String tableName = String.valueOf(f.get(filterObj));
                        fromTable.append(" "+tableName+" ");
                    }
                } else {

                    if (f.get(filterObj)!=null) {

                        fieldName = f.getName();
                        Type f_type = f.getType();

                        if(f_type.equals(String.class)) {
                            where.append(" "+fieldName+" like \'" + f.get(filterObj)+"\' and");

                        } else if(f_type.equals(byte[].class)){
                            BASE64Encoder encoder = new BASE64Encoder();
                            String byteDataStr = encoder.encode((byte[])f.get(filterObj));
                            where.append(" " + fieldName+" = BASE64_DECODE(\'"+byteDataStr+"\') and");
                        } else if(f_type.equals(Date.class)) {
                            Date d = (Date)f.get(filterObj);
                            Long timeValue = d.getTime();
                            timeValue = timeValue/1000;
                            where.append(" "+ fieldName+" = FROM_UNIXTIME("+timeValue+",\'%Y-%m-%d %H:%i:%S\') and");
                        } else {
                            where.append(" " + fieldName + " = " + f.get(filterObj) + " and");
                        }
                    }

                }
            }
            if(where.toString().equals(" where ")) {
                where.delete(0,where.length());
            } else {
                where.delete(where.length()-3,where.length());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        String resultString =  fromTable.toString() + " " + where.toString();
        System.out.println(resultString);
        writer.write(resultString);
        return true;
    }

}
