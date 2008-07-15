/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;


import org.software.bird.common.exception.OutOfRangeException;
import org.software.bird.som.exception.InvalidNumberException;


/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DoubleParsor.java,v0.1 2007-12-7 下午04:38:28 cyyan Exp$
 */
public class IntegerParser extends NumberParser {

	public Object parse(String str) throws InvalidNumberException,
			OutOfRangeException {
		Number num = (Number) super.parse(str);
		double value = num.doubleValue();
		if (!(Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE)) {
			throw new OutOfRangeException("out doubleVar range ["
					+ Integer.MIN_VALUE + "," + Integer.MAX_VALUE + "]");
		}
		Integer doubleVar = new Integer(num.intValue());
		return doubleVar;
	}
}
