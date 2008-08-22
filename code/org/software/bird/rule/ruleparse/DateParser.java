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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.software.bird.rule.RuleBugException;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DateParsor.java,v0.1 2007-12-7 ÏÂÎç05:24:04 cyyan Exp$
 */
public class DateParser extends StringParser {

	final private String DATE_PATTERN = "yyyy-MM-dd";

	public static SimpleDateFormat DATE_FORMAT;

	private String pattern;

	public DateParser(String pattern) {
		this.pattern = pattern;
		DATE_FORMAT = new SimpleDateFormat(pattern);
	}

	public DateParser() {
		DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
	}

	public Object parse(String dateStr) throws RuleBugException {

		if (!dateStr.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
			throw new RuleBugException(constructDateRuleBugReport(dateStr));
		} else {
			String[] datePart = dateStr.split("-");
			dateStr = datePart[0];// year
			dateStr = dateStr + "-" + (datePart[1].length() == 1 ? "0" + datePart[1]
					: datePart[1]);// month
			dateStr = dateStr + "-" + (datePart[2].length() == 1 ? "0" + datePart[2]
					: datePart[2]);// day
		}

		Date date = null;
		try {
			date = DATE_FORMAT.parse(dateStr);
			String dateStr2 = DATE_FORMAT.format(date);
			if (!dateStr.equals(dateStr2)) {
				throw new RuleBugException(constructDateRuleBugReport(dateStr)
						+ ", month or day out of range");
			}
		} catch (ParseException e) {
			throw new RuleBugException(constructDateRuleBugReport(dateStr));
		}
		return date;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Object parse(String str, String pattern) throws Exception {
		return parse(str);
	}

	public String constructDateRuleBugReport(String dateStr) {
		return RuleBugException.rule_bug_report_pre + dateStr + " invalid date";
	}
}
