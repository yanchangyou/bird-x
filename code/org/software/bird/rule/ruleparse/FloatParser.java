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
 * @version $Id: te.java,v0.1 2007-12-7 ����05:44:57 cyyan Exp$
 */

public class FloatParser extends NumberParser {

	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,"Float"));
		}
		Float floatVar = new Float(num.floatValue());
		return floatVar;
	}

	public String getRange() {
		return "[" + -Float.MAX_VALUE + ", " + Float.MAX_VALUE + "]";
	}

	public boolean isInRange(Number num) {
		return -Float.MAX_VALUE <= num.doubleValue()
				&& num.doubleValue() <= Float.MAX_VALUE;
	}
}