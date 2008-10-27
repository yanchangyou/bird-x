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
 * 接口ExcelIO的内部代理, ExcelIO所有功能都是调用ExcelIODelegation
 * 使用代理增加了灵活性
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelParseProxy.java,v0.1 2007-12-8 下午01:43:43 cyyan Exp$
 */
public class ExcelIODelegation {
	/**
	 * 工作表没有发现异常
	 */
	public static String sheet_not_found_exception_message = "请检查是否是正确的模板，表单的名字是否被改动";
	/**
	 * excel代理
	 */
	private ExcelDelegation excelDelegation;

	/**
	 * 构造函数
	 * @param stream
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public ExcelIODelegation(InputStream stream) throws FileNotFoundException, IOException, Exception{
		excelDelegation = new ExcelDelegation(stream);
	}
	
	
	/**
	 * 解析指sheet成list
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
			throw new RuntimeException("没有找到" + objClass + "类的som对象， 确保som文件存在，并且被加载到了sheet_object_config.xml文件中");
		}
		if (sheetName == null || sheetName.trim().equals("")) {//如果没有指定sheet名就使用配置文件中的
			sheetName = som.getSheetName();
		} else {		//如果指定sheet名就将som的sheet名赋值位 指定的 sheet名
			som.setSheetName(sheetName);
		}
		HSSFSheet sheet = excelDelegation.getSheet(som.getSheetName());
		
		//set the excel reference
		som.excelReference.setWorkbook(excelDelegation.getWorkbook());
		som.excelReference.setSheet(sheet);
		
		if (sheet == null) {
			throw new SheetNotFoundException("表单：[" + sheetName + "]不存在，" + sheet_not_found_exception_message);
		}
		Map titleColumnMap = SheetObjectMappingConfig.configTitleColumnMap(sheet.getRow(som.getTitleRowIndex()));
		som.setTitleColumnMap(titleColumnMap);
		som.configPropertyColumnMap();  //对新的excel进行title--column配置
		list = CoreParseUtil.sheet2List(sheet, som);
		return list;
	}

	/**
	 * 未实现
	 * @param obj
	 * @return
	 */
	public List parseByExample(Object obj) {
		return null;
	}

	/**
	 * 未实现
	 * @param obj
	 * @return
	 */
	public Object parseByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
}
