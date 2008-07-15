/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;

import org.software.bird.som.exception.InvalidDateException;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SQLDateParsor.java,v0.1 2007-12-7 下午05:54:32 cyyan Exp$
 */
public class SQLDateParser extends DateParser {

	public SQLDateParser(String pattern) {
		super(pattern);
	}
	public SQLDateParser() {
		super();
	}

	public Object parse(String dateStr) throws InvalidDateException {
		java.util.Date date = (java.util.Date)super.parse(dateStr);
		return new java.sql.Date(date.getTime());
	}
}
