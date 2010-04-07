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
 * 涓�鑸殑甯哥敤鍑芥暟
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Common.java,v0.1 2007-12-6 涓嬪崍01:46:27 cyyan Exp$
 */
public class CommonUtil {

	/**
	 * 鑾峰彇绫讳笉鍖呭惈鍖呭悕鐨勭被鍚�
	 * @param aClass
	 * @return
	 */
	public static String getClassNameWithoutPackage(Class aClass) {
		String nameWithPackage = aClass.getName();
		String[] nameUnitArray = nameWithPackage.split("\\.");
		return nameUnitArray[nameUnitArray.length - 1];
	}

	/**
	 *  鑾峰彇鍖呭悕
	 * @param aClass
	 * @return
	 */
	public static String getPackageName(Class aClass) {
		return aClass.getPackage().getName();
	}

	/**
	 *  鎶婂寘鍚嶈浆鎹负璺緞鏍煎紡
	 * @param aClass
	 * @return
	 */
	public static String getPackagePath(Class aClass) {
		return getPackageName(aClass).replace('.', File.separatorChar);
	}

	/**
	 * getter鏂规硶
	 * @param property
	 * @return
	 */
	public static String property2getterName(String property) {
		return "get" + upperFirstLetter(property);
	}

	/**
	 * setter鏂规硶
	 * @param property
	 * @return
	 */
	public static String property2setterName(String property) {
		return "set" + upperFirstLetter(property);
	}

	/**
	 * 瀛楃涓查瀛楀ぇ鍐�
	 * @param str
	 * @return
	 */
	public static String upperFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 鑾峰彇绗竴涓偣鍙蜂箣鍓嶅瓧绗︿覆
	 * @param str
	 * @return
	 */
	public static String getBeforePointString(String str) {
		String[] part = str.split("\\.");
		return part[0];
	}

	/**
	 * 鑾峰彇绫绘牴璺緞涓嬬殑璧勬簮娴�
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
	 * 鑾峰彇涓庣被涓嬮潰鐨勫悓璺緞鐨勮祫婧�
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
	 * 鑾峰彇閲嶅鐨勫瓧绗︿覆
	 * @param str 鍩烘湰瀛楃涓�
	 * @param time 閲嶅鐨勬鏁�
	 * @return 瀛楃涓�
	 */
	public static String getCopyString(String str, int time) {
		StringBuffer copyString = new StringBuffer();
		for (int i = 0; i < time; i++) {
			copyString.append(str);
		}
		return copyString.toString();
	}
	
	
}
