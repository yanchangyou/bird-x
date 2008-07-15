/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.som.util;
/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Checkor.java,v0.1 2007-12-7 ����07:04:53 cyyan Exp$
 */
public class CheckeUtil {

	public static boolean isEmptyString(String string) {
		return string == null || string.matches("^\\s*$");
	}
	
	public static boolean isOutOfMaxLength(String string, int length) {
		return string.length() > length;
	}
}
