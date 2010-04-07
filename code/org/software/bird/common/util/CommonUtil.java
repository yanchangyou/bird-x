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

package org.software.bird.common.util;

import java.io.File;
import java.io.InputStream;

/**
 * 一般的常用函数
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Common.java,v0.1 2007-12-6 下午01:46:27 cyyan Exp$
 */
public class CommonUtil {

	/**
	 * 获取类不包含包名的类名
	 * @param aClass
	 * @return
	 */
	public static String getClassNameWithoutPackage(Class aClass) {
		String nameWithPackage = aClass.getName();
		String[] nameUnitArray = nameWithPackage.split("\\.");
		return nameUnitArray[nameUnitArray.length - 1];
	}

	/**
	 *  获取包名
	 * @param aClass
	 * @return
	 */
	public static String getPackageName(Class aClass) {
		return aClass.getPackage().getName();
	}

	/**
	 *  把包名转换为路径格式
	 * @param aClass
	 * @return
	 */
	public static String getPackagePath(Class aClass) {
		return getPackageName(aClass).replace('.', File.separatorChar);
	}

	/**
	 * getter方法
	 * @param property
	 * @return
	 */
	public static String property2getterName(String property) {
		return "get" + upperFirstLetter(property);
	}

	/**
	 * setter方法
	 * @param property
	 * @return
	 */
	public static String property2setterName(String property) {
		return "set" + upperFirstLetter(property);
	}

	/**
	 * 字符串首字大写
	 * @param str
	 * @return
	 */
	public static String upperFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 获取第一个点号之前字符串
	 * @param str
	 * @return
	 */
	public static String getBeforePointString(String str) {
		String[] part = str.split("\\.");
		return part[0];
	}

	/**
	 * 获取类根路径下的资源流
	 * @param sourceName
	 * @return
	 */
	public static InputStream getInputStreamBySourceName(String sourceName) {
		InputStream configFileInputStream = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		configFileInputStream = classLoader.getResourceAsStream(sourceName);
		return configFileInputStream;
	}
	
	/**
	 * 
	 * 获取与类下面的同路径的资源
	 * 
	 * @param sourceName
	 * @param aClass
	 * @return
	 */
	public static InputStream getInputStreamBySoureNameInSamePackage(String sourceName, Class aClass) {
		InputStream configFileInputStream = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		configFileInputStream = classLoader.getResourceAsStream(getPackagePath(aClass) + File.separator + sourceName);
		return configFileInputStream;
	}
	
	/**
	 * 获取重复的字符串
	 * @param str 基本字符串
	 * @param time 重复的次数
	 * @return 字符串
	 */
	public static String getCopyString(String str, int time) {
		StringBuffer copyString = new StringBuffer();
		for (int i = 0; i < time; i++) {
			copyString.append(str);
		}
		return copyString.toString();
	}
	
	
}
