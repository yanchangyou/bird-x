/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.som.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.software.bird.common.stringparse.Parser;
import org.software.bird.rule.RuleBugException;
import org.software.bird.rule.ruleparse.ByteParser;
import org.software.bird.rule.ruleparse.DateParser;
import org.software.bird.rule.ruleparse.DoubleParser;
import org.software.bird.rule.ruleparse.FloatParser;
import org.software.bird.rule.ruleparse.IntegerParser;
import org.software.bird.rule.ruleparse.LongParser;
import org.software.bird.rule.ruleparse.SQLDateParser;
import org.software.bird.rule.ruleparse.ShortParser;
import org.software.bird.rule.ruleparse.StringParser;
import org.software.bird.rule.ruleparse.TimestampParser;
import org.software.bird.som.exception.UnsupportedParseException;


/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BasicObjectFormat.java,v0.1 2007-12-7 下午04:03:41 cyyan Exp$
 */
public class BasicObjectParseUtil {
	
	private static final long serialVersionUID = 859369932165173796L;

	private static List allParsableObject = new ArrayList();
	private static Map objectParsorMap = new HashMap();
	
	static {
		allParsableObject.add(Byte.class);
		allParsableObject.add(Short.class);
		allParsableObject.add(Integer.class);
		allParsableObject.add(Long.class);
		allParsableObject.add(Float.class);
		allParsableObject.add(Double.class);
		allParsableObject.add(String.class);
		allParsableObject.add(java.util.Date.class);
		allParsableObject.add(java.sql.Date.class);
		allParsableObject.add(java.sql.Timestamp.class);
	}
	
	static {
		objectParsorMap.put(Byte.class, new ByteParser());
		objectParsorMap.put(Short.class, new ShortParser());
		objectParsorMap.put(Integer.class, new IntegerParser());
		objectParsorMap.put(Long.class, new LongParser());
		objectParsorMap.put(Float.class, new FloatParser());
		objectParsorMap.put(Double.class, new DoubleParser());
		objectParsorMap.put(String.class, new StringParser());
		objectParsorMap.put(java.util.Date.class, new DateParser());
		objectParsorMap.put(java.sql.Date.class, new SQLDateParser());
		objectParsorMap.put(java.sql.Timestamp.class, new TimestampParser());
	}
	
	public static Object parase(String str,String pattern, Class objClass) throws UnsupportedParseException, Exception  {
		if (!allParsableObject.contains(objClass)) {
			throw new UnsupportedParseException("supported this class parse");
		}
		Parser parser = (Parser)objectParsorMap.get(objClass);
		parser.setPattern(pattern);
		return parser.parse(str);
	}
	
	public static Object parase(String str, Class objClass)throws RuleBugException  {
		
		if (!allParsableObject.contains(objClass)) {
			System.out.println("SOM:eroor! 不支持对此类型的解析!");
			return null;
		}
		StringParser stringParsor = (StringParser)objectParsorMap.get(objClass);
		return stringParsor.parse(str);	
	}
}



