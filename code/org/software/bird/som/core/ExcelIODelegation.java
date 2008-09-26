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
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelParseProxy.java,v0.1 2007-12-8 ����01:43:43 cyyan Exp$
 */
public class ExcelIODelegation {
	
	public static String sheet_not_found_exception_message = "�����Ƿ�����ȷ��ģ�壬�����������Ƿ񱻸Ķ�";
	
	private ExcelDelegation excelDelegation;

	public ExcelIODelegation(InputStream stream) throws FileNotFoundException, IOException, Exception{
		excelDelegation = new ExcelDelegation(stream);
	}
	
	public List parseAll(String sheetName, Class objClass) throws SheetNotFoundException, BreakStringRuleException, SheetTitleNotFoundException {
		List list = null;
		SheetObjectMapping som = SheetObjectMappingConfig.getSOM(objClass);
		if (som == null) {
			throw new RuntimeException("û���ҵ�" + objClass + "���som���� ȷ��som�ļ����ڣ����ұ����ص���sheet_object_config.xml�ļ���");
		}
		if (sheetName == null || sheetName.trim().equals("")) {//���û��ָ��sheet����ʹ�������ļ��е�
			sheetName = som.getSheetName();
		} else {		//���ָ��sheet���ͽ�som��sheet����ֵλ ָ���� sheet��
			som.setSheetName(sheetName);
		}
		HSSFSheet sheet = excelDelegation.getSheet(som.getSheetName());
		
		//set the excel reference
		som.excelReference.setWorkbook(excelDelegation.getWorkbook());
		som.excelReference.setSheet(sheet);
		
		if (sheet == null) {
			throw new SheetNotFoundException("������[" + sheetName + "]�����ڣ�" + sheet_not_found_exception_message);
		}
		Map titleColumnMap = SheetObjectMappingConfig.configTitleColumnMap(sheet.getRow(som.getTitleRowIndex()));
		som.setTitleColumnMap(titleColumnMap);
		som.configPropertyColumnMap();  //���µ�excel����title--column����
		list = CoreParseUtil.sheet2List(sheet, som);
		return list;
	}

	public List parseByExample(Object obj) {
		return null;
	}

	public Object parseByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
}