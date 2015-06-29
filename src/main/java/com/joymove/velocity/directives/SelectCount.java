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
public class SelectCount  extends Directive {

    final static Logger logger = LoggerFactory.getLogger(SelectCount.class);
    protected static final String PARAMETER_OBJECT_KEY = "_parameter";
    protected static final String DATABASE_ID_KEY = "_databaseId";
    protected static final String MAPPING_COLLECTOR_KEY = "_pmc";
    protected static final String VARIABLES_KEY = "_vars";




    public String getName() { return "SelectCount"; } //指定指令的名称

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
        StringBuffer fromTable = new StringBuffer();


        Node parameterNode = node.jjtGetChild(0);

        Map<String,Object> paraMap = (Map<String,Object>)parameterNode.value(context);

        Object  filterObj = paraMap.get("filter");

        String TimeConditions = TimeScopeFilter.generateConditons(paraMap);


        paraClass = filterObj.getClass();


        Field[] fields = paraClass.getFields();
        String fieldName;
        try {
            fromTable.append("select count(id) from ");
            where.append(" where ");
            for(Field f : fields) {

                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    //不使用静态的属性
                    if(f.getName()=="tableName") {
                        String tableName = String.valueOf(f.get(filterObj));
                        fromTable.append(" "+tableName+"  u ");
                    }
                } else {

                    if (f.get(filterObj)!=null) {

                        fieldName = f.getName();
                        Type f_type = f.getType();

                        if(f_type.equals(String.class)) {
                            where.append(" u." + fieldName + " like @{filter." + fieldName + "} and");
                        } else {
                            where.append(" u." + fieldName + " = @{filter." + fieldName + "} and");
                        }
                    }

                }
            }
            where.append(TimeConditions);
            if(where.toString().equals(" where ")) {
                where.delete(0,where.length());
            } else {
                where.delete(where.length()-3,where.length());
            }

            String resultString = fromTable.toString() + " " + where.toString();
            SQLScriptSource thisScriptSource = (SQLScriptSource)context.get("sqlSource");
            SQLScriptSource childSQLScriptSource = new SQLScriptSource(thisScriptSource.getConfiguration(),resultString,Map.class);
            Map<String, Object> contextRoot =  (Map<String, Object>)context.get("contextRoot");
            Map<String, Object> context2 = new HashMap<String, Object>();
            BoundSql boundsQL = childSQLScriptSource.getBoundSql(paraMap,context2);
            contextRoot.put(MAPPING_COLLECTOR_KEY, context2.get(MAPPING_COLLECTOR_KEY));
            String retSQL = boundsQL.getSql();
            writer.write(retSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
