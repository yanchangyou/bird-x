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
 * 对Number类型的解析类
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: NumberParsor.java,v0.1 2007-12-7 下午04:35:28 cyyan Exp$
 */
public abstract class NumberParser extends StringParser {

	/**
	 * 十进制格式对象
	 */
	final static DecimalFormat aDecimalFormat = new DecimalFormat();

	/**
	 * 把字符串解析成Number
	 * @param 待解析的字符串
	 * @return Number实例
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
	 * 判读Number是否在其范围内部， 这是抽象方法， 具体范围有其子类提供
	 * @param num
	 * @return
	 */
	public abstract boolean isInRange(Number num);
	
	/**
	 * 获取范围
	 * @return 对Number范围的描述
	 */
	public abstract String getRange();
	
	/**
	 * 构造规则报告
	 * @param num
	 * @param type
	 * @return 报告的字符串
	 */
	public String constructNumberRuleBugReport(Number num, String type) {
		return RuleBugException.rule_bug_report_pre + num.doubleValue() + " out " + type + " range " + getRange();
	}
}
