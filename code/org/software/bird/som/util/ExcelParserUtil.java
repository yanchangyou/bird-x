/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
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
 * @version $Id: Excel2ObjectUtil.java,v0.1 2007-12-6 下午01:55:32 cyyan Exp$
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
