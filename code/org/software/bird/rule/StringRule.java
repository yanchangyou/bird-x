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
 * 瀛楃瑙勫垯绫�
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringRule.java,v0.1 2007-12-14 涓婂崍08:54:46 cyyan Exp$
 */
public class StringRule {

	/**
	 * 鐖惰鍒欏璞�
	 */
	private StringRule parentStringRule; 
	
	/**
	 * 瑙勫垯鎻忚堪
	 */
	private String description;

	/**
	 * 鏄惁涓虹┖
	 */
	private boolean emptyable = true;

	/**
	 * 鍖归厤瀛楃涓�
	 */
	private String match;

	/**
	 * 涓嶅尮閰嶇殑娑堟伅
	 */
	private String message;

	/**
	 * 琚祴璇曪紝鏍￠獙鐨勫瓧绗︿覆
	 */
	private String testedString;
	
	/**
	 * 娴嬭瘯鏃讹紝鍏堟祴璇曠埗瑙勫垯锛屾祴璇曟柟娉曞涓嬶細<br>
	 * 
	 * 鐢ㄨ鍒欏幓娴嬭瘯瀛楃涓� 鏈変袱涓鍒欒妫�鏌� <br>
	 * 1锛屾槸鍚︿负绌� <br>
	 * 2锛屾槸鍚︽弧瓒虫鍒欒〃杈惧紡 <br>
	 * 
	 * 娴嬭瘯涓嶉�氳繃灏嗘姏寮傚父 涓嶉�氳繃鐨勬潯浠跺涓嬶細 <br>
	 * 1锛屽瓧绗︿覆鏄┖鐨勶紝浣嗚鍒欒鏄庝笉鑳戒负绌� code锛�(isEmpty && !emptyable)<br>
	 * 2锛屽瓧绗︿覆涓嶄负绌猴紝瑙勫垯涔熶笉涓虹┖锛屼絾涓嶆弧瓒虫鍒欒〃杈惧紡 code锛�(!isEmpty && !isEmptyString(match) && !str.matches(match))<br>
	 * 
	 * 鐗规畩澶勭悊锛氫负浜嗛伩鍏� null 瀛楃涓茬殑鎯呭喌锛屽厛灏� null 杞寲涓� "",浠ヤ究澶勭悊<br>
	 * 
	 * @param str
	 * @throws BreakStringRuleException
	 */
	public void test(String str) throws BreakStringRuleException {
		testedString = null2empty(str);
		boolean isEmpty = isEmptyString(testedString);

		if (this.parentStringRule != null) {
			/**
			 * try-catch 浣滅敤 鏄妸 鏁翠釜瑙勫垯閫氭姤鍑哄幓锛屽鏋滄病鏈塼ry-catch 瀹冨彧閫氭姤鐖惰鍒�
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
	 * 鏄惁鏄┖瀛楃涓�
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return str == null || str.matches("^\\s*$");
	}

	/**
	 * 鎶妌ull澶勭悊鎴�""
	 * @param str
	 * @return
	 */
	public static String null2empty(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 鏄惁涓虹┖
	 * @return
	 */
	public boolean isEmptyable() {
		return emptyable;
	}

	/**
	 * emptyable 鐨刧etter鏂规硶
	 * @return
	 */
	public boolean getEmptyable() {
		return emptyable;
	}

	/**
	 * emptyable 鐨剆etter鏂规硶
	 * @param emptyable
	 */
	public void setEmptyable(boolean emptyable) {
		this.emptyable = emptyable;
	}
	/**
	 * emptyable 鐨剆etter鏂规硶
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
	 * 杞崲鎴愬瓧绗︿覆
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("瀛楃涓诧細\n" + testedString + "\n瑙勫垯锛歕n");
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
	 * parentStringRule 鐨刧etter鏂规硶
	 * @return StringRule
	 */
	public StringRule getParentStringRule() {
		return parentStringRule;
	}
	/**
	 * parentStringRule 鐨剆etter鏂规硶
	 * @param parentStringRule
	 */
	public void setParentStringRule(StringRule parentStringRule) {
		this.parentStringRule = parentStringRule;
	}

	/**
	 * testedString鐨� getter鏂规硶
	 * @return
	 */
	public String getTestedString() {
		return testedString;
	}

	/**
	 * testedString 鐨剆etter鏂规硶
	 * @param testedString
	 */
	public void setTestedString(String testedString) {
		this.testedString = testedString;
	}

}
