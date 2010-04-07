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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.software.bird.common.util.BeanUtil;
import org.software.bird.common.util.CheckUtil;
import org.software.bird.rule.BreakStringRuleException;
import org.software.bird.rule.RuleBugException;
import org.software.bird.rule.StringRule;
import org.software.bird.som.exception.SheetTitleNotFoundException;
import org.software.bird.som.util.BasicObjectParseUtil;
import org.software.bird.som.util.ExcelUtil;

/**
 * 鏍稿績瑙ｆ瀽绫�, 鐢ㄤ簬 sheet-->list, cell-->property鐨勮В鏋�,
 * 骞跺湪瑙ｆ瀽涓繘琛屾牎楠�
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: CoreParseUtil.java,v0.1 2007-12-7 涓嬪崍02:17:22 cyyan Exp$
 */
public class CoreParseUtil {

	final static String sheet_title_not_found_exception = "璇风敤姝ｇ‘鐨勬ā鏉垮鍏�";  
	
	/**
	 * 琛╯heet瑙ｆ瀽鎴恖ist
	 * @param sheet
	 * @param som
	 * @return
	 * @throws BreakStringRuleException
	 * @throws SheetTitleNotFoundException
	 */
	public static List sheet2List(HSSFSheet sheet, SheetObjectMapping som)
			throws BreakStringRuleException, SheetTitleNotFoundException {
		List list = new ArrayList();
		Iterator iterator = sheet.rowIterator();
		
		StringBuffer errorMsg = new StringBuffer("");

		// 鏁版嵁琛屽紑濮嬭В鏋�
		for (int i = 0; i < som.getFirstDataRowIndex(); i++) {
			if (iterator.hasNext()) {
				iterator.next();
			}
		}
		int dataRowMaxNumber = som.getDataRowMaxNumber();
		
		int rowCount = som.getFirstDataRowIndex() + 1;
		int dataCount = 0;
		while (iterator.hasNext()) {
			
			if (dataRowMaxNumber > -1 && dataCount >= dataRowMaxNumber) { //瓒呰繃鏈�澶ф寚瀹氳鏁板氨璺冲嚭
				break;
			}
			dataCount ++;
			
			Object obj = null;
			HSSFRow row = null;

			row = (HSSFRow) iterator.next();
			
			som.excelReference.setRow(row);
			
			try {
				if (ExcelUtil.isEmptyRow(row)) {
					rowCount++;
					continue;
				} else {
					obj = row2obj(row, som);
				}
			} catch (BreakStringRuleException e) {
				errorMsg.append("绗�" + rowCount + "琛�" + e.getMessage() + "<BR>");
				//e.printStackTrace();
			}
			// 鍘绘帀涓嶅拰閫昏緫鐨勬儏鍐�
			if (obj != null) {
				list.add(obj);				
			}
			
			rowCount++;
		}
		if (!errorMsg.toString().equals("")) {
			throw new BreakStringRuleException( "鍦ㄨ〃鍗昜" + som.getSheetName() + "]涓嚭鐜颁笅鍒楅敊璇�"  + "<BR>" + errorMsg.toString() + "<BR>");
		}
		return list;
	}

	/**
	 * 
	 * 灏唕ow瑙ｆ瀽鎴愬璞�
	 * 
	 * @param row
	 * @param som
	 * @return
	 * @throws BreakStringRuleException
	 * @throws SheetTitleNotFoundException
	 */
	public static Object row2obj(HSSFRow row, SheetObjectMapping som)
			throws BreakStringRuleException, SheetTitleNotFoundException {
		Object obj = null;
		Class objClass = som.getObjClass();		
		
		try {
			obj = objClass.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
			return null;
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			return null;
		}

		Map propertyColumnMap = null;		
		propertyColumnMap = som.getPropertyColumnMap();

		HSSFFormulaEvaluator evaluator = ExcelUtil.getFormulaEvaluator(som.excelReference.getSheet(), som.excelReference.getWorkbook(), som.excelReference.getRow());
		
		Set propertySet = propertyColumnMap.keySet();
		Iterator propertyIt = propertySet.iterator();
		StringBuffer errorMsg = new StringBuffer();
		while (propertyIt.hasNext()) {

			String property = (String) propertyIt.next();
			Short columnShort = (Short) propertyColumnMap.get(property);
			if (columnShort == null) {
				throw new SheetTitleNotFoundException("[" + som.getPropertyTitleMap().get(property) +"]娌℃湁鎵惧埌锛�" + sheet_title_not_found_exception);
			}
			short column = columnShort.shortValue();
			
			String cellStr = ExcelUtil.cell2string(row.getCell(column), evaluator);
			cellStr = cellStr == null ? "" : cellStr;
			StringRule stringRule = (StringRule) som.getPropertyStringRuleMap().get(property);
			if (CheckUtil.isNotNull(stringRule)) {
				try {
					stringRule.test(cellStr);
				} catch(BreakStringRuleException e) {
					errorMsg.append("[" + som.getPropertyTitleMap().get(property) +"]杈撳叆涓嶅悎娉曪紝" + stringRule.getMessage() + "瀹為檯杈撳叆鏄細" + cellStr + "<BR>");
					continue;
				}
			}

			Class propertyClass = null;
			try {
				propertyClass = BeanUtil.getPropertyClassOfBean(obj, property);
			} catch (SecurityException e1) {
				e1.printStackTrace();
				return null;
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
				return null;
			}
			Object value = null;
			if (cellStr == null || cellStr.trim().equals("")) {
				value = null;
			} else {
				try {
					value = BasicObjectParseUtil.parase(cellStr.trim(), propertyClass);
				} catch(RuleBugException e) {
					e.printStackTrace();
					if (CheckUtil.isNotNull(stringRule)) {
						errorMsg.append("鍦╗" + som.getPropertyTitleMap().get(property) +"]鏈夎緭鍏ラ敊璇� 锛�" + stringRule.getMessage() + "<BR>");
					} else {
						errorMsg.append("鍦╗" + som.getPropertyTitleMap().get(property) +"]鏈夎緭鍏ラ敊璇細璇锋鏌�<BR>");
					}
					continue;
				}
			}

			try {
				BeanUtil.setPropertyValueOfBean(obj, property, value);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		if (!errorMsg.toString().equals("")) {
			throw new BreakStringRuleException( errorMsg.toString());
		}
		return obj;
	}

	/*public static Object cell2property(HSSFCell cell, Field fild)
			throws RuleBugException {
		Object value = null;
		String cellStr = ExcelUtil.cell2string(cell).trim();
		if (cellStr == null || cellStr.trim().equals("")) {
			value = null;
		} else {
			value = BasicObjectParseUtil.parase(cellStr, fild.getType());
		}

		return value;
	}*/

}
