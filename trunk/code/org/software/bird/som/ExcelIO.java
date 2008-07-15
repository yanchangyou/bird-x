/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
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
 * @version $Id: ConvertUtil.java,v0.1 2007-12-6 ����01:47:14 cyyan Exp$
 */
public class ExcelIO {

	private ExcelIODelegation excelIODelegation;

	public static String excel_read_exception_message = "������ȷ��ģ�嵼��";
	
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
