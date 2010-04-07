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

import java.lang.reflect.Method;

/**
 * 澶勭悊bean鐨勪娇鐢ㄧ被
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BeanUtil.java,v0.1 2007-12-14 涓嬪崍05:35:29 cyyan Exp$
 */
public class BeanUtil {

	/**
	 * 璁剧疆bean鐨勫睘鎬у��
	 * @param obj
	 * @param property
	 * @param value
	 * @throws Exception
	 * @throws NoSuchMethodException
	 */
	public static void setPropertyValueOfBean(Object obj, String property, Object value) throws Exception, NoSuchMethodException {
		Class objClass = obj.getClass();
		Method getter = objClass.getMethod(CommonUtil
				.property2getterName(property), null);

		Method setter = objClass.getMethod(CommonUtil
				.property2setterName(property), new Class[] { getter.getReturnType()});

		setter.invoke(obj, new Object[] { value });
	}
	
	/**
	 * 鑾峰彇bean灞炴�х殑绫荤被鍨�
	 * @param obj
	 * @param property
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Class getPropertyClassOfBean(Object obj, String property) throws SecurityException, NoSuchMethodException {
		Method getter = obj.getClass().getMethod(CommonUtil
				.property2getterName(property), null);
		return getter.getReturnType();
	}
}
