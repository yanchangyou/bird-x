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
 * 瑙ｆ瀽鎺ュ彛
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Parsable.java,v0.1 2007-12-7 涓嬪崍04:34:24 cyyan Exp$
 */
public interface Parsable {
 
	/**
	 * 鎶婂瓧绗︿覆鐨勮В鏋愭垚瀵硅薄
	 * @param str 寰呰В鏋愮殑瀛楃涓�
	 * @return 杩斿洖瑙ｆ瀽鍚庣殑瀵硅薄
	 * @throws Exception 瑙ｆ瀽杩囩▼鐨勫紓甯�
	 */
	Object parse(String str) throws Exception;
	
	/**
	 * 鎶婂瓧绗︿覆瑙ｆ瀽鎴愬璞″苟瀵瑰畠鎸囧畾鍙傛暟
	 * @param str 寰呰В鏋愮殑瀛楃涓�
	 * @param pattern 鏍峰紡
	 * @return 杩斿洖瑙ｆ瀽鍚庣殑瀵硅薄
	 * @throws Exception 瑙ｆ瀽杩囩▼鐨勫紓甯�
	 */
	Object parse(String str, String pattern) throws Exception;
	
	/**
	 * 璁剧疆鏍峰紡
	 * @param pattern
	 */
	void setPattern(String pattern);
}
