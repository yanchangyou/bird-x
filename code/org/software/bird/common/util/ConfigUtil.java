/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * ��Ȩ���� v1.0
 *
 * ������(����Դ����Ͷ�������)��֪ʶ��Ȩ(����������������Ȩ��ר������Ȩ���̱�Ȩ��ר�м���)������Ȩ��ʹ��Ȩ��

 * ת��Ȩ�Լ������һ��Ȩ��������ISoftstone���С�

 * ==============================================================================================================*/
package org.software.bird.common.util;

import java.io.File;

import org.software.bird.common.BirdConstant;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConfigUtil.java,v0.1 2007-12-16 ����03:06:16 cyyan Exp$
 */
public class ConfigUtil {

	public static String getRealPath(String path) {
		return path.replace(BirdConstant.separator_char_in_config_file, File.separatorChar);
	}
}
