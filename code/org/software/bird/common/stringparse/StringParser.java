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

import org.software.bird.som.exception.EmptyException;

/**
 * 对String类型的解析类
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringParsor.java,v0.1 2007-12-7 下午05:24:57 cyyan Exp$
 */
public class StringParser  extends Parser {

	public static String str2str(String str) throws EmptyException {
		if (str == null || str.trim().equals("")) {
			throw new EmptyException("空白字符串异常");
		}
		return str;
	}

	public Object parse(String str) throws Exception {
		if (str == null || str.trim().equals("")) {
			throw new EmptyException("空白字符串异常");
		}
		return str;
	}

	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}

	public void setPattern(String pattern) {		
	}
}
