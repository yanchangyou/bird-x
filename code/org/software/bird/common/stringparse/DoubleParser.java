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
 * @version $Id: asdfasd.java,v0.1 2007-12-7 ����05:45:11 cyyan Exp$
 */

public class DoubleParser extends NumberParser {

	public Object parse(String str) throws InvalidNumberException, OutOfRangeException {
		Number num = (Number) super.parse(str);
		double value = num.doubleValue();
		if (!(-Double.MAX_VALUE <= value && value <= Double.MAX_VALUE)) {
			throw new OutOfRangeException("out doubleVar range [" + Double.MIN_VALUE + "," + Double.MAX_VALUE + "]");
		}
		Double doubleVar = new Double(num.doubleValue());
		return doubleVar;
	}
}
