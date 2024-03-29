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

package org.software.bird.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 解析xml文件的常用函数
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: XMLUtil.java,v0.1 2007-12-6 下午01:46:58 cyyan Exp$
 */
public class XMLUtil {

	/**
	 * 按名获取节点
	 * @param xmlDocument
	 * @param tagName
	 * @param nodeNameValue
	 * @return
	 */
	public static Node getNodeByName(Document xmlDocument, String tagName,
			String nodeNameValue) {
		Node node = null;
		NodeList classList = null;
		classList = xmlDocument.getElementsByTagName(tagName);
		for (int i = 0; i < classList.getLength(); i++) {
			node = classList.item(i);
			String name = getNodePropertyValue(node, "name");
			if (name.equals(nodeNameValue)) {
				break;
			}
		}
		return node;
	}

	/**
	 * 获取标签中的第一个节点
	 * @param parentNode
	 * @param tagName
	 * @return
	 */
	public static Node getFirstChildNodeByTagName(Node parentNode, String tagName) {
		if (parentNode == null) {
			return null;
		}
		Node node = null;
		NodeList nodeList = null;
		
		nodeList = parentNode.getChildNodes();
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				String nodeName = node.getNodeName();
				if (nodeName.equals(tagName)) {
					break;
				}
			}
		}
		return node;
	}

	/**
	 * 获取某标签的所有节点
	 * @param parentNode
	 * @param tagName
	 * @return
	 */
	public static List getAllSubNodeByTagName(Node parentNode, String tagName) {
		if (parentNode == null) {
			return null;
		}
		List allNodeList = new ArrayList();
		Node node = null;
		NodeList nodeList = null;
		
		nodeList = parentNode.getChildNodes();
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				String nodeName = node.getNodeName();
				if (nodeName.equals(tagName)) {
					allNodeList.add(node);
				}
			}
		}
		return allNodeList;
	}
	
	/**
	 * 获取节点文本
	 * @param node
	 * @return
	 */
	public static String getNodeText(Node node) {
		String text = null;
		if (node.getNodeType() == Node.TEXT_NODE) {
			text = node.getNodeValue().trim();
		} else {
			NodeList nodeList = null;
			nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node realNode = nodeList.item(i);
				if (realNode.getNodeType() == Node.TEXT_NODE) {
					text = realNode.getNodeValue().trim();
					break;
				}
			}
		}
		return text;
	}

	/**
	 * 获取节点的属性值
	 * @param node
	 * @param propertyName
	 * @return
	 */
	public static String getNodePropertyValue(Node node, String propertyName) {
		if (node == null) {
			return null;
		}
		String propertyValue = null;
		NamedNodeMap aNamedNodeMap = node.getAttributes();
		if (aNamedNodeMap == null) {
			propertyValue = null;
		} else {
			Node nameItem = aNamedNodeMap.getNamedItem(propertyName);
			propertyValue = nameItem == null ? null : nameItem.getNodeValue();
		}

		return propertyValue;
	}

	/**
	 * 从流中获取poi对象的Document对象
	 * @param inputStream
	 * @return
	 * @throws FactoryConfigurationError
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document getDocumentAtInputStream(
			InputStream inputStream)
			throws FactoryConfigurationError, ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document xmlDocument = db.parse(inputStream);
		inputStream.close();
		return xmlDocument;
	}

	/**
	 * 获取所有的节点值
	 * @param nodeList
	 * @param propertyName
	 * @return
	 */
	public static String[] getAllPropertyValue(NodeList nodeList,
			String propertyName) {
		String[] propertyValueArray = new String[nodeList.getLength()];
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			propertyValueArray[i] = getNodePropertyValue(node, propertyName);
		}
		return propertyValueArray;
	}

}
