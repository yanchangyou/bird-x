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
 * 字符规则类
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringRule.java,v0.1 2007-12-14 上午08:54:46 cyyan Exp$
 */
public class StringRule {

	private StringRule parentStringRule; 
	
	private String description;

	private boolean emptyable = true;

	private String match;

	private String message;

	private String testedString;
	
	/**
	 * 测试时，先测试父规则，测试方法如下：
	 * 
	 * 用规则去测试字符串 有两个规则要检查 1，是否为空 2，是否满足正则表达式 
	 * 
	 * 测试不通过将抛异常 不通过的条件如下： 1，字符串是空的，但规则说明不能为空 code：(isEmpty && !emptyable)
	 * 2，字符串不为空，规则也不为空，但不满足正则表达式 code：(!isEmpty && !isEmptyString(match) && !str.matches(match))
	 * 
	 * 特殊处理：为了避免 null 字符串的情况，先将 null 转化为 "",以便处理
	 * 
	 * @param str
	 * @throws BreakStringRuleException
	 */
	public void test(String str) throws BreakStringRuleException {
		testedString = null2empty(str);
		boolean isEmpty = isEmptyString(testedString);

		if (this.parentStringRule != null) {
			/**
			 * try-catch 作用 是把 整个规则通报出去，如果没有try-catch 它只通报父规则
			 */
			try {
				this.parentStringRule.test(testedString);
			} catch (BreakStringRuleException e) {
				throw new BreakStringRuleException(this);
			}			
		}
		if ((isEmpty && !emptyable) || (!isEmpty && !isEmptyString(match) && !testedString.matches(match))) {
			throw new BreakStringRuleException(this);
		}
	}

	public static boolean isEmptyString(String str) {
		return str == null || str.matches("^\\s*$");
	}

	public static String null2empty(String str) {
		return str == null ? "" : str;
	}

	public boolean isEmptyable() {
		return emptyable;
	}

	public boolean getEmptyable() {
		return emptyable;
	}

	public void setEmptyable(boolean emptyable) {
		this.emptyable = emptyable;
	}

	public void setEmptyable(String emptyable) {
		this.emptyable = new Boolean(emptyable).booleanValue();
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPropertyValueMaxLenght() {
		int maxLength = 0;
		String[] stringPropertyArray = {description, match, message};
		for (int i = 0; i < stringPropertyArray.length; i++) {
			if (stringPropertyArray[i] != null && stringPropertyArray[i].trim().length() > maxLength ) {
				maxLength = stringPropertyArray[i].trim().length();
			}
		}
		if (this.parentStringRule != null) {
			if (maxLength < this.parentStringRule.getPropertyValueMaxLenght()) {
				maxLength = this.parentStringRule.getPropertyValueMaxLenght();
			}
		}
		return maxLength;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("字符串：\n" + testedString + "\n规则：\n");
		buf.append("{\n");
		buf.append("\tdescription:" + description + "\n");
		buf.append("\temptyable:" + emptyable + "\n");
		buf.append("\tmatch:" + match + "\n");
		buf.append("\tmessage:" + message + "\n");
		buf.append("}\n");
		if (this.parentStringRule != null) {
			buf.append("[parent rule]:\n" + this.parentStringRule);
		}
		return buf.toString();
	}

	public StringRule getParentStringRule() {
		return parentStringRule;
	}

	public void setParentStringRule(StringRule parentStringRule) {
		this.parentStringRule = parentStringRule;
	}

	public String getTestedString() {
		return testedString;
	}

	public void setTestedString(String testedString) {
		this.testedString = testedString;
	}

}
