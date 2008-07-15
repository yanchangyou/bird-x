/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: CheckUtil.java,v0.1 2007-12-16 ����03:10:08 cyyan Exp$
 */
public class CheckUtil {

	public static boolean isEmptyString(String str) {
		return isNull(str) || str.matches("^\\s*$");		
	}
	
	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);		
	}
	
	public static boolean isEmptyCollection(Collection collection) {		
		return isNull(collection) || collection.size() == 0;
	}
	
	public static boolean isNotEmptyCollection(Collection collection) {		
		return !isEmptyCollection(collection);
	}
	
	public static boolean isEmptyMap(Map map) {		
		return isNull(map) || map.size() == 0;
	}
	
	public static boolean isNotEmptyMap(Map map) {		
		return !isEmptyMap(map);
	}
	
	public static boolean isNull(Object obj) {
		return null == obj;
	}
	
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}	
}
