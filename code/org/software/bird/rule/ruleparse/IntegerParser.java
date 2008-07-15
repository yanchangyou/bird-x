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
 * @version $Id: DoubleParsor.java,v0.1 2007-12-7 ����04:38:28 cyyan Exp$
 */
public class IntegerParser extends NumberParser {

	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,"Integer"));
		}
		Integer doubleVar = new Integer(num.intValue());
		return doubleVar;
	}

	public String getRange() {		
		return "["+ Integer.MIN_VALUE + "," + Integer.MAX_VALUE + "]";
	}

	public boolean isInRange(Number num) {
		return Integer.MIN_VALUE <= num.doubleValue() && num.doubleValue() <= Integer.MAX_VALUE;
	}
}
