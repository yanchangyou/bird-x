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
 * 对Long类型的解析类
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: asdf.java,v0.1 2007-12-7 下午05:45:06 cyyan Exp$
 */

public class LongParser extends NumberParser {

	/**
	 * 把字符串解析成Long对象
	 * @param 待解析的字符串
	 */
	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,"Long"));
		}
		Long longVar = new Long(num.longValue());
		return longVar;
	}

	public String getRange() {
		return "[" + Long.MIN_VALUE + ", " + Long.MAX_VALUE + "]";
	}

	/**
	 * 获取范围
	 * @return 范围字符串
	 */
	public boolean isInRange(Number num) {
		return Long.MIN_VALUE <= num.doubleValue()&& num.doubleValue() <= Long.MAX_VALUE;
	}
}
