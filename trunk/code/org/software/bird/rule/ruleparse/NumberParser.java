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

	final static DecimalFormat aDecimalFormat = new DecimalFormat();

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
	
	public abstract boolean isInRange(Number num);
	public abstract String getRange();
	
	public String constructNumberRuleBugReport(Number num, String type) {
		return RuleBugException.rule_bug_report_pre + num.doubleValue() + " out " + type + " range " + getRange();
	}
}
