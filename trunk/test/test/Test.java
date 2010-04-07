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

package test;

import java.util.List;

import org.software.bird.som.ExcelIO;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Test.java,v0.1 2008-9-26 下午04:52:07 cyyan Exp$
 */
public class Test {

	public static void main(String[] args) throws Exception {

		简单测试();
		
//		String[] sheetNameArray = new String[]{"学生信息1000","学生信息4000","学生信息10000","学生信息40000"};
//		性能测试(sheetNameArray);
	}
	
	
	public final static String fileName = "bin/学生信息测试.xls";
	
	public static void 简单测试() throws Exception {
				
		ExcelIO aExcelIO = new ExcelIO(fileName);
		
		List list = aExcelIO.readAll(Student.class);
		
		System.out.println(list);
	}
	
	
	
	public static void 性能测试(String[] sheetNameArray) throws Exception {
		System.out.println("性能测试");
		
		long time1 = 0;
		long time2 = 0;
		long time3 = 0;
		
		time1 = System.currentTimeMillis();			
		
		ExcelIO aExcelIO = new ExcelIO(fileName);
		
		time2 = System.currentTimeMillis();
		
		time1 = time2 - time1;
		
		
		
		for (int i = 0; i < sheetNameArray.length; i++) {
			time2 = 0;
			time3 = 0;
			
			time2 = System.currentTimeMillis();
			List list = aExcelIO.readAll(sheetNameArray[i], Student.class);
			time3 = System.currentTimeMillis();

			//System.out.println("开始解析 sheet ：" + sheetNameArray[i]);
			System.out.println(list.size() + "行");
			System.out.println("读取excel成poi对象 ： " + time1/1000.0 + "秒");
			System.out.println("解析poi对象成list ： " + (time3 - time2)/1000.0 + "秒");
			System.out.println("读取+解析  ： " + (time3 - time2 + time1)/1000.0 + "秒");
			System.out.println();
			
		}
	}
}
