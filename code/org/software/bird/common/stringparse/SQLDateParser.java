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

import org.software.bird.som.exception.InvalidDateException;

/**
 * ��SQLDate���͵Ľ�����
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SQLDateParsor.java,v0.1 2007-12-7 ����05:54:32 cyyan Exp$
 */
public class SQLDateParser extends DateParser {

	/**
	 * ���췽��
	 * @param pattern �������Ĺ��췽��
	 */
	public SQLDateParser(String pattern) {
		super(pattern);
	}

	/**
	 * ���������Ĺ��췽��
	 *
	 */	
	public SQLDateParser() {
		super();
	}

	/**
	 * �����ַ�����sql���ڶ���
	 * @param �����ַ���
	 */
	public Object parse(String dateStr) throws InvalidDateException {
		java.util.Date date = (java.util.Date)super.parse(dateStr);
		return new java.sql.Date(date.getTime());
	}
}
