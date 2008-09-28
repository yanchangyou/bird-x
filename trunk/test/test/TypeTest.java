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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.software.bird.common.util.CommonUtil;
import org.software.bird.rule.BreakStringRuleException;
import org.software.bird.som.ExcelIO;
import org.software.bird.som.exception.InvalidExcelFileException;
import org.software.bird.som.exception.SheetNotFoundException;
import org.software.bird.som.exception.SheetTitleNotFoundException;



/**
 * Class description goes here.
 *   
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: TestMain.java,v0.1 2007-12-6 下午01:57:37 cyyan Exp$
 */
public class TypeTest {
	
	public static void main(String[] args) {		
		
		String fileName = "支持的变量类型.xls";
		
		Class[] objClassArr 
			= new Class[] {
//							StringTypeTest.class,
							NumberTypeTest.class, 
//							DateTypeTest.class,
						   };
		
		
		test(fileName, objClassArr);
	}
	
	
	public static void test(String excelFileName, Class[] objClassArr ) {
		List list = null;
		InputStream inputStream = CommonUtil.getInputStreamBySourceName(excelFileName);
		
		try {
			ExcelIO aExcelIO = new ExcelIO(inputStream);
			for (int i=0; i<objClassArr.length; i++) {
				list = aExcelIO.readAll(objClassArr[i]);
				System.out.println(list);
			}
		} catch(InvalidExcelFileException e) {
			System.out.println(e.getMessage());
		} catch (SheetNotFoundException e) {
			System.out.println(e.getMessage());			
		} catch (BreakStringRuleException e) {
			System.out.println(e.getCause());
			String msg = e.getMessage();
			msg = msg == null ? "" : msg;
			System.out.println("\n错误信息如下：\n" + msg.replaceAll("<BR>", "\n"));
			e.printStackTrace();
		} catch (SheetTitleNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
