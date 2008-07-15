/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

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
 * @version $Id: TestTypeMain.java,v0.1 2008-1-16 下午08:05:20 cyyan Exp$
 */
public class TestTypeMain {

	public static void main(String[] args) {		
		
		String fileName = "测试.xls";		
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
			System.out.println("\n错误信息如下：\n" + msg.replaceAll("<BR>", "\n"));
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
