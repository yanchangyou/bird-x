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

package org.software.bird.som.util;

import org.w3c.dom.Node;

/**
 * 配置som的使用类
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: MappingUtil.java,v0.1 2007-12-6 下午02:17:30 cyyan Exp$
 */
public class SheetObjectMappingUtil {

	/**
	 * 获取第一个数据行的位置
	 * @param classNode
	 * @return
	 */
	public static int getFirstDataRowIndex(Node classNode) {
		String firstDataRowIndexStr = XMLUtil.getNodePropertyValue(classNode,"firstDataRowIndex");
		int firstDataRowIndex = firstDataRowIndexStr == null ? 1 : Integer.parseInt(firstDataRowIndexStr)-1;
		return firstDataRowIndex;
	}

	/**
	 * 获取标题行位置
	 * @param classNode
	 * @return
	 */
	public static int getTitleRowIndex(Node classNode) {
		String titleRowIndexStr = XMLUtil.getNodePropertyValue(classNode,"titleRowIndex");
		int titleRowIndex = titleRowIndexStr == null ? 0 : Integer.parseInt(titleRowIndexStr)-1;
		return titleRowIndex;
	}

	/**
	 * 获取数据的最大行数
	 * @param classNode
	 * @return
	 */
	public static int getDataRowMaxNumber(Node classNode) {
		String dataRowMaxNumberStr = XMLUtil.getNodePropertyValue(classNode,"dataRowMaxNumber");
		int dataRowMaxNumber = dataRowMaxNumberStr == null ? -1 : Integer.parseInt(dataRowMaxNumberStr);
		return dataRowMaxNumber;
	}

}
