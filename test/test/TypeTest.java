/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package test;

import java.util.Date;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: typeTest.java,v0.1 2008-1-16 ����07:21:22 cyyan Exp$
 */
public class TypeTest {

	private Double number;

	private Date date;

	private String string;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double num) {
		this.number = num;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String toString() {
		return "(" + this.number + "," + 
				this.date + "," + 
				this.string + ")\n";
	}
}
