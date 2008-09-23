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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelUtil.java,v0.1 2007-12-6 下午01:46:38 cyyan Exp$
 */
public class ExcelUtil {

	final public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static HSSFRow getSheetTitleRow(HSSFSheet sheet, int titleRowIndex) {
		return sheet.getRow(titleRowIndex);
	}
	
	
	
	public static boolean isEmptyRow(HSSFRow row) {
		boolean result = true;
		if (row == null) {
			result = true;
		} else {
			for (short i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				HSSFCell cell = row.getCell(i);
				result &= isEmptyCell(cell);
				if (!result) {
					break;
				}
			}
		}
		return result;
	}

	public static boolean isEmptyCell(HSSFCell cell) {
		String cellStr = null;
		cellStr = cell2string(cell, null);
		return cellStr == null || cellStr.trim().equals("");
	}

	/**
	 * 对单元格的数据转换成字符串
	 * 
	 * @param cell
	 * @return
	 */
	public static String cell2string(HSSFCell cell, HSSFFormulaEvaluator evaluator) {
		if (cell == null) {
			return null;
		}
		String str = null;
		final int cellType = cell.getCellType();
	
		switch (cellType) {
		case HSSFCell.CELL_TYPE_STRING:
			str = "" + cell.getRichStringCellValue().getString().trim();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				str = "" + dateFormat.format(cell.getDateCellValue());
			} else {
				//modify by cyyan 2008-09-23 19:17:28 
				//excel中数值转换过来时采用E计数法, 导致后面的规则校验失败;
				//为此进行把E计数法转换普通计数法, 并且使用小数点后15位, (15位是小数的最大精度, 能够保证非本组件带来的误差)
				// 使用15位小数后, 会带来多个末尾的0, 相邻的一条语句是去掉这些末尾0
				str = "" + new BigDecimal(String.valueOf(cell.getNumericCellValue())).setScale(15,BigDecimal.ROUND_HALF_UP);
				str = str.replaceAll("\\.0*$", "");
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			str = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			str = "" + cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			str = "" + cell.getErrorCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			if (evaluator == null) {
				str = "" + cell.getRichStringCellValue().getString();				
			} else {
				str = "" + evaluator.evaluate(cell).getNumberValue();
			}
			
			break;
		}	

		return (str == null || str.trim().equals("")) ? null : str.trim();
	}



	public static HSSFFormulaEvaluator getFormulaEvaluator(HSSFSheet sheet, HSSFWorkbook workbook, HSSFRow row) {
		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, workbook);
		evaluator.setCurrentRow(row);
		return evaluator;
	}
}
