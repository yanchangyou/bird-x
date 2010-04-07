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
 * @version $Id: NumberTypeTest.java,v0.1 2007-12-12 下午05:52:43 cyyan Exp$
 */
public class NumberTypeTest {
	private Byte byteVar;

	private Byte byteVar_text;

	private Short shortVar;

	private Short shortVar_text;

	private Integer intVar;

	private Integer intVar_text;

	private Long longVar;

	private Long longVar_text;

	private Float floatVar;

	private Float floatVar_text;

	private Double doubleVar;

	private Double doubleVar_text;

	public Byte getByteVar() {
		return byteVar;
	}

	public void setByteVar(Byte byteVar) {
		this.byteVar = byteVar;
	}

	public Byte getByteVar_text() {
		return byteVar_text;
	}

	public void setByteVar_text(Byte byteVar_text) {
		this.byteVar_text = byteVar_text;
	}

	public Double getDoubleVar() {
		return doubleVar;
	}

	public void setDoubleVar(Double doubleVar) {
		this.doubleVar = doubleVar;
	}

	public Double getDoubleVar_text() {
		return doubleVar_text;
	}

	public void setDoubleVar_text(Double doubleVar_text) {
		this.doubleVar_text = doubleVar_text;
	}

	public Float getFloatVar() {
		return floatVar;
	}

	public void setFloatVar(Float floatVar) {
		this.floatVar = floatVar;
	}

	public Float getFloatVar_text() {
		return floatVar_text;
	}

	public void setFloatVar_text(Float floatVar_text) {
		this.floatVar_text = floatVar_text;
	}

	public Integer getIntVar() {
		return intVar;
	}

	public void setIntVar(Integer intVar) {
		this.intVar = intVar;
	}

	public Integer getIntVar_text() {
		return intVar_text;
	}

	public void setIntVar_text(Integer intVar_text) {
		this.intVar_text = intVar_text;
	}

	public Long getLongVar() {
		return longVar;
	}

	public void setLongVar(Long longVar) {
		this.longVar = longVar;
	}

	public Long getLongVar_text() {
		return longVar_text;
	}

	public void setLongVar_text(Long longVar_text) {
		this.longVar_text = longVar_text;
	}

	public Short getShortVar() {
		return shortVar;
	}

	public void setShortVar(Short shortVar) {
		this.shortVar = shortVar;
	}

	public Short getShortVar_text() {
		return shortVar_text;
	}

	public void setShortVar_text(Short shortVar_text) {
		this.shortVar_text = shortVar_text;
	}

	public String toString() {
		return "" + byteVar + ", " + byteVar_text + ", " + shortVar + ", "
				+ shortVar_text + ", " + intVar + ", " + intVar_text + ", "
				+ longVar + ", " + longVar_text + ", " + floatVar + ", "
				+ floatVar_text + ", " + doubleVar + ", " + doubleVar_text +"\n";
	}
}
