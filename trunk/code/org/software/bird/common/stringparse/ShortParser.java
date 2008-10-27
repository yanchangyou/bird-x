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

package org.software.bird.common.stringparse;


import org.software.bird.common.exception.OutOfRangeException;
import org.software.bird.som.exception.InvalidNumberException;


/**
 * ��Short���͵Ľ�����
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: safd.java,v0.1 2007-12-7 ����05:45:02 cyyan Exp$
 */

public class ShortParser extends NumberParser {
	/**
	 * �����ַ���Short����
	 * 
	 * @param ���������ַ���
	 * 
	 * @exception InvalidNumberException ��Ч���ַ���
	 * @exception OutOfRangeException����Double���͵����Χ Short.MIN_VALUE <= value && value <= Short.MAX_VALUE
	 */
	public Object parse(String str) throws InvalidNumberException, OutOfRangeException {
		Number num = (Number) super.parse(str);
		double value = num.doubleValue();
		if (!(Short.MIN_VALUE <= value && value <= Short.MAX_VALUE)) {
			throw new OutOfRangeException("out shortVar range [" + Short.MIN_VALUE + "," + Short.MAX_VALUE + "]");
		}
		Short shortVar = new Short(num.shortValue());
		return shortVar;
	}
}
