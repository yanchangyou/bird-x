/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;



import java.sql.Timestamp;
import java.util.Date;

import org.software.bird.som.exception.InvalidDateException;


/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: TimestampParsor.java,v0.1 2007-12-7 ����05:54:50 cyyan Exp$
 */
public class TimestampParser extends DateParser {

	public Object parse(String dateStr) throws InvalidDateException{
		Date date = (Date)super.parse(dateStr);
		return new Timestamp(date.getTime());
	}

}
