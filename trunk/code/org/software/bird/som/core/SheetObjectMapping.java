/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.som.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.software.bird.common.util.CommonUtil;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SheetObjectMapping.java,v0.1 2007-12-5 下午03:42:01 cyyan Exp$
 */
public class SheetObjectMapping {

	public ExcelReference excelReference = new ExcelReference();

	private String sheetName;

	private Class objClass;

	private String configFileName;

	private int titleRowIndex = 0;

	private int firstDataRowIndex = 1;

	private int dataRowMaxNumber = -1;

	private Map propertyTitleMap;

	private Map titleColumnMap;

	private Map propertyColumnMap;

	private Map propertyStringRuleMap;

	public Class getObjClass() {
		return objClass;
	}

	public void setObjClass(Class objClass) {
		this.objClass = objClass;
	}

	public Map getPropertyTitleMap() {
		return propertyTitleMap;
	}

	public void setPropertyTitleMap(Map propertyTitleMap) {
		this.propertyTitleMap = propertyTitleMap;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Map getTitleColumnMap() {
		return titleColumnMap;
	}

	public void setTitleColumnMap(Map titleColumnMap) {
		this.titleColumnMap = titleColumnMap;
	}

	public String toString() {
		return sheetName + ":\n" + titleColumnMap + "\n" + objClass + ":\n"
				+ propertyTitleMap;
	}

	public Map getPropertyColumnMap() {
		if (propertyColumnMap == null) {
			configPropertyColumnMap();
		}
		return propertyColumnMap;
	}

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

	public int getFirstDataRowIndex() {
		return firstDataRowIndex;
	}

	public void setFirstDataRowIndex(int firstDataRowIndex) {
		this.firstDataRowIndex = firstDataRowIndex;
	}

	public int getTitleRowIndex() {
		return titleRowIndex;
	}

	public void setTitleRowIndex(int titleRowIndex) {
		this.titleRowIndex = titleRowIndex;
	}

	public String getConfigFileName() {
		if (configFileName == null) {
			initConfigFileName();
		}
		return configFileName;
	}

	private void initConfigFileName() {
		configFileName = CommonUtil.getPackagePath(objClass) + "\\"
				+ CommonUtil.getClassNameWithoutPackage(objClass) + ".som.xml";
	}

	public Map getPropertyStringRuleMap() {
		return propertyStringRuleMap;
	}

	public void setPropertyStringRuleMap(Map propertyStringRuleMap) {
		this.propertyStringRuleMap = propertyStringRuleMap;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public void setPropertyColumnMap(Map propertyColumnMap) {
		this.propertyColumnMap = propertyColumnMap;
	}

	public int getDataRowMaxNumber() {
		return dataRowMaxNumber;
	}

	public void setDataRowMaxNumber(int dataRowMaxNumber) {
		this.dataRowMaxNumber = dataRowMaxNumber;
	}

	class ExcelReference {

		public HSSFWorkbook workbook;

		public HSSFSheet sheet;

		public HSSFRow row;

		public HSSFRow getRow() {
			return row;
		}

		public void setRow(HSSFRow row) {
			this.row = row;
		}

		public HSSFSheet getSheet() {
			return sheet;
		}

		public void setSheet(HSSFSheet sheet) {
			this.sheet = sheet;
		}

		public HSSFWorkbook getWorkbook() {
			return workbook;
		}

		public void setWorkbook(HSSFWorkbook workbook) {
			this.workbook = workbook;
		}

	}
}
