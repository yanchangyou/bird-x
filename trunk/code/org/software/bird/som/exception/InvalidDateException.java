/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.som.exception;


/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DateParseException.java,v0.1 2007-12-7 ����04:47:38 cyyan Exp$
 */
public class InvalidDateException extends BreakRuleException {

	private static final long serialVersionUID = -6555656755094186252L;
	public InvalidDateException(String string) {
		super(string);
	}
}
