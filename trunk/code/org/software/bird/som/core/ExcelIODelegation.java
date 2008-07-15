/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
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
 * @version $Id: ExcelParseProxy.java,v0.1 2007-12-8 下午01:43:43 cyyan Exp$
 */
public class ExcelIODelegation {
	
	public static String sheet_not_found_exception_message = "请检查是否是正确的模板，表单的名字是否被改动";
	
	private ExcelDelegation excelDelegation;

	public ExcelIODelegation(InputStream stream) throws FileNotFoundException, IOException, Exception{
		excelDelegation = new ExcelDelegation(stream);
	}
	
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

	public List parseByExample(Object obj) {
		return null;
	}

	public Object parseByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
}
