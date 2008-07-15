/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.som.util;

import java.io.File;
import java.io.InputStream;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Common.java,v0.1 2007-12-6 ����01:46:27 cyyan Exp$
 */
public class CommonUtil {

	public static String getClassNameWithoutPackage(Class aClass) {
		String nameWithPackage = aClass.getName();
		String[] nameUnitArray = nameWithPackage.split("\\.");
		return nameUnitArray[nameUnitArray.length - 1];
	}

	public static String getPackageName(Class aClass) {
		return aClass.getPackage().getName();
	}

	public static String getPackagePath(Class aClass) {
		return getPackageName(aClass).replace('.', File.separatorChar);
	}

	public static String property2getterName(String property) {
		return "get" + upperFirstLetter(property);
	}

	public static String property2setterName(String property) {
		return "set" + upperFirstLetter(property);
	}

	public static String upperFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String getBeforePointString(String str) {
		String[] part = str.split("\\.");
		return part[0];
	}

	public static InputStream getInputStreamBySourceName(String sourceName) {
		InputStream configFileInputStream = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		configFileInputStream = classLoader.getResourceAsStream(sourceName);
		return configFileInputStream;
	}
	
	public static InputStream getInputStreamBySoureNameInSamePackage(String sourceName, Class aClass) {
		InputStream configFileInputStream = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		configFileInputStream = classLoader.getResourceAsStream(getPackagePath(aClass) + File.separator + sourceName);
		return configFileInputStream;
	}
}
