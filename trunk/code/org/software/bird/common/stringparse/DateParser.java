/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.software.bird.common.stringparse;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.software.bird.som.exception.InvalidDateException;


/**
 * 对Date类型的解析类
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DateParsor.java,v0.1 2007-12-7 下午05:24:04 cyyan Exp$
 */
public class DateParser extends Parser {

	/**
	 * 日前格式 yyyy-MM-dd
	 */
	final private String DATE_PATTERN = "yyyy-MM-dd"; 
	/**
	 * 日前格式对象用于格式日前
	 */
	public static SimpleDateFormat DATE_FORMAT;

	/**
	 * 日前格式样式
	 */
	private String pattern;
	
	/**
	 * 日前格式解析类构造方法---指定样式
	 * @param pattern 格式样式
	 */
	public DateParser(String pattern) {
		this.pattern = pattern;
		DATE_FORMAT = new SimpleDateFormat(pattern);
	}
	/**
	 * 日前格式解析类构造方法---使用缺省的日前样式 yyyy-MM-dd
	 *
	 */
	public DateParser() {
		DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
	}

	/**
	 * 解析日前字符串
	 * @param dateStr 要解析的日前字符串
	 */
	public Object parse(String dateStr) throws InvalidDateException {
		Date date = null;
		try {
			date = DATE_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			throw new InvalidDateException("invalid date");
		}
		return date;
	}

	/**
	 * 获取样式
	 * @return 日前样式字符串
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * 设置日前样式
	 * @param pattern 样式
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * 解析字符串指定样式
	 * @param str 要解析的字符串
	 * @param pattern 日前样式
	 */
	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}
}
