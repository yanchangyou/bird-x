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

import org.software.bird.rule.RuleBugException;

/**
 * 瀵笽nteger绫诲瀷鐨勮В鏋愮被
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DoubleParsor.java,v0.1 2007-12-7 涓嬪崍04:38:28 cyyan Exp$
 */
public class IntegerParser extends NumberParser {

	/**
	 * 鎶婂瓧绗︿覆瑙ｆ瀽鎴怚nteger瀵硅薄
	 * @param 寰呰В鏋愮殑瀛楃涓�
	 */
	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,"Integer"));
		}
		Integer doubleVar = new Integer(num.intValue());
		return doubleVar;
	}

	/**
	 * 鏄惁鍦↖nteger鑼冨洿鍐呴儴
	 * @param Number
	 */
	public String getRange() {		
		return "["+ Integer.MIN_VALUE + "," + Integer.MAX_VALUE + "]";
	}

	/**
	 * 鑾峰彇鑼冨洿
	 * @return 鑼冨洿瀛楃涓�
	 */
	public boolean isInRange(Number num) {
		return Integer.MIN_VALUE <= num.doubleValue() && num.doubleValue() <= Integer.MAX_VALUE;
	}
}
