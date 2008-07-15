/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
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
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: XMLUtil.java,v0.1 2007-12-6 下午01:46:58 cyyan Exp$
 */
public class XMLUtil {

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
