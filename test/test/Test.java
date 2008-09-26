/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.software.bird.common.util.CommonUtil;
import org.software.bird.som.ExcelIO;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Test.java,v0.1 2008-9-26 下午04:52:07 cyyan Exp$
 */
public class Test {

	public static void main(String[] args) throws Exception {
		
		
		System.out.println("张三".matches("[^\\x00-\\xff]{2,5}"));

		String fileName = "bin/复件 测试.xls";
		//File file = new File(fileName);
		//System.out.println(file.getAbsolutePath());
		//InputStream inputStream = CommonUtil.getInputStreamBySourceName(fileName);
		

		long time1 = 0;
		long time2 = 0;
		long time3 = 0;
		
		time1 = System.currentTimeMillis();
		
		ExcelIO aExcelIO = new ExcelIO(fileName);
		time2 = System.currentTimeMillis();
		List list = aExcelIO.readAll(Student.class);
		time3 = System.currentTimeMillis();

		System.out.println(list.size() + "行");
		
		System.out.println("读取excel成poi对象 ： " + (time2 - time1)/1000.0 + "秒");
		System.out.println("解析poi对象成list ： " + (time3 - time2)/1000.0 + "秒");
		System.out.println("读取+解析  ： " + (time3 - time1)/1000.0 + "秒");
	}
}
