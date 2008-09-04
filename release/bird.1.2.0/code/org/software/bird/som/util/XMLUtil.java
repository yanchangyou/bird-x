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
 * @version $Id: XMLUtil.java,v0.1 2007-12-6 ÏÂÎç01:46:58 cyyan Exp$
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
