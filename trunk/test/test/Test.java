/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.software.bird.common.util.CommonUtil;
import org.software.bird.som.ExcelIO;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: Test.java,v0.1 2008-9-26 ����04:52:07 cyyan Exp$
 */
public class Test {

	public static void main(String[] args) throws Exception {
		
		
		System.out.println("����".matches("[^\\x00-\\xff]{2,5}"));

		String fileName = "bin/���� ����.xls";
		//File file = new File(fileName);
		//System.out.println(file.getAbsolutePath());
		//InputStream inputStream = CommonUtil.getInputStreamBySourceName(fileName);
		

		long time1 = 0;
		long time2 = 0;
		long time3 = 0;
		
		time1 = System.currentTimeMillis();
		
		ExcelIO aExcelIO = new ExcelIO(fileName);
		time2 = System.currentTimeMillis();
		List list = aExcelIO.readAll(Student.class);
		time3 = System.currentTimeMillis();

		System.out.println(list.size() + "��");
		
		System.out.println("��ȡexcel��poi���� �� " + (time2 - time1)/1000.0 + "��");
		System.out.println("����poi�����list �� " + (time3 - time2)/1000.0 + "��");
		System.out.println("��ȡ+����  �� " + (time3 - time1)/1000.0 + "��");
	}
}
