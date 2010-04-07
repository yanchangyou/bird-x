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


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.software.bird.common.exception.NotUniqueException;
import org.software.bird.rule.BreakStringRuleException;
import org.software.bird.som.exception.SheetNotFoundException;
import org.software.bird.som.exception.SheetTitleNotFoundException;

/**
 * 鎺ュ彛ExcelIO鐨勫唴閮ㄤ唬鐞�, ExcelIO鎵�鏈夊姛鑳介兘鏄皟鐢‥xcelIODelegation
 * 浣跨敤浠ｇ悊澧炲姞浜嗙伒娲绘��
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelParseProxy.java,v0.1 2007-12-8 涓嬪崍01:43:43 cyyan Exp$
 */
public class ExcelIODelegation {
	/**
	 * 宸ヤ綔琛ㄦ病鏈夊彂鐜板紓甯�
	 */
	public static String sheet_not_found_exception_message = "璇锋鏌ユ槸鍚︽槸姝ｇ‘鐨勬ā鏉匡紝琛ㄥ崟鐨勫悕瀛楁槸鍚﹁鏀瑰姩";
	/**
	 * excel浠ｇ悊
	 */
	private ExcelDelegation excelDelegation;

	/**
	 * 鏋勯�犲嚱鏁�
	 * @param stream
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public ExcelIODelegation(InputStream stream) throws FileNotFoundException, IOException, Exception{
		excelDelegation = new ExcelDelegation(stream);
	}
	
	
	/**
	 * 瑙ｆ瀽鎸噑heet鎴恖ist
	 * @param sheetName
	 * @param objClass
	 * @return
	 * @throws SheetNotFoundException
	 * @throws BreakStringRuleException
	 * @throws SheetTitleNotFoundException
	 */
	public List parseAll(String sheetName, Class objClass) throws SheetNotFoundException, BreakStringRuleException, SheetTitleNotFoundException {
		List list = null;
		SheetObjectMapping som = SheetObjectMappingConfig.getSOM(objClass);
		if (som == null) {
			throw new RuntimeException("娌℃湁鎵惧埌" + objClass + "绫荤殑som瀵硅薄锛� 纭繚som鏂囦欢瀛樺湪锛屽苟涓旇鍔犺浇鍒颁簡sheet_object_config.xml鏂囦欢涓�");
		}
		if (sheetName == null || sheetName.trim().equals("")) {//濡傛灉娌℃湁鎸囧畾sheet鍚嶅氨浣跨敤閰嶇疆鏂囦欢涓殑
			sheetName = som.getSheetName();
		} else {		//濡傛灉鎸囧畾sheet鍚嶅氨灏唖om鐨剆heet鍚嶈祴鍊间綅 鎸囧畾鐨� sheet鍚�
			som.setSheetName(sheetName);
		}
		HSSFSheet sheet = excelDelegation.getSheet(som.getSheetName());
		
		//set the excel reference
		som.excelReference.setWorkbook(excelDelegation.getWorkbook());
		som.excelReference.setSheet(sheet);
		
		if (sheet == null) {
			throw new SheetNotFoundException("琛ㄥ崟锛歔" + sheetName + "]涓嶅瓨鍦紝" + sheet_not_found_exception_message);
		}
		Map titleColumnMap = SheetObjectMappingConfig.configTitleColumnMap(sheet.getRow(som.getTitleRowIndex()));
		som.setTitleColumnMap(titleColumnMap);
		som.configPropertyColumnMap();  //瀵规柊鐨別xcel杩涜title--column閰嶇疆
		list = CoreParseUtil.sheet2List(sheet, som);
		return list;
	}

	/**
	 * 鏈疄鐜�
	 * @param obj
	 * @return
	 */
	public List parseByExample(Object obj) {
		return null;
	}

	/**
	 * 鏈疄鐜�
	 * @param obj
	 * @return
	 */
	public Object parseByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
}
