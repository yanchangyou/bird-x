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
 * @version $Id: RuleBugException.java,v0.1 2007-12-16 下午05:11:31 cyyan Exp$
 */
public class RuleBugException extends Exception {

	private static final long serialVersionUID = -8876172406935676701L;
	
	public RuleBugException(String message) {
		super(message);
	}
	
	public static final String rule_bug_report_pre = "this is a rule bug, please check the rule and rewrite it; \nreason : " ;
}
