/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;

import org.software.bird.som.exception.EmptyException;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringParsor.java,v0.1 2007-12-7 ����05:24:57 cyyan Exp$
 */
public class StringParser  extends Parser {

	public static String str2str(String str) throws EmptyException {
		if (str == null || str.trim().equals("")) {
			throw new EmptyException("�հ��ַ����쳣");
		}
		return str;
	}

	public Object parse(String str) throws Exception {
		if (str == null || str.trim().equals("")) {
			throw new EmptyException("�հ��ַ����쳣");
		}
		return str;
	}

	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}

	public void setPattern(String pattern) {		
	}
}
