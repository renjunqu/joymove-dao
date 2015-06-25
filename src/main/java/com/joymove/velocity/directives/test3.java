package com.joymove.velocity.directives;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.mybatis.scripting.velocity.ParameterMappingCollector;
import org.mybatis.scripting.velocity.SQLScriptSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jessie on 2015/6/14.
 */
//selectprefix 在这个元语生产的sql语句后面补充上用户自己的sql查询条件和Limit条件以及排序条件
public class test3  extends Directive  {

    final static Logger logger = LoggerFactory.getLogger(SelectWithExtendCondition.class);
    protected static final String PARAMETER_OBJECT_KEY = "_parameter";
    protected static final String DATABASE_ID_KEY = "_databaseId";
    protected static final String MAPPING_COLLECTOR_KEY = "_pmc";
    protected static final String VARIABLES_KEY = "_vars";



    public String getName() { return "test3"; } //指定指令的名称

    @Override
    public int getType() { return BLOCK; } //指定指令类型为行指令

    /* (non-Javadoc)
    * @see org.apache.velocity.runtime.directive.Directive#render()
    */
    //接收一个Map类型的 {三个key, 第一个是Entity,第二个是}
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException,
            MethodInvocationException
    {

        SQLScriptSource thisScriptSource = (SQLScriptSource)context.get("sqlSource");

        // ParameterMapping.Builder builder = new ParameterMapping.Builder();
        // ParameterMappingCollector pmc =  (ParameterMappingCollector)context.get("_pmc");
        // pmc.setItemKey("haha");
        Map map = (Map)node.jjtGetChild(0).value(context);

        String test = "select * from JOY_Users where id = @{id}";



        SQLScriptSource childSQLScriptSource = new SQLScriptSource(thisScriptSource.getConfiguration(),test,Map.class);
        Map<String, Object> contextRoot =  (Map<String, Object>)context.get("contextRoot");


        // context2.put(DATABASE_ID_KEY, thisScriptSource.getConfiguration().getDatabaseId());
        // context2.put(PARAMETER_OBJECT_KEY, map);
        // context2.put(MAPPING_COLLECTOR_KEY, pmc);
        // context2.put(VARIABLES_KEY, thisScriptSource.getConfiguration().getVariables());



        thisScriptSource.setParameterMappingSources(childSQLScriptSource.getParameterMappingSources());

        Map<String, Object> context2 = new HashMap<String, Object>();
        BoundSql boundsQL = childSQLScriptSource.getBoundSql(map,context2);

        contextRoot.put(MAPPING_COLLECTOR_KEY,context2.get("pmc"));

        writer.write(boundsQL.getSql());
        return true;
    }

}
