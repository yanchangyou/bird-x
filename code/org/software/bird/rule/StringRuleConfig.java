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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.software.bird.common.exception.ConfigFileNotFoundException;
import org.software.bird.common.util.BootConfig;
import org.software.bird.common.util.CheckUtil;
import org.software.bird.common.util.CommonUtil;
import org.software.bird.common.util.ConfigUtil;
import org.software.bird.common.util.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 将规则配置文件处理成对象
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: StringRuleConfig.java,v0.1 2007-12-14 上午09:21:08 cyyan Exp$
 */
public final class StringRuleConfig {

	// 配置文件的固定位置
	private final static String string_rule_config = ConfigUtil.getRealPath(BootConfig.getProperty("ether.anima.bird.rule.boot.path"));

	// 配置文件中的标签

	public final static String string_rule_tag_name = "string_rule";
	
	public final static String string_rule_name_property = "name";

	public final static String string_rule_description_tag_name = "description";

	public final static String string_rule_message_tag_name = "message";

	public final static String string_rule_match_tag_name = "match";

	public final static String string_rule_emptyable_tag_name = "emptyable";
	
	public final static String string_rule_parent_rule_property_name = "parent_rule";

	//
	public final static String string_rule_import_tag_name = "import";

	public final static String string_rule_import_src_property_name = "src";

	// 存储配置文件里的所以规则
	private static Map string_rule_map;

	
	static {
		try {
			config();
		} catch (Exception e) {		
			
			e.printStackTrace();
		}
		System.out.println("\n所有的字符规则：\n" + string_rule_map);
	}
	
	
	/**
	 * 按规则名字获取规则
	 * 
	 * @param stringRuleName
	 * @return
	 */
	public static StringRule getStringRule(String stringRuleName) {
		if (CheckUtil.isEmptyMap(string_rule_map)) {
			try {
				initConfig();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (StringRule) string_rule_map.get(stringRuleName);
	}

	/**
	 * 配置
	 * 
	 * @throws ConfigFileNotFoundException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void config() throws ConfigFileNotFoundException,
			FactoryConfigurationError, ParserConfigurationException,
			SAXException, IOException {
		if (CheckUtil.isEmptyMap(string_rule_map)) {
			initConfig();
		}
	}
	
	

	/**
	 * 初始化配置
	 * 
	 * @throws ConfigFileNotFoundException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static void initConfig() throws ConfigFileNotFoundException,
			FactoryConfigurationError, ParserConfigurationException,
			SAXException, IOException {
		string_rule_map = new HashMap();
		InputStream configFileInputStream = CommonUtil
				.getInputStreamBySourceName(string_rule_config);
		if (configFileInputStream == null) {
			throw new ConfigFileNotFoundException("can't find "
					+ string_rule_config + " file");
		}
		// 获取配置文件
		Document xmlDocument = XMLUtil
				.getDocumentAtInputStream(configFileInputStream);

		// 先初始化导入配置文件
		NodeList stringRuleImportNodeList = xmlDocument
				.getElementsByTagName(string_rule_import_tag_name);
		String[] importFileNameArray = XMLUtil.getAllPropertyValue(
				stringRuleImportNodeList, string_rule_import_src_property_name);
		initImportConfig(importFileNameArray);

		// 再初始化本配置文件
		NodeList stringRuleNodeList = xmlDocument
				.getElementsByTagName(string_rule_tag_name);
		configStringRuleMap(stringRuleNodeList, string_rule_map);

		string_rule_map = Collections.unmodifiableMap(string_rule_map);
		// System.out.println(string_rule_map);
	}

	/**
	 * 初始化导入文件的配置
	 * 
	 * @throws ConfigFileNotFoundException
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static void initImportConfig(String[] importFileNameArray)
			throws ConfigFileNotFoundException, FactoryConfigurationError,
			ParserConfigurationException, SAXException, IOException {
		
		for (int i = 0; i < importFileNameArray.length; i++) {
			String configFileNmae = importFileNameArray[i];
			if (CheckUtil.isNotEmptyString(configFileNmae)) {
				configFileNmae = ConfigUtil.getRealPath(configFileNmae);
				InputStream configFileInputStream = CommonUtil.getInputStreamBySourceName(configFileNmae);
				if (configFileInputStream == null) {
					throw new ConfigFileNotFoundException("can't find "
							+ configFileNmae + " file");
				}
				// config mapping
				Document xmlDocument = XMLUtil
						.getDocumentAtInputStream(configFileInputStream);

				NodeList stringRuleNodeList = xmlDocument
						.getElementsByTagName(string_rule_tag_name);
				configStringRuleMap(stringRuleNodeList, string_rule_map);
			}			
		}
		// System.out.println(string_rule_map);
	}

	/**
	 * 构造字符串规则映射
	 * 
	 * @param stringRuleNodeList
	 * @param string_rule_map
	 */
	private static void configStringRuleMap(NodeList stringRuleNodeList,
			Map string_rule_map) {
		for (int i = 0; i < stringRuleNodeList.getLength(); i++) {
			Node stringRuleNode = stringRuleNodeList.item(i);
			String stringRuleName = XMLUtil.getNodePropertyValue(
					stringRuleNode, string_rule_name_property);
			StringRule stringRule = constructStringRule(stringRuleNode);
			string_rule_map.put(stringRuleName, stringRule);
		}
	}

	/**
	 * 构造单个规则
	 * 
	 * @param stringRuleNode
	 * @return
	 */
	public static StringRule constructStringRule(Node stringRuleNode) {
		StringRule stringRule = new StringRule();

		Node descriptionNode = XMLUtil.getFirstChildNodeByTagName(stringRuleNode,
				string_rule_description_tag_name);
		Node emptyableNode = XMLUtil.getFirstChildNodeByTagName(stringRuleNode,
				string_rule_emptyable_tag_name);
		Node matchNode = XMLUtil.getFirstChildNodeByTagName(stringRuleNode,
				string_rule_match_tag_name);
		Node messageNode = XMLUtil.getFirstChildNodeByTagName(stringRuleNode,
				string_rule_message_tag_name);
		String parentStringRuleName = XMLUtil.getNodePropertyValue(
				stringRuleNode, string_rule_parent_rule_property_name);

		if (CheckUtil.isNotEmptyString(parentStringRuleName)) {
			stringRule.setParentStringRule(getStringRule(parentStringRuleName));
		}

		if (CheckUtil.isNotNull(descriptionNode)) {
			stringRule.setDescription(XMLUtil.getNodeText(descriptionNode)
					.trim());
		}
		if (CheckUtil.isNotNull(emptyableNode)) {
			String emptyableValue = XMLUtil.getNodeText(emptyableNode);
			if (CheckUtil.isEmptyString(emptyableValue)) {
				stringRule.setEmptyable(true);
			} else {
				stringRule.setEmptyable(emptyableValue.trim());
			}
		}
		if (CheckUtil.isNotNull(matchNode)) {
			stringRule.setMatch(XMLUtil.getNodeText(matchNode).trim());
		}
		if (CheckUtil.isNotNull(messageNode)) {
			stringRule.setMessage(XMLUtil.getNodeText(messageNode).trim());
		}

		return stringRule;
	}

	public static void main(String[] args) throws ConfigFileNotFoundException,
			FactoryConfigurationError, ParserConfigurationException,
			SAXException, IOException {
		config();
	}
}
