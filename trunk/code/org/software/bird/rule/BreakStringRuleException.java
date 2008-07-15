/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.rule;
/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BreakStringRuleException.java,v0.1 2007-12-14 ����08:57:03 cyyan Exp$
 */
public class BreakStringRuleException extends Exception {
	
	private static final long serialVersionUID = -4115377227500062678L;
	
	private StringRule stringRule;
	
	public StringRule getStringRule() {
		return stringRule;
	}

	public void setStringRule(StringRule stringRule) {
		this.stringRule = stringRule;
	}

	public BreakStringRuleException(String msg) {
		super(msg);
	}
	
	public BreakStringRuleException(StringRule stringRule) {
		this(stringRule.getMessage());
		this.stringRule = stringRule;
	}

}
