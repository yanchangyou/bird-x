/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.rule.ruleparse;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.software.bird.rule.RuleBugException;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: NumberParsor.java,v0.1 2007-12-7 ����04:35:28 cyyan Exp$
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
