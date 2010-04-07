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
 * 瀵筍tring绫诲瀷鐨勮В鏋愮被
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringParsor.java,v0.1 2007-12-7 涓嬪崍05:24:57 cyyan Exp$
 */
public class StringParser  extends Parser {

	/**
	 * 瑙ｆ瀽瀛楃涓诧紝涓嶈兘涓虹┖
	 * @param 瑕佽В鏋愬瓧绗︿覆
	 * @exception 瀵逛簬null鍜岀┖鐧藉瓧绗︿覆閮借涓烘槸寮傚父
	 */
	public Object parse(String str) throws Exception {
		if (str == null || str.trim().equals("")) {
			throw new EmptyException("绌虹櫧瀛楃涓插紓甯�");
		}
		return str;
	}

	/**
	 * 涓嶈姝ゆ柟娉�
	 * @deprecated
	 */
	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}

	/**
	 * 涓嶈姝ゆ柟娉�
	 * 
	 * @deprecated
	 */
	public void setPattern(String pattern) {		
	}
}
