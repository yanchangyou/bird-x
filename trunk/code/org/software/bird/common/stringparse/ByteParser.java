/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.stringparse;


import org.software.bird.common.exception.OutOfRangeException;
import org.software.bird.som.exception.InvalidNumberException;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: t.java,v0.1 2007-12-7 ����05:44:50 cyyan Exp$
 */

public class ByteParser extends NumberParser {

	public Object parse(String str) throws InvalidNumberException, OutOfRangeException {
		Number num = (Number) super.parse(str);
		double value = num.doubleValue();
		if (!(Byte.MIN_VALUE <= value && value <= Byte.MAX_VALUE)) {
			throw new OutOfRangeException("out byteVar range [" + Byte.MIN_VALUE + "," + Byte.MAX_VALUE + "]");
		}
		Byte byteVar = new Byte(num.byteValue());
		return byteVar;
	}
}
