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

package org.software.bird.som;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.software.bird.common.exception.NotUniqueException;
import org.software.bird.rule.BreakStringRuleException;
import org.software.bird.som.core.ExcelIODelegation;
import org.software.bird.som.exception.InValidExcelFileException;
import org.software.bird.som.exception.SheetNotFoundException;
import org.software.bird.som.exception.SheetTitleNotFoundException;


/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConvertUtil.java,v0.1 2007-12-6 下午01:47:14 cyyan Exp$
 */
public class ExcelIO {

	private ExcelIODelegation excelIODelegation;

	public static String excel_read_exception_message = "请用正确的模板导入";
	
	public ExcelIO(String excelFileName) throws Exception {
		this(new File(excelFileName));
	}

	public ExcelIO(File excelFile) throws Exception {
		this(new FileInputStream(excelFile));
	}

	public ExcelIO(InputStream excelInputStream) throws InValidExcelFileException  {
		if (excelInputStream == null) {
			throw new InValidExcelFileException(excel_read_exception_message);
		}
		try {
			excelIODelegation = new ExcelIODelegation(excelInputStream);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new InValidExcelFileException(excel_read_exception_message);
		} catch (IOException e) {
			e.printStackTrace();
			throw new InValidExcelFileException(excel_read_exception_message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InValidExcelFileException(excel_read_exception_message);
		}
	}
	public List readAll(Class objClass) throws SheetNotFoundException, BreakStringRuleException, SheetTitleNotFoundException  {
		List list = null;
		list = excelIODelegation.parseAll(null, objClass);
		return list;
	}
	
	public List readAll(String sheetName, Class objClass) throws SheetNotFoundException, BreakStringRuleException, SheetTitleNotFoundException  {
		List list = null;
		list = excelIODelegation.parseAll(sheetName, objClass);
		return list;
	}

	public List readByExample(Object obj) {
		return null;
	}

	public Object readByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
	
	public void write(List objList) {
		
	}
	
	public void write(Object obj) {
		
	}
}
