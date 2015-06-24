package com.joymove.velocity.directives;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class test extends Directive {

    final static Logger logger = LoggerFactory.getLogger(DeleteFilter.class);



    public String getName() { return "test"; } //指定指令的名称

    @Override
    public int getType() { return BLOCK; } //指定指令类型为行指令

    /* (non-Javadoc)
    * @see org.apache.velocity.runtime.directive.Directive#render()
    */
    //接收一个Map类型的 {第一个是Entity}
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException,
            MethodInvocationException
    {
        System.out.println("child numbers is " + node.jjtGetNumChildren());
        for(int i=0;i<node.jjtGetNumChildren();i++) {
            Object value = node.jjtGetChild(i);
            System.out.println("("+i+") class is "+value.getClass()+" , value is "+value.toString());
            if(value.getClass().equals(ASTBlock.class)) {
                ASTBlock block = (ASTBlock)value;
             block.render(context,writer);

            }
        }



      //  writer.write("select * from JOY_Users where id=42;");
        return true;
    }

}
