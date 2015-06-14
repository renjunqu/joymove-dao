package com.joymove.velocity.directives;

import org.apache.commons.lang.StringUtils;
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
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jessie on 2015/6/14.
 */
public class QrjTrim  extends Directive  {
    public String getName() { return "QrjTrim"; } //指定指令的名称

    @Override
    public int getType() { return LINE; } //指定指令类型为行指令

    /* (non-Javadoc)
   * @see org.apache.velocity.runtime.directive.Directive#render()
   */
    public static String doTrim(String fixStr,String removeContent,String bodyStr) {
        System.out.println("hello trim");
        Pattern prefixPattern = Pattern.compile("^\\s*"+removeContent+"\\W(.*)");
        Pattern suffixPattern = Pattern.compile("(.*)\\W"+removeContent+"\\s*$");
        if(fixStr.equals("prefix")) {
            Matcher prefixMatcher = prefixPattern.matcher(bodyStr);
            if(prefixMatcher.matches()) {
                bodyStr = prefixMatcher.group(1);
            }

        } else if(fixStr.equals("suffix")) {
            Matcher suffixMatcher = suffixPattern.matcher(bodyStr);
            if(suffixMatcher.matches()){
                bodyStr = suffixMatcher.group(1);
            }
        }
        System.out.println(bodyStr);
        return bodyStr;
    }

    //接收一个Map类型的 {第一个是Entity}
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException,
            MethodInvocationException {

        Node parameter1 = node.jjtGetChild(0);
        Node parameter2 = node.jjtGetChild(1);
        Node body = node.jjtGetChild(2);
        System.out.println("number is "+node.jjtGetNumChildren());
        String fixStr = String.valueOf(parameter1.value(context));
        String removeContent = String.valueOf(parameter2.value(context));
        String bodyStr = String.valueOf(body.value(context)).replace("\n","");
        System.out.println("body is "+bodyStr);
        System.out.println("fixStr is "+fixStr);
        System.out.println("removeContent  is "+removeContent);
        bodyStr = QrjTrim.doTrim(fixStr,removeContent,bodyStr);
        writer.write(bodyStr);
        return true;
    }


    public  static  void main(String [] args) {
        String testS = "  \n             rt    QQQQ      and sdfsdfdsfsfsdfdsf    sdfdsfdsfsdf "+
                "sdfsdf  and\n";
        doTrim("prefix","rt",testS);

    }

}
