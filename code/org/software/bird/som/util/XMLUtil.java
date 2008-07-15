/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.som.util;

import java.io.IOException;
import java.io.InputStream;

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
 * @version $Id: XMLUtil.java,v0.1 2007-12-6 ����01:46:58 cyyan Exp$
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

	public static String getNodePropertyValue(Node node, String propertyName) {
		String propertyValue = null;
		NamedNodeMap aNamedNodeMap = node.getAttributes();
		Node nameItem = aNamedNodeMap.getNamedItem(propertyName);

		propertyValue = nameItem == null ? null : nameItem.getNodeValue();

		return propertyValue;
	}

	public static Document getDocumentByName(InputStream configFileInputStream) throws FactoryConfigurationError,
			ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document xmlDocument = db.parse(configFileInputStream);
		configFileInputStream.close();
		return xmlDocument;
	}

}
