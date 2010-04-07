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
 * 瀵笵ouble绫诲瀷鐨勮В鏋愮被
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: asdfasd.java,v0.1 2007-12-7 涓嬪崍05:45:11 cyyan Exp$
 */

public class DoubleParser extends NumberParser {

	/**
	 * 瑙ｆ瀽瀛楃鎴怐ouble绫诲瀷
	 * 
	 * @param 寰呰В鏋愮殑瀛楃涓�
	 * 
	 * @exception InvalidNumberException 鏃犳晥鐨勫瓧绗︿覆
	 * @exception OutOfRangeException瓒呰繃Double绫诲瀷鐨勬渶澶ц寖鍥� -Double.MAX_VALUE <= value && value <= Double.MAX_VALUE
	 */
	public Object parse(String str) throws InvalidNumberException, OutOfRangeException {
		Number num = (Number) super.parse(str);
		double value = num.doubleValue();
		if (!(-Double.MAX_VALUE <= value && value <= Double.MAX_VALUE)) {
			throw new OutOfRangeException("out doubleVar range [" + Double.MIN_VALUE + "," + Double.MAX_VALUE + "]");
		}
		Double doubleVar = new Double(num.doubleValue());
		return doubleVar;
	}
}
