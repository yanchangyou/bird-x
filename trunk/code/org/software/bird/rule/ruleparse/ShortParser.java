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
 * ��Short���͵Ľ�����
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: safd.java,v0.1 2007-12-7 ����05:45:02 cyyan Exp$
 */

public class ShortParser extends NumberParser {
	
	/**
	 * ���ַ���������Long����
	 * @param ���������ַ���
	 */
	public Object parse(String str) throws RuleBugException {
		Number num = (Number) super.parse(str);
		if (!isInRange(num)) {
			throw new RuleBugException(constructNumberRuleBugReport(num,
					"Short"));
		}
		Short shortVar = new Short(num.shortValue());
		return shortVar;
	}
	
	/**
	 * �Ƿ���Short��Χ�ڲ�
	 * @param Number
	 */
	public String getRange() {
		return "[" + Short.MIN_VALUE + ", " + Short.MAX_VALUE + "]";
	}

	/**
	 * ��ȡ��Χ
	 * @return ��Χ�ַ���
	 */
	public boolean isInRange(Number num) {
		return Short.MIN_VALUE <= num.doubleValue()
				&& num.doubleValue() <= Short.MAX_VALUE;
	}
}
