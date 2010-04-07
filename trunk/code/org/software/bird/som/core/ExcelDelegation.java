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
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.software.bird.som.util.ExcelParserUtil;

/**
 * excel鏂囦欢瀵瑰簲鐨勫璞�, 浠ｇ悊excel鏂囦欢, 杩欐牱鏇村叿鏈夌伒娲绘��
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelDelegation.java,v0.1 2007-12-15 涓嬪崍04:00:56 cyyan Exp$
 */
public class ExcelDelegation {


	/**
	 * 鍛戒腑宸ヤ綔琛ㄦ槧灏�
	 */
	final private Map name_sheet_map = new HashMap();

	/**
	 * 宸ヤ綔钖�
	 */
	public HSSFWorkbook workbook;

	/**
	 * 鏋勯�犲嚱鏁�
	 * @param stream
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public ExcelDelegation(InputStream stream) throws FileNotFoundException,
			IOException, Exception {
		configNameSheetMap(stream);
	}

	/**
	 * 
	 * 閰嶇疆鍚嶇О鍒皊heet鐨勬槧灏�
	 * 
	 * @param stream
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	private void configNameSheetMap(InputStream stream)
			throws FileNotFoundException, IOException, Exception {
		if (stream == null) {
			throw new NullPointerException("error! stream is null point");
		}
		HSSFWorkbook wb = null;
		wb = ExcelParserUtil.getWorkbook(stream);
		workbook = wb;
		int sheetNumber = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			String sheetName = wb.getSheetName(i);
			HSSFSheet sheet = wb.getSheetAt(i);
			name_sheet_map.put(sheetName, sheet);
		}
		stream.close();
	}

	/**
	 * 
	 * 鎸夊悕鑾峰彇poi鐨勮〃鍗�
	 * 
	 * @param sheetName
	 * @return
	 */
	public HSSFSheet getSheet(String sheetName) {
		return (HSSFSheet) name_sheet_map.get(sheetName);
	}

	/**
	 * 鑾峰彇宸ヤ綔钖�
	 * @return
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * 璁剧疆宸ヤ綔钖�
	 * @param workbook
	 */
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

}
