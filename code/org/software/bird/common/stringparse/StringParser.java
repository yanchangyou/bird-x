/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;

import org.software.bird.som.exception.EmptyException;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringParsor.java,v0.1 2007-12-7 下午05:24:57 cyyan Exp$
 */
public class StringParser  extends Parser {

	public static String str2str(String str) throws EmptyException {
		if (str == null || str.trim().equals("")) {
			throw new EmptyException("空白字符串异常");
		}
		return str;
	}

	public Object parse(String str) throws Exception {
		if (str == null || str.trim().equals("")) {
			throw new EmptyException("空白字符串异常");
		}
		return str;
	}

	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}

	public void setPattern(String pattern) {		
	}
}
