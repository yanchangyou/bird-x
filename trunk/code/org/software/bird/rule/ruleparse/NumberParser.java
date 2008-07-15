/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.rule.ruleparse;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.software.bird.rule.RuleBugException;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: NumberParsor.java,v0.1 2007-12-7 下午04:35:28 cyyan Exp$
 */
public abstract class NumberParser extends StringParser {

	final static DecimalFormat aDecimalFormat = new DecimalFormat();

	public Object parse(String str) throws RuleBugException {
		Number num = null;
		try {
			num = aDecimalFormat.parse(str);
		} catch (ParseException e) {
			throw new RuleBugException(RuleBugException.rule_bug_report_pre + str	+ " is not a number");
		}
		if (num instanceof java.math.BigDecimal || num instanceof java.math.BigInteger) {
			throw new RuleBugException(RuleBugException.rule_bug_report_pre + str + " out of can parse range");
		}
		return num;
	}
	
	public abstract boolean isInRange(Number num);
	public abstract String getRange();
	
	public String constructNumberRuleBugReport(Number num, String type) {
		return RuleBugException.rule_bug_report_pre + num.doubleValue() + " out " + type + " range " + getRange();
	}
}
