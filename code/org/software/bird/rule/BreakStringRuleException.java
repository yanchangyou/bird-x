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

package org.software.bird.rule;
/**
 * 破坏规则的异常
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BreakStringRuleException.java,v0.1 2007-12-14 上午08:57:03 cyyan Exp$
 */
public class BreakStringRuleException extends Exception {
	
	private static final long serialVersionUID = -4115377227500062678L;
	
	/**
	 * 字符规则
	 */
	private StringRule stringRule;
	/**
	 * getter方法
	 * @return
	 */
	public StringRule getStringRule() {
		return stringRule;
	}

	/**
	 * setter方法
	 * @param stringRule
	 */
	public void setStringRule(StringRule stringRule) {
		this.stringRule = stringRule;
	}

	/**
	 * 规则异常
	 * @param msg 异常消息
	 */
	public BreakStringRuleException(String msg) {
		super(msg);
	}
	
	/**
	 * 规则异常
	 * @param stringRule
	 */
	public BreakStringRuleException(StringRule stringRule) {
		this(stringRule.getMessage());
		this.stringRule = stringRule;
	}

}
