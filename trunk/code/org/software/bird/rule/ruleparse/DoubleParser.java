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
 * @version $Id: asdfasd.java,v0.1 2007-12-7 ����05:45:11 cyyan Exp$
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
