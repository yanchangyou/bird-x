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
 * �ַ�������
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringRule.java,v0.1 2007-12-14 ����08:54:46 cyyan Exp$
 */
public class StringRule {

	/**
	 * ���������
	 */
	private StringRule parentStringRule; 
	
	/**
	 * ��������
	 */
	private String description;

	/**
	 * �Ƿ�Ϊ��
	 */
	private boolean emptyable = true;

	/**
	 * ƥ���ַ���
	 */
	private String match;

	/**
	 * ��ƥ�����Ϣ
	 */
	private String message;

	/**
	 * �����ԣ�У����ַ���
	 */
	private String testedString;
	
	/**
	 * ����ʱ���Ȳ��Ը����򣬲��Է������£�<br>
	 * 
	 * �ù���ȥ�����ַ��� ����������Ҫ��� <br>
	 * 1���Ƿ�Ϊ�� <br>
	 * 2���Ƿ�����������ʽ <br>
	 * 
	 * ���Բ�ͨ�������쳣 ��ͨ�����������£� <br>
	 * 1���ַ����ǿյģ�������˵������Ϊ�� code��(isEmpty && !emptyable)<br>
	 * 2���ַ�����Ϊ�գ�����Ҳ��Ϊ�գ���������������ʽ code��(!isEmpty && !isEmptyString(match) && !str.matches(match))<br>
	 * 
	 * ���⴦��Ϊ�˱��� null �ַ�����������Ƚ� null ת��Ϊ "",�Ա㴦��<br>
	 * 
	 * @param str
	 * @throws BreakStringRuleException
	 */
	public void test(String str) throws BreakStringRuleException {
		testedString = null2empty(str);
		boolean isEmpty = isEmptyString(testedString);

		if (this.parentStringRule != null) {
			/**
			 * try-catch ���� �ǰ� ��������ͨ����ȥ�����û��try-catch ��ֻͨ��������
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
	 * �Ƿ��ǿ��ַ���
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return str == null || str.matches("^\\s*$");
	}

	/**
	 * ��null�����""
	 * @param str
	 * @return
	 */
	public static String null2empty(String str) {
		return str == null ? "" : str;
	}

	/**
	 * �Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmptyable() {
		return emptyable;
	}

	/**
	 * emptyable ��getter����
	 * @return
	 */
	public boolean getEmptyable() {
		return emptyable;
	}

	/**
	 * emptyable ��setter����
	 * @param emptyable
	 */
	public void setEmptyable(boolean emptyable) {
		this.emptyable = emptyable;
	}
	/**
	 * emptyable ��setter����
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
	 * ת�����ַ���
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("�ַ�����\n" + testedString + "\n����\n");
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
	 * parentStringRule ��getter����
	 * @return StringRule
	 */
	public StringRule getParentStringRule() {
		return parentStringRule;
	}
	/**
	 * parentStringRule ��setter����
	 * @param parentStringRule
	 */
	public void setParentStringRule(StringRule parentStringRule) {
		this.parentStringRule = parentStringRule;
	}

	/**
	 * testedString�� getter����
	 * @return
	 */
	public String getTestedString() {
		return testedString;
	}

	/**
	 * testedString ��setter����
	 * @param testedString
	 */
	public void setTestedString(String testedString) {
		this.testedString = testedString;
	}

}
