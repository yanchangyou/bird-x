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



import java.text.DecimalFormat;
import java.text.ParseException;

import org.software.bird.common.exception.OutOfRangeException;
import org.software.bird.som.exception.InvalidNumberException;


/**
 * 瀵筃umber鍩烘湰绫诲瀷鐨勮В鏋愮被
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: NumberParsor.java,v0.1 2007-12-7 涓嬪崍04:35:28 cyyan Exp$
 */
public class NumberParser extends Parser {

	final static DecimalFormat aDecimalFormat = new DecimalFormat();
	/**
	 * 瑙ｆ瀽瀛楃鎴愬熀鏈被鍨嬶紝濡傛灉瑙ｆ瀽鐨勭粨鏋滄槸BigDecimal锛孊igInteger灏嗗鑷磋寖鍥村紓甯�
	 * 
	 * @param 寰呰В鏋愮殑瀛楃涓�
	 * 
	 * @exception InvalidNumberException 鏃犳晥鐨勫瓧绗︿覆
	 * @exception OutOfRangeException瓒呰繃BigInteger锛孊igDecimal
	 * 
	 */
	public Object parse(String str) throws InvalidNumberException, OutOfRangeException {
		if (str == null || str.trim().equals("")) {
			throw new InvalidNumberException("empty exception");
		}
		Number num = null;
		try {
			num = aDecimalFormat.parse(str);
		} catch (ParseException e) {
			throw new InvalidNumberException("not a number");
		}
		if (num instanceof java.math.BigDecimal || num instanceof java.math.BigInteger) {
			throw new OutOfRangeException("out of range exception");
		}
		return num;
	}

	/**
	 * 涓嶄娇鐢ㄦ鏂规硶
	 * 
	 * @deprecated
	 */
	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}
	/**
	 * 涓嶄娇鐢ㄦ鏂规硶
	 * 
	 * @deprecated
	 */
	public void setPattern(String pattern) {
	}
}
