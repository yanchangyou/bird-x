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
 * һ�����ʹ����
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: CheckUtil.java,v0.1 2007-12-16 ����03:10:08 cyyan Exp$
 */
public class CheckUtil {

	/**
	 * �ж��Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return isNull(str) || str.matches("^\\s*$");		
	}
	
	/**
	 * �ж��ǲ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);		
	}
	
	/**
	 * �ж��Ƿ��ǿռ���
	 * @param collection
	 * @return
	 */
	public static boolean isEmptyCollection(Collection collection) {		
		return isNull(collection) || collection.size() == 0;
	}
	
	/**
	 * �ж��Ƿ��ǿռ���
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmptyCollection(Collection collection) {		
		return !isEmptyCollection(collection);
	}
	
	/**
	 * �ж��Ƿ��ǿ�ӳ��
	 * @param map
	 * @return
	 */
	public static boolean isEmptyMap(Map map) {		
		return isNull(map) || map.size() == 0;
	}
	
	/**
	 * �ж��Ƿ��ǿ�ӳ��
	 * @param map
	 * @return
	 */
	public static boolean isNotEmptyMap(Map map) {		
		return !isEmptyMap(map);
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		return null == obj;
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}	
}
