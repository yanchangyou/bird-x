/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.util;

import java.lang.reflect.Method;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BeanUtil.java,v0.1 2007-12-14 ����05:35:29 cyyan Exp$
 */
public class BeanUtil {

	String str;
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public static void setPropertyValueOfBean(Object obj, String property, Object value) throws Exception, NoSuchMethodException {
		Class objClass = obj.getClass();
		Method getter = objClass.getMethod(CommonUtil
				.property2getterName(property), null);

		Method setter = objClass.getMethod(CommonUtil
				.property2setterName(property), new Class[] { getter.getReturnType()});

		setter.invoke(obj, new Object[] { value });
	}
	
	public static Class getPropertyClassOfBean(Object obj, String property) throws SecurityException, NoSuchMethodException {
		Method getter = obj.getClass().getMethod(CommonUtil
				.property2getterName(property), null);
		return getter.getReturnType();
	}
	
	public static void main(String[] args) throws NoSuchMethodException, Exception {
		BeanUtil aBeanUtil = new BeanUtil();
		setPropertyValueOfBean(aBeanUtil,"str","x100x");
		System.out.println(aBeanUtil.getStr());
	}
	
}
