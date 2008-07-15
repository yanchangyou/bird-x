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
 * @version $Id: DoubleParsor.java,v0.1 2007-12-7 ����04:38:28 cyyan Exp$
 */
public class IntegerParser extends NumberParser {

	public Object parse(String str) throws InvalidNumberException,
			OutOfRangeException {
		Number num = (Number) super.parse(str);
		double value = num.doubleValue();
		if (!(Integer.MIN_VALUE <= value && value <= Integer.MAX_VALUE)) {
			throw new OutOfRangeException("out doubleVar range ["
					+ Integer.MIN_VALUE + "," + Integer.MAX_VALUE + "]");
		}
		Integer doubleVar = new Integer(num.intValue());
		return doubleVar;
	}
}
