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
 * 澶勭悊excel鐨勭浉鍏冲嚱鏁�
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelUtil.java,v0.1 2007-12-6 涓嬪崍01:46:38 cyyan Exp$
 */
public class ExcelUtil {

	final public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 鑾峰彇琛ㄥ崟涓殑title 琛宲oi瀵硅薄
	 * @param sheet
	 * @param titleRowIndex
	 * @return
	 */
	public static HSSFRow getSheetTitleRow(HSSFSheet sheet, int titleRowIndex) {
		return sheet.getRow(titleRowIndex);
	}
	
	
	/**
	 * 鍒ゆ柇琛ㄥ崟涓竴琛屾槸鍚︿负绌�
	 * 濡傛灉涓虹┖灏变笉鐢ㄨВ鏋�, 鐢ㄤ簬鎺掗櫎excel涓┖鐧借
	 * @param row
	 * @return
	 */
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

	/**
	 * 鍒ゆ柇鏄惁鏄┖鍗曞厓鏍�
	 * @param cell
	 * @return
	 */
	public static boolean isEmptyCell(HSSFCell cell) {
		String cellStr = null;
		cellStr = cell2string(cell, null);
		return cellStr == null || cellStr.trim().equals("");
	}

	/**
	 * 瀵瑰崟鍏冩牸鐨勬暟鎹浆鎹㈡垚瀛楃涓�, 浠ヤ究鍚庨潰缁熶竴澶勭悊
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
				String numberStr = String.valueOf(cell.getNumericCellValue());
				
				//modify by cyyan 2008-09-23 19:17:28 
				//excel涓暟鍊艰浆鎹㈣繃鏉ユ椂閲囩敤E璁℃暟娉�, 瀵艰嚧鍚庨潰鐨勮鍒欐牎楠屽け璐�;
				//涓烘杩涜鎶奅璁℃暟娉曡浆鎹㈡櫘閫氳鏁版硶, 骞朵笖浣跨敤灏忔暟鐐瑰悗15浣�, (15浣嶆槸灏忔暟鐨勬渶澶х簿搴�, 鑳藉淇濊瘉闈炴湰缁勪欢甯︽潵鐨勮宸�)
				// 浣跨敤15浣嶅皬鏁板悗, 浼氬甫鏉ュ涓湯灏剧殑0, 鐩搁偦鐨勪竴鏉¤鍙ユ槸鍘绘帀杩欎簺鏈熬0
				str = "" + new BigDecimal(numberStr).setScale(15, BigDecimal.ROUND_HALF_UP);
				
				//modify yanchangyou 2008-09-26 18:01:43 
				//鍘熸潵鐨勬儏鍐靛彧鑳藉幓鎺� .0000* 杩欑妯″紡, 淇敼鍚庤兘鍘绘帀鏈熬鐨�0, 濡傛灉鏈熬绱ц繛鎺�
				if (str.indexOf('.') != -1) {
					str = str.replaceAll("(\\.)?0*$", "");
				}				

				/*
				 * 蹇�熷幓鎺夊皬鏁扮偣鍚庢湯灏剧殑闆�, 鍜屽皬鏁扮偣鍚庡叏韬浂鐨勬儏鍐�
				 */
//				if (str.indexOf('.') != -1) { //鍙鐞嗘湁灏忔暟鐐�
//					int index = str.length();
//					for (int i = str.length()-1; i > -1; i--) {
//						if (str.charAt(i) == '0') {
//							index = i;
//						} else if (str.charAt(i) == '.'){
//							index = i;
//							break;
//						} else {
//							break;
//						}
//					}
//					str = str.substring(0, index);
//				}
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



	/**
	 * 鑾峰彇excel涓殑鏂圭▼
	 * @param sheet
	 * @param workbook
	 * @param row
	 * @return
	 */
	public static HSSFFormulaEvaluator getFormulaEvaluator(HSSFSheet sheet, HSSFWorkbook workbook, HSSFRow row) {
		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, workbook);
		evaluator.setCurrentRow(row);
		return evaluator;
	}
}
