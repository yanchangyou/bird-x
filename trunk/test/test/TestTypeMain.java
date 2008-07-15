/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.software.bird.common.util.CommonUtil;
import org.software.bird.rule.BreakStringRuleException;
import org.software.bird.som.ExcelIO;
import org.software.bird.som.exception.InValidExcelFileException;
import org.software.bird.som.exception.SheetNotFoundException;
import org.software.bird.som.exception.SheetTitleNotFoundException;


/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: TestTypeMain.java,v0.1 2008-1-16 ����08:05:20 cyyan Exp$
 */
public class TestTypeMain {

	public static void main(String[] args) {		
		
		String fileName = "����.xls";		
		InputStream inputStream = CommonUtil.getInputStreamBySourceName(fileName);
		
		try {
			ExcelIO aExcelIO = new ExcelIO(inputStream);

			List list = aExcelIO.readAll(TypeTest.class);
			System.out.println(list);

			
		} catch(InValidExcelFileException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (SheetNotFoundException e) {
			System.out.println(e.getMessage());			
		} catch (BreakStringRuleException e) {
			System.out.println(e.getCause());
			String msg = e.getMessage();
			msg = msg == null ? "" : msg;
			System.out.println("\n������Ϣ���£�\n" + msg.replaceAll("<BR>", "\n"));
			//e.printStackTrace();
		} catch (SheetTitleNotFoundException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
