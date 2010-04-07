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

package org.software.bird.rule.ruleparse;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.software.bird.rule.RuleBugException;

/**
 * 瀵筃umber绫诲瀷鐨勮В鏋愮被
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: NumberParsor.java,v0.1 2007-12-7 涓嬪崍04:35:28 cyyan Exp$
 */
public abstract class NumberParser extends StringParser {

	/**
	 * 鍗佽繘鍒舵牸寮忓璞�
	 */
	final static DecimalFormat aDecimalFormat = new DecimalFormat();

	/**
	 * 鎶婂瓧绗︿覆瑙ｆ瀽鎴怤umber
	 * @param 寰呰В鏋愮殑瀛楃涓�
	 * @return Number瀹炰緥
	 */
	public Object parse(String str) throws RuleBugException {
		Number num = null;
		try {
			num = aDecimalFormat.parse(str);
		} catch (ParseException e) {
			throw new RuleBugException(RuleBugException.rule_bug_report_pre + str	+ " is not a number");
		}
		if (num instanceof java.math.BigDecimal || num instanceof java.math.BigInteger) {
			throw new RuleBugException(RuleBugException.rule_bug_report_pre + str + " out of can parse range");
		}
		return num;
	}
	
	/**
	 * 鍒よNumber鏄惁鍦ㄥ叾鑼冨洿鍐呴儴锛� 杩欐槸鎶借薄鏂规硶锛� 鍏蜂綋鑼冨洿鏈夊叾瀛愮被鎻愪緵
	 * @param num
	 * @return
	 */
	public abstract boolean isInRange(Number num);
	
	/**
	 * 鑾峰彇鑼冨洿
	 * @return 瀵筃umber鑼冨洿鐨勬弿杩�
	 */
	public abstract String getRange();
	
	/**
	 * 鏋勯�犺鍒欐姤鍛�
	 * @param num
	 * @param type
	 * @return 鎶ュ憡鐨勫瓧绗︿覆
	 */
	public String constructNumberRuleBugReport(Number num, String type) {
		return RuleBugException.rule_bug_report_pre + num.doubleValue() + " out " + type + " range " + getRange();
	}
}
