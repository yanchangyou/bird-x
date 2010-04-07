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

package org.software.bird.som.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 对应配置文件的类<br>
 * 
 * 每个som标签都被解析成一个SheetObjectMapping实例，然后程序根据som解析excel。<br>
 * 下一版中将使用proterpty类代替Map
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SheetObjectMapping.java,v0.1 2007-12-5 下午03:42:01 cyyan Exp$
 */
public class SheetObjectMapping {

	/**
	 * 对excel的引用, 由于excel的方程，样式等由workbook引用, 此变量作用就是用于全局引用的
	 * （这不是一种很好的处理方式，有待改善，它与这个类的职责不匹配）
	 */
	public ExcelReference excelReference = new ExcelReference();

	/**
	 * 要映射的类
	 */
	private Class objClass;	

	/**
	 * 工作表名
	 */
	private String sheetName;

	/**
	 * 在工作表中标题行的行号 1-based，以1开始计数
	 */
	private int titleRowIndex = 0;

	/**
	 * 在工作表中第一条数据行， 1-based，以1开始计数
	 */
	private int firstDataRowIndex = 1;

	/**
	 * 在工作表中数据读取的最多行数
	 */
	private int dataRowMaxNumber = -1;

	/**
	 * 对象的属性到工作表的列头的映射
	 */
	private Map propertyTitleMap;

	/**
	 * 工作表列头到工作表列的映射
	 */
	private Map titleColumnMap;

	/**
	 * 对象属性到工作表列的映射
	 */
	private Map propertyColumnMap;

	/**
	 * 属性的字符规则映射
	 */
	private Map propertyStringRuleMap;

	/**
	 * 获取对象的Class
	 * 
	 * @return 返回对象的Class
	 */
	public Class getObjClass() {
		return objClass;
	}

	/**
	 * 设置对象的Class
	 * 
	 * @param objClass 要设置的Class
	 */
	public void setObjClass(Class objClass) {
		this.objClass = objClass;
	}

	/**
	 * 获取对象属性（property）到工作表列头的映射
	 * @return 返回映射
	 */
	public Map getPropertyTitleMap() {
		return propertyTitleMap;
	}

	/**
	 * 设置对象属性到工作表列头的映射
	 * @param propertyTitleMap
	 */
	public void setPropertyTitleMap(Map propertyTitleMap) {
		this.propertyTitleMap = propertyTitleMap;
	}

	/**
	 * 获取工作表的名称
	 * @return 工作表的名称
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * 设置工作表的名称
	 * @param sheetName 工作表的名称
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 获取工作表列头到列的映射
	 * @return 工作表列头到列的映射
	 */
	public Map getTitleColumnMap() {
		return titleColumnMap;
	}

	/**
	 * 设置工作表列头到列的映射
	 * @param titleColumnMap 工作表列头到列的映射
	 */
	public void setTitleColumnMap(Map titleColumnMap) {
		this.titleColumnMap = titleColumnMap;
	}

	/**
	 * 转换位字符串
	 */
	public String toString() {
		return sheetName + ":\n" + titleColumnMap + "\n" + objClass + ":\n"
				+ propertyTitleMap;
	}

	/**
	 * 获取属性列的映射
	 * @return
	 */
	public Map getPropertyColumnMap() {
		if (propertyColumnMap == null) {
			configPropertyColumnMap();
		}
		return propertyColumnMap;
	}

	/**
	 * 配置属性到列的映射
	 *
	 */
	public void configPropertyColumnMap() {
		propertyColumnMap = new HashMap();
		Set propertySet = propertyTitleMap.keySet();
		Iterator propertyIt = propertySet.iterator();
		while (propertyIt.hasNext()) {
			Object property = propertyIt.next();
			Object column = titleColumnMap.get(propertyTitleMap.get(property));
			propertyColumnMap.put(property, column);
		}
	}

	/**
	 * 获取第一行数据在工作表的行数
	 * @return 行数
	 */
	public int getFirstDataRowIndex() {
		return firstDataRowIndex;
	}

	/**
	 * 设置第一个数据行位置
	 * @param firstDataRowIndex
	 */
	public void setFirstDataRowIndex(int firstDataRowIndex) {
		this.firstDataRowIndex = firstDataRowIndex;
	}

	/**
	 * 获取工作表列头行位置
	 * @return 工作表标题行位置
	 */
	public int getTitleRowIndex() {
		return titleRowIndex;
	}

	/**
	 * 设置工作表中列头行的位置
	 * @param titleRowIndex 列头行的位置
	 */
	public void setTitleRowIndex(int titleRowIndex) {
		this.titleRowIndex = titleRowIndex;
	}

	/**
	 * 获取属性字符规则映射map
	 * @return 属性字符规则映射map
	 */
	public Map getPropertyStringRuleMap() {
		return propertyStringRuleMap;
	}

	/**
	 * 设置属性字符规则映射map
	 * @param propertyStringRuleMap 属性字符规则映射map
	 */
	public void setPropertyStringRuleMap(Map propertyStringRuleMap) {
		this.propertyStringRuleMap = propertyStringRuleMap;
	}
	
	/**
	 * 设置对象属性到工作表列的映射
	 * @param propertyColumnMap 对象属性到工作表列的映射
	 */
	public void setPropertyColumnMap(Map propertyColumnMap) {
		this.propertyColumnMap = propertyColumnMap;
	}

	/**
	 * 获取指定能获取的最大数据行数
	 * @return 最大行数
	 */
	public int getDataRowMaxNumber() {
		return dataRowMaxNumber;
	}

	/**
	 * 设置指定能获取的最大数据行数
	 * @param dataRowMaxNumber 指定能获取的最大数据行数
 	 */
	public void setDataRowMaxNumber(int dataRowMaxNumber) {
		this.dataRowMaxNumber = dataRowMaxNumber;
	}

	/**
	 * 提供excel的全局引用, 处理函数等只能是一个excel内部使用
	 * @author cyyan
	 *
	 */
	class ExcelReference {

		/**
		 * poi中工作薄对象
		 */
		public HSSFWorkbook workbook;

		/**
		 * poi中工作薄对象
		 */
		public HSSFSheet sheet;

		/**
		 * poi中行对象
		 */
		public HSSFRow row;

		/**
		 * 获取poi行对象
		 * @return
		 */
		public HSSFRow getRow() {
			return row;
		}

		/**
		 * 设置poi行对象
		 * @param row poi行对象
		 */
		public void setRow(HSSFRow row) {
			this.row = row;
		}

		/**
		 * 获取poi工作表对象
		 * @return poi工作薄对象
		 */
		public HSSFSheet getSheet() {
			return sheet;
		}

		/**
		 * 设置poi工作薄对象
		 * @param sheet poi工作薄对象
		 */
		public void setSheet(HSSFSheet sheet) {
			this.sheet = sheet;
		}

		/**
		 * 获取poi工作薄对象
		 * @return poi工作薄对象
		 */
		public HSSFWorkbook getWorkbook() {
			return workbook;
		}

		/**
		 * 设置poi工作薄对象
		 * @param workbook poi工作薄对象
		 */
		public void setWorkbook(HSSFWorkbook workbook) {
			this.workbook = workbook;
		}

	}
}
