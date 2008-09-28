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

package org.software.bird.som.core;




import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.software.bird.common.exception.ConfigFileNotFoundException;
import org.software.bird.common.util.BootConfig;
import org.software.bird.common.util.CheckUtil;
import org.software.bird.common.util.CommonUtil;
import org.software.bird.common.util.ConfigUtil;
import org.software.bird.common.util.XMLUtil;
import org.software.bird.rule.StringRule;
import org.software.bird.rule.StringRuleConfig;
import org.software.bird.som.util.ExcelUtil;
import org.software.bird.som.util.SheetObjectMappingUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SheetObjectConfig.java,v0.1 2007-12-5 下午09:39:11 cyyan Exp$
 */
public class SheetObjectMappingConfig {

	
	
	final public static String som_config_file = ConfigUtil.getRealPath(BootConfig.getProperty("ether.anima.bird.som.boot.path"));
	final public static String som_tag_name = "som";
	final public static String som_class_tag_name = "class";
	final public static String som_class_name_property_name = "name";
	final public static String som_class_sheet_property_name = "sheet";
	final public static String som_src_property_name = "src";
	
	final public static String som_class_property_tag_name = "property";
	
	final public static String som_class_property_property_name = "name";
	final public static String som_class_property_title_name = "title";
	
	final public static String som_class_property_string_rule_tag = "string_rule";
	
	public final static char string_rule_import_file_separator_char = '|';
	
	private static Map class_som_map = new HashMap();	
	
	public static SheetObjectMapping getSOM(Class objClass) {
		
		if (CheckUtil.isEmptyMap(class_som_map)) {
			try {
				initConfig();
				System.out.println("所有的som ：\n" + class_som_map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return (SheetObjectMapping)class_som_map.get(objClass);
	}
	/**
	 * 初始化 配置
	 * @throws ConfigFileNotFoundException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void initConfig() throws ConfigFileNotFoundException, FactoryConfigurationError, ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
		InputStream configFileInputStream = CommonUtil.getInputStreamBySourceName(som_config_file);
		if (configFileInputStream == null) {
				throw new ConfigFileNotFoundException("can't find som config file : "
			+ ConfigUtil.getRealPath(som_config_file));
			}
		
		// 获取配置文件
		Document xmlDocument = XMLUtil.getDocumentAtInputStream(configFileInputStream);
		NodeList somNodeList = xmlDocument.getElementsByTagName(som_tag_name);
		String[] somFileNameArray = XMLUtil.getAllPropertyValue(somNodeList, som_src_property_name);
		for (int i = 0; i < somFileNameArray.length; i++) {
			String somFileName = somFileNameArray[i];
			if (!CheckUtil.isEmptyString(somFileName)) {
				somFileName = ConfigUtil.getRealPath(somFileName);
				configSOM(somFileName);
			}
			
		}		
	}
	
	 /**
	  * 配置 title 到 column 的映射
	  * @param titleRow
	  * @return
	  */
	public static Map configTitleColumnMap(HSSFRow titleRow) {
		final Map title_column_map = new HashMap();

		for (short i = titleRow.getFirstCellNum(); i <= titleRow.getLastCellNum(); i++) {
			HSSFCell cell = titleRow.getCell( i);
			String cellStringValue = ExcelUtil.cell2string(cell, null);
			if (CheckUtil.isNotEmptyString(cellStringValue)) {
				title_column_map.put(cellStringValue, new Short(i));
			}
		}
		return title_column_map;
	}
	
	/**
	 * 配置 一个som 文件 可能有多个 som 配置在里面
	 * @param somFileName
	 * @throws ConfigFileNotFoundException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void configSOM(String somFileName) throws ConfigFileNotFoundException, FactoryConfigurationError, ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
		SheetObjectMapping aSheetObjectMapping = new SheetObjectMapping();
		
		InputStream configFileInputStream = CommonUtil.getInputStreamBySourceName(somFileName);
		if (configFileInputStream == null) {
			throw new ConfigFileNotFoundException("can't find this class's config file:" + aSheetObjectMapping.getObjClass() + ", please check the file");
		}
		// config mapping
		Document xmlDocument = XMLUtil.getDocumentAtInputStream(configFileInputStream);

		NodeList classNodeList = xmlDocument.getElementsByTagName(som_class_tag_name);
		
		configSOM(classNodeList, class_som_map);
	}
	/**
	 * 配置 classNodeList 可能有多个 som 配置在里面
	 * @param classNodeList
	 * @param class_som_map
	 * @throws ClassNotFoundException
	 */
	public static void configSOM(NodeList classNodeList, Map class_som_map) throws ClassNotFoundException {
		for (int i = 0; i < classNodeList.getLength(); i++) {
			Node somNode = classNodeList.item(i);
			String className = XMLUtil.getNodePropertyValue(
					somNode, som_class_name_property_name);
			Class somClass = Class.forName(className);
			SheetObjectMapping som = constructSOM(somNode);
			class_som_map.put(somClass, som);
		}
	}
	/**
	 *  配置单个som
	 * @param classNode
	 * @return
	 */
	public static SheetObjectMapping constructSOM(Node classNode) {
		SheetObjectMapping som = new SheetObjectMapping();
		NodeList propertyList = classNode.getChildNodes();
		// set object class name
		String className = XMLUtil.getNodePropertyValue(
				classNode, som_class_name_property_name);
		Class objClass = null;
		try {
			objClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
		}
		som.setObjClass(objClass);
		
		// set sheet name
		String sheetName = XMLUtil.getNodePropertyValue(
				classNode, som_class_sheet_property_name);
		som.setSheetName(sheetName);
		
		// set title row
		int titleRowIndex = SheetObjectMappingUtil.getTitleRowIndex(classNode);
		som.setTitleRowIndex(titleRowIndex);
		// set first data row index
		int firstDataRowIndex = SheetObjectMappingUtil.getFirstDataRowIndex(classNode);
		som.setFirstDataRowIndex(firstDataRowIndex);
		
//		 set max row number
		int dataRowMaxNumber = SheetObjectMappingUtil.getDataRowMaxNumber(classNode);
		som.setDataRowMaxNumber(dataRowMaxNumber);
		
		// set all property
		Map propertyTitleMap = new HashMap();
		Map propertyStringRuleMap = new HashMap();
		for (int i = 0; i < propertyList.getLength(); i++) {
			Node propertyNode = propertyList.item(i);
			if (propertyNode.getNodeName().equals(som_class_property_tag_name)) {
				// property title map
				String propertyName = XMLUtil.getNodePropertyValue(propertyNode, som_class_property_property_name);
				String titleName = XMLUtil.getNodePropertyValue(propertyNode, som_class_property_title_name);
				propertyTitleMap.put(propertyName, titleName);
				// string rule
				Node stringRuleNode = XMLUtil.getFirstChildNodeByTagName(propertyNode, som_class_property_string_rule_tag);
				if (CheckUtil.isNotNull(stringRuleNode)) {
					StringRule stringRule = StringRuleConfig.constructStringRule(stringRuleNode);
					propertyStringRuleMap.put(propertyName, stringRule);
				}
			}
		}
		som.setPropertyTitleMap(propertyTitleMap);
		som.setPropertyStringRuleMap(propertyStringRuleMap);
		return som;
	}
}
