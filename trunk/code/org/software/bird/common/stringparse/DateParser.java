/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.software.bird.som.exception.InvalidDateException;


/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DateParsor.java,v0.1 2007-12-7 ����05:24:04 cyyan Exp$
 */
public class DateParser extends Parser {

	final private String DATE_PATTERN = "yyyy-MM-dd"; 
	public static SimpleDateFormat DATE_FORMAT;

	private String pattern;
	public DateParser(String pattern) {
		this.pattern = pattern;
		DATE_FORMAT = new SimpleDateFormat(pattern);
	}
	
	public DateParser() {
		DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
	}

	public Object parse(String dateStr) throws InvalidDateException {
		Date date = null;
		try {
			date = DATE_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			throw new InvalidDateException("invalid date");
		}
		return date;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}
}
