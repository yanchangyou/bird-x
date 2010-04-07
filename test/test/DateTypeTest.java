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

package test;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: DateTypeTest.java,v0.1 2007-12-12 下午09:21:42 cyyan Exp$
 */
public class DateTypeTest {

	private java.util.Date dateText;

	private java.sql.Date sqlDateText;

	private java.sql.Timestamp timestampText;

	private java.util.Date dateDate;

	private java.sql.Date sqlDateDate;

	private java.sql.Timestamp timestampDate;

	public java.util.Date getDateDate() {
		return dateDate;
	}

	public void setDateDate(java.util.Date dateDate) {
		this.dateDate = dateDate;
	}

	public java.util.Date getDateText() {
		return dateText;
	}

	public void setDateText(java.util.Date dateText) {
		this.dateText = dateText;
	}

	public java.sql.Date getSqlDateDate() {
		return sqlDateDate;
	}

	public void setSqlDateDate(java.sql.Date sqlDateDate) {
		this.sqlDateDate = sqlDateDate;
	}

	public java.sql.Date getSqlDateText() {
		return sqlDateText;
	}

	public void setSqlDateText(java.sql.Date sqlDateText) {
		this.sqlDateText = sqlDateText;
	}

	public java.sql.Timestamp getTimestampDate() {
		return timestampDate;
	}

	public void setTimestampDate(java.sql.Timestamp timestampDate) {
		this.timestampDate = timestampDate;
	}

	public java.sql.Timestamp getTimestampText() {
		return timestampText;
	}

	public void setTimestampText(java.sql.Timestamp timestampText) {
		this.timestampText = timestampText;
	}

	public String toString() {
		return dateText + ", " + sqlDateText + ", " + timestampText + ", "
				+ dateDate + ", " + sqlDateDate + ", " + timestampDate + "\n";
	}
}
