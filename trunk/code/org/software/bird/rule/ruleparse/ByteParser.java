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
 * 瀵笲yte绫诲瀷鐨勮В鏋愮被
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: t.java,v0.1 2007-12-7 涓嬪崍05:44:50 cyyan Exp$
 */

public class ByteParser extends NumberParser {

	/**
	 * 寰呰鍒欐牎楠岀殑鎶婂瓧绗︿覆瑙ｆ瀽鎴怋yte绫诲瀷
	 * @exception 瑙勫垯寮傚父
	 */
	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(
					constructNumberRuleBugReport(num, "Byte"));
		}
		Byte byteVar = new Byte(num.byteValue());
		return byteVar;
	}

	/**
	 * 鑾峰彇瀛楃鐨勮寖鍥�
	 */
	public String getRange() {
		return "[" + Byte.MIN_VALUE + "," + Byte.MAX_VALUE + "]";
	}

	/**
	 * 鍒ゆ柇鏄惁鍦˙yte鑼冨洿鍐�
	 */
	public boolean isInRange(Number num) {
		return Byte.MIN_VALUE <= num.doubleValue()
				&& num.doubleValue() <= Byte.MAX_VALUE;
	}
}
