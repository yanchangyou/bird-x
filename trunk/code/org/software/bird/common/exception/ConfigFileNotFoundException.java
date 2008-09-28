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

package org.software.bird.common.exception;
/**
 * �����ļ�û���ҵ��쳣
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConfigFileNotFoundException.java,
 * v0.1 2007-12-8 ����06:23:50 cyyan Exp$
 */
public class ConfigFileNotFoundException extends Exception {

	private static final long serialVersionUID = -5499846709748480844L;

	public ConfigFileNotFoundException(String string) {
		super(string);
	}
}
