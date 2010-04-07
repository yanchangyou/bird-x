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

package org.software.bird.rule;
/**
 *  瑙勫垯鏈韩鐨勫紓甯�
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: RuleBugException.java,v0.1 2007-12-16 涓嬪崍05:11:31 cyyan Exp$
 */
public class RuleBugException extends Exception {

	private static final long serialVersionUID = -8876172406935676701L;
	/**
	 * 瑙勫垯鏈韩鐨勫紓甯�
	 * @param message
	 */
	public RuleBugException(String message) {
		super(message);
	}
	
	public static final String rule_bug_report_pre = "this is a rule bug, please check the rule and rewrite it; \nreason : " ;
}
