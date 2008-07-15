/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.rule.ruleparse;

import org.software.bird.rule.RuleBugException;


/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: asdf.java,v0.1 2007-12-7 ����05:45:06 cyyan Exp$
 */

public class LongParser extends NumberParser {

	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,"Long"));
		}
		Long longVar = new Long(num.longValue());
		return longVar;
	}

	public String getRange() {
		return "[" + Long.MIN_VALUE + ", " + Long.MAX_VALUE + "]";
	}

	public boolean isInRange(Number num) {
		return Long.MIN_VALUE <= num.doubleValue()&& num.doubleValue() <= Long.MAX_VALUE;
	}
}
