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

	/**
	 * 父规则对象
	 */
	private StringRule parentStringRule; 
	
	/**
	 * 规则描述
	 */
	private String description;

	/**
	 * 是否为空
	 */
	private boolean emptyable = true;

	/**
	 * 匹配字符串
	 */
	private String match;

	/**
	 * 不匹配的消息
	 */
	private String message;

	/**
	 * 被测试，校验的字符串
	 */
	private String testedString;
	
	/**
	 * 测试时，先测试父规则，测试方法如下：<br>
	 * 
	 * 用规则去测试字符串 有两个规则要检查 <br>
	 * 1，是否为空 <br>
	 * 2，是否满足正则表达式 <br>
	 * 
	 * 测试不通过将抛异常 不通过的条件如下： <br>
	 * 1，字符串是空的，但规则说明不能为空 code：(isEmpty && !emptyable)<br>
	 * 2，字符串不为空，规则也不为空，但不满足正则表达式 code：(!isEmpty && !isEmptyString(match) && !str.matches(match))<br>
	 * 
	 * 特殊处理：为了避免 null 字符串的情况，先将 null 转化为 "",以便处理<br>
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

	/**
	 * 是否是空字符串
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return str == null || str.matches("^\\s*$");
	}

	/**
	 * 把null处理成""
	 * @param str
	 * @return
	 */
	public static String null2empty(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmptyable() {
		return emptyable;
	}

	/**
	 * emptyable 的getter方法
	 * @return
	 */
	public boolean getEmptyable() {
		return emptyable;
	}

	/**
	 * emptyable 的setter方法
	 * @param emptyable
	 */
	public void setEmptyable(boolean emptyable) {
		this.emptyable = emptyable;
	}
	/**
	 * emptyable 的setter方法
	 * @param emptyable
	 */
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
	
	/**
	 * 转换成字符串
	 */
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

	/**
	 * parentStringRule 的getter方法
	 * @return StringRule
	 */
	public StringRule getParentStringRule() {
		return parentStringRule;
	}
	/**
	 * parentStringRule 的setter方法
	 * @param parentStringRule
	 */
	public void setParentStringRule(StringRule parentStringRule) {
		this.parentStringRule = parentStringRule;
	}

	/**
	 * testedString的 getter方法
	 * @return
	 */
	public String getTestedString() {
		return testedString;
	}

	/**
	 * testedString 的setter方法
	 * @param testedString
	 */
	public void setTestedString(String testedString) {
		this.testedString = testedString;
	}

}
