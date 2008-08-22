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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Excel2ObjectUtil.java,v0.1 2007-12-6 ÏÂÎç01:55:32 cyyan Exp$
 */
public class ExcelParserUtil {

	public static String getSheetNameByClass(Document xmlDocument,
			Class objClass) throws FactoryConfigurationError,
			ParserConfigurationException, SAXException, IOException {
		String sheetName = null;
		Node node = XMLUtil.getNodeByName(xmlDocument, "class", objClass
				.getName());
		sheetName = XMLUtil.getNodePropertyValue(node, "sheet");
		return sheetName;
	}

	public static Document getDocumentByConfigFileName(String configFileName)
			throws FactoryConfigurationError, ParserConfigurationException,
			SAXException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream configFileInputStream = classLoader.getResourceAsStream(configFileName);
		return XMLUtil.getDocumentByName(configFileInputStream);
	}	

	public static NodeList getPropertyList(Document xmlDocument, Class objClass) {
		Node classNode = null;
		classNode = getClassNodeByClass(xmlDocument, objClass);
		return classNode.getChildNodes();
	}

	public static NodeList getPropertyListByClass(Document xmlDocument,
			Class objClass) throws FactoryConfigurationError,
			ParserConfigurationException, SAXException, IOException {
		return getPropertyList(xmlDocument, objClass);
	}

	
	public static HSSFWorkbook getWorkbook(InputStream excelInputStream)
			throws FileNotFoundException, IOException, Exception {
		HSSFWorkbook wb = null;
		POIFSFileSystem fs = new POIFSFileSystem(excelInputStream);
		wb = new HSSFWorkbook(fs);
		return wb;
	}

	public static Node getClassNodeByClass(Document xmlDocument, Class objClass) {
		Node classNode = null;
		String className = objClass.getName();
		classNode = XMLUtil.getNodeByName(xmlDocument, "class", className);
		return classNode;
	}
}
