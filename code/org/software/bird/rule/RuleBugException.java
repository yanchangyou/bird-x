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
 * @version $Id: RuleBugException.java,v0.1 2007-12-16 ����05:11:31 cyyan Exp$
 */
public class RuleBugException extends Exception {

	private static final long serialVersionUID = -8876172406935676701L;
	
	public RuleBugException(String message) {
		super(message);
	}
	
	public static final String rule_bug_report_pre = "this is a rule bug, please check the rule and rewrite it; \nreason : " ;
}
