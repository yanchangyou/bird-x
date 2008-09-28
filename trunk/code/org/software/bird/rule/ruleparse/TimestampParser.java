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

package org.software.bird.rule.ruleparse;

import java.sql.Timestamp;
import java.util.Date;

import org.software.bird.rule.RuleBugException;

/**
 * 对Timestamp类型的解析类
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: TimestampParsor.java,v0.1 2007-12-7 下午05:54:50 cyyan Exp$
 */
public class TimestampParser extends DateParser {

	public Object parse(String dateStr) throws RuleBugException {
		Date date = (Date) super.parse(dateStr);
		return new Timestamp(date.getTime());
	}

}
