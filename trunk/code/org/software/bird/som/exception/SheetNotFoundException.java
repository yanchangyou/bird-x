/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.som.exception;
/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SheetNotExistException.java,v0.1 2007-12-17 ����09:21:04 cyyan Exp$
 */
public class SheetNotFoundException extends Exception {

	private static final long serialVersionUID = -5214500848044945652L;
	public SheetNotFoundException(String string) {
		super(string);
	}
}
