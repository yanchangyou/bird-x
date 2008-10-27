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
import org.software.bird.som.exception.InvalidExcelFileException;
import org.software.bird.som.exception.SheetNotFoundException;
import org.software.bird.som.exception.SheetTitleNotFoundException;


/**
 * SOM组件的接口文件, SOM所有功能都同此类调用<br>
 * 用法如下：<br>
 * ExcelIO aExcelIO = new ExcelIO(excel文件);<br>
 * 将excel文件转换为poi对象,暂存于内存中等待解析<br>
 * List list = aExcelIO.readAll(aClass);<br>
 * 解析excel,并转换为list<br>
 * 
 * 对于文件的传入方式有:<br>
 * 1, 文件名 excelFileName<br>
 * 2, 文件对象 excelFile<br>
 * 3, 文件输入流 excelInputStream<br>
 * 
 * 从excel中读取对象有下列方式：<br>
 * 1, 按类读取<br>
 * 2, 按类和sheet名读取<br>
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConvertUtil.java,v0.1 2007-12-6 下午01:47:14 cyyan Exp$
 */
public class ExcelIO {

	/**
	 * excelIO代理类， 所以的操作都转交个excelIODelegation来做
	 */
	private ExcelIODelegation excelIODelegation;

	/**
	 * excel的异常信息，下一版中将改进
	 */
	public static String excel_read_exception_message = "excel没找到或已损坏, 请检查";
	/**
	 * 通过文件名传递excel文件
	 * @param excelFileName
	 * @throws Exception
	 */
	public ExcelIO(String excelFileName) throws Exception {
		this(new File(excelFileName).exists() ? new File(excelFileName) : null);
	}

	/**
	 * 通过文件对象传递excel文件
	 * @param excelFile
	 * @throws Exception
	 */
	public ExcelIO(File excelFile) throws Exception {
		this(excelFile == null ? null : new FileInputStream(excelFile));
	}

	/**
	 * 通过流对象对象传递excel文件, 在web用于常用此方式
	 * @param excelInputStream
	 * @throws InvalidExcelFileException
	 */
	public ExcelIO(InputStream excelInputStream) throws InvalidExcelFileException  {
		if (excelInputStream == null) {
			throw new InvalidExcelFileException(excel_read_exception_message);
		}
		try {
			excelIODelegation = new ExcelIODelegation(excelInputStream);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new InvalidExcelFileException(excel_read_exception_message);
		} catch (IOException e) {
			e.printStackTrace();
			throw new InvalidExcelFileException(excel_read_exception_message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidExcelFileException(excel_read_exception_message);
		}
	}
	
	/**
	 * 
	 * 解析excel的方式1
	 * 只使用类, 类对应的表单在配置文件中
	 * 
	 * @param objClass 目标对象的类
	 * @return
	 * @throws SheetNotFoundException
	 * @throws BreakStringRuleException
	 * @throws SheetTitleNotFoundException
	 */
	public List readAll(Class objClass) throws SheetNotFoundException, BreakStringRuleException, SheetTitleNotFoundException  {
		List list = null;
		list = excelIODelegation.parseAll(null, objClass);
		return list;
	}
	
	/**
	 * 解析excel的方式2
	 * 直接指定表单名称
	 * 
	 * @param sheetName
	 * @param objClass
	 * @return
	 * @throws SheetNotFoundException
	 * @throws BreakStringRuleException
	 * @throws SheetTitleNotFoundException
	 */
	public List readAll(String sheetName, Class objClass) throws SheetNotFoundException, BreakStringRuleException, SheetTitleNotFoundException  {
		List list = null;
		list = excelIODelegation.parseAll(sheetName, objClass);
		return list;
	}

	/**
	 * 未实现
	 * @param obj
	 * @return
	 */
	public List readByExample(Object obj) {
		return null;
	}

	/**
	 * 未实现
	 * @param obj
	 * @return
	 * @throws NotUniqueException
	 */
	public Object readByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
	/**
	 * 未实现
	 * @param objList
	 */
	public void write(List objList) {
		
	}
	/**
	 * 未实现
	 * @param obj
	 */
	public void write(Object obj) {
		
	}
}
