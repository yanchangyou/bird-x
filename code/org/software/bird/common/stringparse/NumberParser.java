/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;



import java.text.DecimalFormat;
import java.text.ParseException;

import org.software.bird.common.exception.OutOfRangeException;
import org.software.bird.som.exception.InvalidNumberException;


/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: NumberParsor.java,v0.1 2007-12-7 下午04:35:28 cyyan Exp$
 */
public class NumberParser extends Parser {

	final static DecimalFormat aDecimalFormat = new DecimalFormat();

	public Object parse(String str) throws InvalidNumberException, OutOfRangeException {
		if (str == null || str.trim().equals("")) {
			throw new InvalidNumberException("empty exception");
		}
		Number num = null;
		try {
			num = aDecimalFormat.parse(str);
		} catch (ParseException e) {
			throw new InvalidNumberException("not a number");
		}
		if (num instanceof java.math.BigDecimal || num instanceof java.math.BigInteger) {
			throw new OutOfRangeException("out of range exception");
		}
		return num;
	}

	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}

	public void setPattern(String pattern) {
	}
}
