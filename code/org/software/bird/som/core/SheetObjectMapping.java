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
 * ��Ӧ�����ļ�����<br>
 * 
 * ÿ��som��ǩ����������һ��SheetObjectMappingʵ����Ȼ��������som����excel��<br>
 * ��һ���н�ʹ��proterpty�����Map
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SheetObjectMapping.java,v0.1 2007-12-5 ����03:42:01 cyyan Exp$
 */
public class SheetObjectMapping {

	/**
	 * ��excel������, ����excel�ķ��̣���ʽ����workbook����, �˱������þ�������ȫ�����õ�
	 * ���ⲻ��һ�ֺܺõĴ���ʽ���д����ƣ�����������ְ��ƥ�䣩
	 */
	public ExcelReference excelReference = new ExcelReference();

	/**
	 * Ҫӳ�����
	 */
	private Class objClass;	

	/**
	 * ��������
	 */
	private String sheetName;

	/**
	 * �ڹ������б����е��к� 1-based����1��ʼ����
	 */
	private int titleRowIndex = 0;

	/**
	 * �ڹ������е�һ�������У� 1-based����1��ʼ����
	 */
	private int firstDataRowIndex = 1;

	/**
	 * �ڹ����������ݶ�ȡ���������
	 */
	private int dataRowMaxNumber = -1;

	/**
	 * ��������Ե����������ͷ��ӳ��
	 */
	private Map propertyTitleMap;

	/**
	 * ��������ͷ���������е�ӳ��
	 */
	private Map titleColumnMap;

	/**
	 * �������Ե��������е�ӳ��
	 */
	private Map propertyColumnMap;

	/**
	 * ���Ե��ַ�����ӳ��
	 */
	private Map propertyStringRuleMap;

	/**
	 * ��ȡ�����Class
	 * 
	 * @return ���ض����Class
	 */
	public Class getObjClass() {
		return objClass;
	}

	/**
	 * ���ö����Class
	 * 
	 * @param objClass Ҫ���õ�Class
	 */
	public void setObjClass(Class objClass) {
		this.objClass = objClass;
	}

	/**
	 * ��ȡ�������ԣ�property������������ͷ��ӳ��
	 * @return ����ӳ��
	 */
	public Map getPropertyTitleMap() {
		return propertyTitleMap;
	}

	/**
	 * ���ö������Ե���������ͷ��ӳ��
	 * @param propertyTitleMap
	 */
	public void setPropertyTitleMap(Map propertyTitleMap) {
		this.propertyTitleMap = propertyTitleMap;
	}

	/**
	 * ��ȡ�����������
	 * @return �����������
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * ���ù����������
	 * @param sheetName �����������
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * ��ȡ��������ͷ���е�ӳ��
	 * @return ��������ͷ���е�ӳ��
	 */
	public Map getTitleColumnMap() {
		return titleColumnMap;
	}

	/**
	 * ���ù�������ͷ���е�ӳ��
	 * @param titleColumnMap ��������ͷ���е�ӳ��
	 */
	public void setTitleColumnMap(Map titleColumnMap) {
		this.titleColumnMap = titleColumnMap;
	}

	/**
	 * ת��λ�ַ���
	 */
	public String toString() {
		return sheetName + ":\n" + titleColumnMap + "\n" + objClass + ":\n"
				+ propertyTitleMap;
	}

	/**
	 * ��ȡ�����е�ӳ��
	 * @return
	 */
	public Map getPropertyColumnMap() {
		if (propertyColumnMap == null) {
			configPropertyColumnMap();
		}
		return propertyColumnMap;
	}

	/**
	 * �������Ե��е�ӳ��
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
	 * ��ȡ��һ�������ڹ����������
	 * @return ����
	 */
	public int getFirstDataRowIndex() {
		return firstDataRowIndex;
	}

	/**
	 * ���õ�һ��������λ��
	 * @param firstDataRowIndex
	 */
	public void setFirstDataRowIndex(int firstDataRowIndex) {
		this.firstDataRowIndex = firstDataRowIndex;
	}

	/**
	 * ��ȡ��������ͷ��λ��
	 * @return �����������λ��
	 */
	public int getTitleRowIndex() {
		return titleRowIndex;
	}

	/**
	 * ���ù���������ͷ�е�λ��
	 * @param titleRowIndex ��ͷ�е�λ��
	 */
	public void setTitleRowIndex(int titleRowIndex) {
		this.titleRowIndex = titleRowIndex;
	}

	/**
	 * ��ȡ�����ַ�����ӳ��map
	 * @return �����ַ�����ӳ��map
	 */
	public Map getPropertyStringRuleMap() {
		return propertyStringRuleMap;
	}

	/**
	 * ���������ַ�����ӳ��map
	 * @param propertyStringRuleMap �����ַ�����ӳ��map
	 */
	public void setPropertyStringRuleMap(Map propertyStringRuleMap) {
		this.propertyStringRuleMap = propertyStringRuleMap;
	}
	
	/**
	 * ���ö������Ե��������е�ӳ��
	 * @param propertyColumnMap �������Ե��������е�ӳ��
	 */
	public void setPropertyColumnMap(Map propertyColumnMap) {
		this.propertyColumnMap = propertyColumnMap;
	}

	/**
	 * ��ȡָ���ܻ�ȡ�������������
	 * @return �������
	 */
	public int getDataRowMaxNumber() {
		return dataRowMaxNumber;
	}

	/**
	 * ����ָ���ܻ�ȡ�������������
	 * @param dataRowMaxNumber ָ���ܻ�ȡ�������������
 	 */
	public void setDataRowMaxNumber(int dataRowMaxNumber) {
		this.dataRowMaxNumber = dataRowMaxNumber;
	}

	/**
	 * �ṩexcel��ȫ������, ��������ֻ����һ��excel�ڲ�ʹ��
	 * @author cyyan
	 *
	 */
	class ExcelReference {

		/**
		 * poi�й���������
		 */
		public HSSFWorkbook workbook;

		/**
		 * poi�й���������
		 */
		public HSSFSheet sheet;

		/**
		 * poi���ж���
		 */
		public HSSFRow row;

		/**
		 * ��ȡpoi�ж���
		 * @return
		 */
		public HSSFRow getRow() {
			return row;
		}

		/**
		 * ����poi�ж���
		 * @param row poi�ж���
		 */
		public void setRow(HSSFRow row) {
			this.row = row;
		}

		/**
		 * ��ȡpoi���������
		 * @return poi����������
		 */
		public HSSFSheet getSheet() {
			return sheet;
		}

		/**
		 * ����poi����������
		 * @param sheet poi����������
		 */
		public void setSheet(HSSFSheet sheet) {
			this.sheet = sheet;
		}

		/**
		 * ��ȡpoi����������
		 * @return poi����������
		 */
		public HSSFWorkbook getWorkbook() {
			return workbook;
		}

		/**
		 * ����poi����������
		 * @param workbook poi����������
		 */
		public void setWorkbook(HSSFWorkbook workbook) {
			this.workbook = workbook;
		}

	}
}
