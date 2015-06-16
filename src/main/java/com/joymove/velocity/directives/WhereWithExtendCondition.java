package com.joymove.velocity.directives;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Date;
import java.util.Map;

/**
 * Created by jessie on 2015/6/14.
 */
//whereprefix 在这个字段加上用户需要的查询条件~,记得在前面加上用户自己的select 语句，
    //记得，一定要将主表的名字 alias 为u
public class WhereWithExtendCondition  extends Directive  {

    final static Logger logger = LoggerFactory.getLogger(WhereWithExtendCondition.class);



    public String getName() { return "WhereWithExtendCondition"; } //指定指令的名称

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
        StringBuffer where = new StringBuffer();


        Node parameterNode = node.jjtGetChild(0);

        Map<String,Object> paraMap = (Map<String,Object>)parameterNode.value(context);

        String TimeConditions = TimeScopeFilter.generateConditons(paraMap);
        Object  filterObj = paraMap.get("filter");

        paraClass = filterObj.getClass();


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

        } catch (Exception e) {
            e.printStackTrace();
        }
        where.append(TimeConditions);
        String resultString = " " + where.toString() + " ";
        logger.trace(resultString);
        writer.write(resultString);
        return true;
    }

}
