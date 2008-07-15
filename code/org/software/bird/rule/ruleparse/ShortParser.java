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
 * @version $Id: safd.java,v0.1 2007-12-7 ����05:45:02 cyyan Exp$
 */

public class ShortParser extends NumberParser {
	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,
					"Short"));
		}
		Short shortVar = new Short(num.shortValue());
		return shortVar;
	}

	public String getRange() {
		return "[" + Short.MIN_VALUE + ", " + Short.MAX_VALUE + "]";
	}

	public boolean isInRange(Number num) {
		return Short.MIN_VALUE <= num.doubleValue()
				&& num.doubleValue() <= Short.MAX_VALUE;
	}
}
