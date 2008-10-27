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
 * 对Integer类型的解析类
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DoubleParsor.java,v0.1 2007-12-7 下午04:38:28 cyyan Exp$
 */
public class IntegerParser extends NumberParser {
	/**
	 * 解析字符成Integer类型
	 * 
	 * @param 待解析的字符串
	 * 
	 * @exception InvalidNumberException 无效的字符串
	 * @exception OutOfRangeException超过Double类型的最大范围 Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE
	 */
	public Object parse(String str) throws InvalidNumberException,
			OutOfRangeException {
		Number num = (Number) super.parse(str);
		double value = num.doubleValue();
		if (!(Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE)) {
			throw new OutOfRangeException("out doubleVar range ["
					+ Integer.MIN_VALUE + "," + Integer.MAX_VALUE + "]");
		}
		Integer doubleVar = new Integer(num.intValue());
		return doubleVar;
	}
}
