/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

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
 * @version $Id: ExcelParseProxy.java,v0.1 2007-12-8 ����01:43:43 cyyan Exp$
 */
public class ExcelIODelegation {
	
	public static String sheet_not_found_exception_message = "�����Ƿ�����ȷ��ģ�壬���������Ƿ񱻸Ķ�";
	
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
			throw new SheetNotFoundException("����[" + sheetName + "]�����ڣ�" + sheet_not_found_exception_message);
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
