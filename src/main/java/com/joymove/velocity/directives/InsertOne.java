package com.joymove.velocity.directives;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.mybatis.scripting.velocity.SQLScriptSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qurj on 15/6/10.
 */
public class InsertOne  extends Directive {

    final static Logger logger = LoggerFactory.getLogger(InsertOne.class);
    protected static final String PARAMETER_OBJECT_KEY = "_parameter";
    protected static final String DATABASE_ID_KEY = "_databaseId";
    protected static final String MAPPING_COLLECTOR_KEY = "_pmc";
    protected static final String VARIABLES_KEY = "_vars";



    public String getName() { return "InsertOne"; } //指定指令的名称

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

        StringBuffer columns = new StringBuffer();
        StringBuffer values = new StringBuffer();
        StringBuffer prefiex = new StringBuffer();

        prefiex.append("insert into ");
        columns.append("( ");
        values.append(" values( ");

        Node parameterNode = node.jjtGetChild(0);

        Object  filterObj = parameterNode.value(context);
        Class  paraClass = filterObj.getClass();


        Field[] fields = paraClass.getFields();
        String fieldName;
        try {
            //org.springframework.jdbc.support.SQLStateSQLExceptionTranslator.doTranslate

            for(Field f : fields) {

                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    //不使用静态的属性
                    if(f.getName()=="tableName") {
                        String tableName = String.valueOf(f.get(filterObj));
                        prefiex.append(" "+tableName);
                    }
                } else {

                    if (f.get(filterObj)!=null) {

                        fieldName = f.getName();

                        Type f_type = f.getType();
                        columns.append(" "+fieldName+" ,");
                        values.append(" @{"+fieldName+"} ,");

                        /*
                        if(f_type.equals(String.class)) {
                            String value = (String)f.get(filterObj);
                            value = value.trim();
                            if(value.length()>0)
                                values.append(" \'" + f.get(filterObj)+"\' ,");

                        }else if(f_type.equals(Date.class)) {
                            Date d = (Date)f.get(filterObj);
                            Long timeValue = d.getTime();
                            timeValue = timeValue/1000;
                            values.append(" FROM_UNIXTIME("+timeValue+",'%Y-%m-%d %H:%i:%S') ,");

                        } else {
                            values.append(" " + f.get(filterObj) + " ,");
                        }
                        */
                    }

                }
            }

            if(values.charAt(values.length()-1)==',') {
                values.delete(values.length() - 1, values.length());
                values.append(")");
            } else {
                values.delete(0,values.length());
            }

            if(columns.charAt(columns.length()-1)==',') {
                columns.delete(columns.length() - 1, columns.length());
                columns.append(")");
            } else {
                columns.delete(0,columns.length());
            }


            String resultString =  prefiex.toString() + columns.toString()+" "+values.toString();
            SQLScriptSource thisScriptSource = (SQLScriptSource)context.get("sqlSource");
            SQLScriptSource childSQLScriptSource = new SQLScriptSource(thisScriptSource.getConfiguration(),resultString,paraClass);
            Map<String, Object> contextRoot =  (Map<String, Object>)context.get("contextRoot");
            Map<String, Object> context2 = new HashMap<String, Object>();
            BoundSql boundsQL = childSQLScriptSource.getBoundSql(filterObj, context2);
            contextRoot.put(MAPPING_COLLECTOR_KEY, context2.get(MAPPING_COLLECTOR_KEY));
            String retSQL = boundsQL.getSql();
            logger.trace(retSQL);
            writer.write(retSQL);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
