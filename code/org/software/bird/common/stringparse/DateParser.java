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
 * 瀵笵ate绫诲瀷鐨勮В鏋愮被
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DateParsor.java,v0.1 2007-12-7 涓嬪崍05:24:04 cyyan Exp$
 */
public class DateParser extends Parser {

	/**
	 * 鏃ュ墠鏍煎紡 yyyy-MM-dd
	 */
	final private String DATE_PATTERN = "yyyy-MM-dd"; 
	/**
	 * 鏃ュ墠鏍煎紡瀵硅薄鐢ㄤ簬鏍煎紡鏃ュ墠
	 */
	public static SimpleDateFormat DATE_FORMAT;

	/**
	 * 鏃ュ墠鏍煎紡鏍峰紡
	 */
	private String pattern;
	
	/**
	 * 鏃ュ墠鏍煎紡瑙ｆ瀽绫绘瀯閫犳柟娉�---鎸囧畾鏍峰紡
	 * @param pattern 鏍煎紡鏍峰紡
	 */
	public DateParser(String pattern) {
		this.pattern = pattern;
		DATE_FORMAT = new SimpleDateFormat(pattern);
	}
	/**
	 * 鏃ュ墠鏍煎紡瑙ｆ瀽绫绘瀯閫犳柟娉�---浣跨敤缂虹渷鐨勬棩鍓嶆牱寮� yyyy-MM-dd
	 *
	 */
	public DateParser() {
		DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
	}

	/**
	 * 瑙ｆ瀽鏃ュ墠瀛楃涓�
	 * @param dateStr 瑕佽В鏋愮殑鏃ュ墠瀛楃涓�
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
	 * 鑾峰彇鏍峰紡
	 * @return 鏃ュ墠鏍峰紡瀛楃涓�
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * 璁剧疆鏃ュ墠鏍峰紡
	 * @param pattern 鏍峰紡
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * 瑙ｆ瀽瀛楃涓叉寚瀹氭牱寮�
	 * @param str 瑕佽В鏋愮殑瀛楃涓�
	 * @param pattern 鏃ュ墠鏍峰紡
	 */
	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}
}
