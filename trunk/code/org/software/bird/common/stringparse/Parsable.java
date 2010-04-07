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
/**
 * 解析接口
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Parsable.java,v0.1 2007-12-7 下午04:34:24 cyyan Exp$
 */
public interface Parsable {
 
	/**
	 * 把字符串的解析成对象
	 * @param str 待解析的字符串
	 * @return 返回解析后的对象
	 * @throws Exception 解析过程的异常
	 */
	Object parse(String str) throws Exception;
	
	/**
	 * 把字符串解析成对象并对它指定参数
	 * @param str 待解析的字符串
	 * @param pattern 样式
	 * @return 返回解析后的对象
	 * @throws Exception 解析过程的异常
	 */
	Object parse(String str, String pattern) throws Exception;
	
	/**
	 * 设置样式
	 * @param pattern
	 */
	void setPattern(String pattern);
}
