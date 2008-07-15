/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
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
				str = "" + new BigDecimal(String.valueOf(cell.getNumericCellValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
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
