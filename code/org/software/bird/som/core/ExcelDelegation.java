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
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelDelegation.java,v0.1 2007-12-15 ÏÂÎç04:00:56 cyyan Exp$
 */
public class ExcelDelegation {

	final private Map name_sheet_map = new HashMap();

	public HSSFWorkbook workbook;

	public ExcelDelegation(InputStream stream) throws FileNotFoundException,
			IOException, Exception {
		configNameSheetMap(stream);
	}

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

	public HSSFSheet getSheet(String sheetName) {
		return (HSSFSheet) name_sheet_map.get(sheetName);
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

}
