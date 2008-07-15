/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.rule;


/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringRule.java,v0.1 2007-12-14 ����08:54:46 cyyan Exp$
 */
public class StringRule {

	private StringRule parentStringRule; 
	
	private String description;

	private boolean emptyable = true;

	private String match;

	private String message;

	private String testedString;
	
	/**
	 * ����ʱ���Ȳ��Ը����򣬲��Է������£�
	 * 
	 * �ù���ȥ�����ַ��� ����������Ҫ��� 1���Ƿ�Ϊ�� 2���Ƿ�����������ʽ 
	 * 
	 * ���Բ�ͨ�������쳣 ��ͨ�����������£� 1���ַ����ǿյģ�������˵������Ϊ�� code��(isEmpty && !emptyable)
	 * 2���ַ�����Ϊ�գ�����Ҳ��Ϊ�գ���������������ʽ code��(!isEmpty && !isEmptyString(match) && !str.matches(match))
	 * 
	 * ���⴦��Ϊ�˱��� null �ַ�����������Ƚ� null ת��Ϊ "",�Ա㴦��
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
