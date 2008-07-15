/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.som.util;

import org.w3c.dom.Node;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: MappingUtil.java,v0.1 2007-12-6 下午02:17:30 cyyan Exp$
 */
public class SheetObjectMappingUtil {

	public static int getFirstDataRowIndex(Node classNode) {
		String firstDataRowIndexStr = XMLUtil.getNodePropertyValue(classNode,"firstDataRowIndex");
		int firstDataRowIndex = firstDataRowIndexStr == null ? 1 : Integer.parseInt(firstDataRowIndexStr)-1;
		return firstDataRowIndex;
	}

	public static int getTitleRowIndex(Node classNode) {
		String titleRowIndexStr = XMLUtil.getNodePropertyValue(classNode,"titleRowIndex");
		int titleRowIndex = titleRowIndexStr == null ? 0 : Integer.parseInt(titleRowIndexStr)-1;
		return titleRowIndex;
	}

	public static int getDataRowMaxNumber(Node classNode) {
		String dataRowMaxNumberStr = XMLUtil.getNodePropertyValue(classNode,"dataRowMaxNumber");
		int dataRowMaxNumber = dataRowMaxNumberStr == null ? -1 : Integer.parseInt(dataRowMaxNumberStr);
		return dataRowMaxNumber;
	}

}
