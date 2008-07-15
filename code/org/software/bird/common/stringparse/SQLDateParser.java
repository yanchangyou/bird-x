/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;

import org.software.bird.som.exception.InvalidDateException;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SQLDateParsor.java,v0.1 2007-12-7 ����05:54:32 cyyan Exp$
 */
public class SQLDateParser extends DateParser {

	public SQLDateParser(String pattern) {
		super(pattern);
	}
	public SQLDateParser() {
		super();
	}

	public Object parse(String dateStr) throws InvalidDateException {
		java.util.Date date = (java.util.Date)super.parse(dateStr);
		return new java.sql.Date(date.getTime());
	}
}
