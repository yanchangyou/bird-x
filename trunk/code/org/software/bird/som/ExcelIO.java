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
 * SOM����Ľӿ��ļ�, SOM���й��ܶ�ͬ�������<br>
 * �÷����£�<br>
 * ExcelIO aExcelIO = new ExcelIO(excel�ļ�);<br>
 * ��excel�ļ�ת��Ϊpoi����,�ݴ����ڴ��еȴ�����<br>
 * List list = aExcelIO.readAll(aClass);<br>
 * ����excel,��ת��Ϊlist<br>
 * 
 * �����ļ��Ĵ��뷽ʽ��:<br>
 * 1, �ļ��� excelFileName<br>
 * 2, �ļ����� excelFile<br>
 * 3, �ļ������� excelInputStream<br>
 * 
 * ��excel�ж�ȡ���������з�ʽ��<br>
 * 1, �����ȡ<br>
 * 2, �����sheet����ȡ<br>
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConvertUtil.java,v0.1 2007-12-6 ����01:47:14 cyyan Exp$
 */
public class ExcelIO {

	/**
	 * excelIO�����࣬ ���ԵĲ�����ת����excelIODelegation����
	 */
	private ExcelIODelegation excelIODelegation;

	/**
	 * excel���쳣��Ϣ����һ���н��Ľ�
	 */
	public static String excel_read_exception_message = "excelû�ҵ�������, ����";
	/**
	 * ͨ���ļ�������excel�ļ�
	 * @param excelFileName
	 * @throws Exception
	 */
	public ExcelIO(String excelFileName) throws Exception {
		this(new File(excelFileName).exists() ? new File(excelFileName) : null);
	}

	/**
	 * ͨ���ļ����󴫵�excel�ļ�
	 * @param excelFile
	 * @throws Exception
	 */
	public ExcelIO(File excelFile) throws Exception {
		this(excelFile == null ? null : new FileInputStream(excelFile));
	}

	/**
	 * ͨ����������󴫵�excel�ļ�, ��web���ڳ��ô˷�ʽ
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
	 * ����excel�ķ�ʽ1
	 * ֻʹ����, ���Ӧ�ı��������ļ���
	 * 
	 * @param objClass Ŀ��������
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
	 * ����excel�ķ�ʽ2
	 * ֱ��ָ��������
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
	 * δʵ��
	 * @param obj
	 * @return
	 */
	public List readByExample(Object obj) {
		return null;
	}

	/**
	 * δʵ��
	 * @param obj
	 * @return
	 * @throws NotUniqueException
	 */
	public Object readByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
	/**
	 * δʵ��
	 * @param objList
	 */
	public void write(List objList) {
		
	}
	/**
	 * δʵ��
	 * @param obj
	 */
	public void write(Object obj) {
		
	}
}
