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
 * SOM缁勪欢鐨勬帴鍙ｆ枃浠�, SOM鎵�鏈夊姛鑳介兘鍚屾绫昏皟鐢�<br>
 * 鐢ㄦ硶濡備笅锛�<br>
 * ExcelIO aExcelIO = new ExcelIO(excel鏂囦欢);<br>
 * 灏唀xcel鏂囦欢杞崲涓簆oi瀵硅薄,鏆傚瓨浜庡唴瀛樹腑绛夊緟瑙ｆ瀽<br>
 * List list = aExcelIO.readAll(aClass);<br>
 * 瑙ｆ瀽excel,骞惰浆鎹负list<br>
 * 
 * 瀵逛簬鏂囦欢鐨勪紶鍏ユ柟寮忔湁:<br>
 * 1, 鏂囦欢鍚� excelFileName<br>
 * 2, 鏂囦欢瀵硅薄 excelFile<br>
 * 3, 鏂囦欢杈撳叆娴� excelInputStream<br>
 * 
 * 浠巈xcel涓鍙栧璞℃湁涓嬪垪鏂瑰紡锛�<br>
 * 1, 鎸夌被璇诲彇<br>
 * 2, 鎸夌被鍜宻heet鍚嶈鍙�<br>
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConvertUtil.java,v0.1 2007-12-6 涓嬪崍01:47:14 cyyan Exp$
 */
public class ExcelIO {

	/**
	 * excelIO浠ｇ悊绫伙紝 鎵�浠ョ殑鎿嶄綔閮借浆浜や釜excelIODelegation鏉ュ仛
	 */
	private ExcelIODelegation excelIODelegation;

	/**
	 * excel鐨勫紓甯镐俊鎭紝涓嬩竴鐗堜腑灏嗘敼杩�
	 */
	public static String excel_read_exception_message = "excel娌℃壘鍒版垨宸叉崯鍧�, 璇锋鏌�";
	/**
	 * 閫氳繃鏂囦欢鍚嶄紶閫抏xcel鏂囦欢
	 * @param excelFileName
	 * @throws Exception
	 */
	public ExcelIO(String excelFileName) throws Exception {
		this(new File(excelFileName).exists() ? new File(excelFileName) : null);
	}

	/**
	 * 閫氳繃鏂囦欢瀵硅薄浼犻�抏xcel鏂囦欢
	 * @param excelFile
	 * @throws Exception
	 */
	public ExcelIO(File excelFile) throws Exception {
		this(excelFile == null ? null : new FileInputStream(excelFile));
	}

	/**
	 * 閫氳繃娴佸璞″璞′紶閫抏xcel鏂囦欢, 鍦╳eb鐢ㄤ簬甯哥敤姝ゆ柟寮�
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
	 * 瑙ｆ瀽excel鐨勬柟寮�1
	 * 鍙娇鐢ㄧ被, 绫诲搴旂殑琛ㄥ崟鍦ㄩ厤缃枃浠朵腑
	 * 
	 * @param objClass 鐩爣瀵硅薄鐨勭被
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
	 * 瑙ｆ瀽excel鐨勬柟寮�2
	 * 鐩存帴鎸囧畾琛ㄥ崟鍚嶇О
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
	 * 鏈疄鐜�
	 * @param obj
	 * @return
	 */
	public List readByExample(Object obj) {
		return null;
	}

	/**
	 * 鏈疄鐜�
	 * @param obj
	 * @return
	 * @throws NotUniqueException
	 */
	public Object readByObjectWithKey(Object obj) throws NotUniqueException {
		return null;
	}
	/**
	 * 鏈疄鐜�
	 * @param objList
	 */
	public void write(List objList) {
		
	}
	/**
	 * 鏈疄鐜�
	 * @param obj
	 */
	public void write(Object obj) {
		
	}
}
