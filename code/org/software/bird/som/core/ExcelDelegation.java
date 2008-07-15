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
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.software.bird.som.util.ExcelParserUtil;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelDelegation.java,v0.1 2007-12-15 ����04:00:56 cyyan Exp$
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
