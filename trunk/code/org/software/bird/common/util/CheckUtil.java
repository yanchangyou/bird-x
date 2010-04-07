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

import java.util.Collection;
import java.util.Map;

/**
 * 涓�鑸鏌ョ殑浣跨敤绫�
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: CheckUtil.java,v0.1 2007-12-16 涓嬪崍03:10:08 cyyan Exp$
 */
public class CheckUtil {

	/**
	 * 鍒ゆ柇鏄惁涓虹┖
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return isNull(str) || str.matches("^\\s*$");		
	}
	
	/**
	 * 鍒ゆ柇鏄笉涓虹┖
	 * @param str
	 * @return
	 */
	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);		
	}
	
	/**
	 * 鍒ゆ柇鏄惁鏄┖闆嗗悎
	 * @param collection
	 * @return
	 */
	public static boolean isEmptyCollection(Collection collection) {		
		return isNull(collection) || collection.size() == 0;
	}
	
	/**
	 * 鍒ゆ柇鏄惁涓嶆槸绌洪泦鍚�
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmptyCollection(Collection collection) {		
		return !isEmptyCollection(collection);
	}
	
	/**
	 * 鍒ゆ柇鏄惁鏄┖鏄犲皠
	 * @param map
	 * @return
	 */
	public static boolean isEmptyMap(Map map) {		
		return isNull(map) || map.size() == 0;
	}
	
	/**
	 * 鍒ゆ柇鏄惁涓嶆槸绌烘槧灏�
	 * @param map
	 * @return
	 */
	public static boolean isNotEmptyMap(Map map) {		
		return !isEmptyMap(map);
	}
	
	/**
	 * 鍒ゆ柇瀵硅薄鏄惁涓虹┖
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return null == obj;
	}
	
	/**
	 * 鍒ゆ柇瀵硅薄鏄惁涓嶄负绌�
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}	
}
