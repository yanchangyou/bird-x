/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.rule.ruleparse;

import org.software.bird.rule.RuleBugException;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: asdfasd.java,v0.1 2007-12-7 下午05:45:11 cyyan Exp$
 */

public class DoubleParser extends NumberParser {

	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,
					"Double"));
		}
		Double doubleVar = new Double(num.doubleValue());
		return doubleVar;
	}

	public boolean isInRange(Number num) {
		return -Double.MAX_VALUE <= num.doubleValue()
				&& num.doubleValue() <= Double.MAX_VALUE;
	}

	public String getRange() {
		return "[" + -Double.MAX_VALUE + "," + Double.MAX_VALUE + "]";
	}
}
