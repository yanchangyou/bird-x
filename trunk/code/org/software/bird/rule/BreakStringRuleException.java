/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.rule;
/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BreakStringRuleException.java,v0.1 2007-12-14 上午08:57:03 cyyan Exp$
 */
public class BreakStringRuleException extends Exception {
	
	private static final long serialVersionUID = -4115377227500062678L;
	
	private StringRule stringRule;
	
	public StringRule getStringRule() {
		return stringRule;
	}

	public void setStringRule(StringRule stringRule) {
		this.stringRule = stringRule;
	}

	public BreakStringRuleException(String msg) {
		super(msg);
	}
	
	public BreakStringRuleException(StringRule stringRule) {
		this(stringRule.getMessage());
		this.stringRule = stringRule;
	}

}
