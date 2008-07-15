/* Copyright(c) ISoftstone,2006 - 2008
 * 
 * 版权声明 v1.0
 *
 * 本程序(包括源代码和二进制码)的知识产权(包括但不限于著作权、专利申请权、商标权、专有技术)的所有权、使用权、

 * 转让权以及收益等一切权利均属于ISoftstone所有。

 * ==============================================================================================================*/
package org.software.bird.common.util;

import java.io.File;

import org.software.bird.common.BirdConstant;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConfigUtil.java,v0.1 2007-12-16 下午03:06:16 cyyan Exp$
 */
public class ConfigUtil {

	public static String getRealPath(String path) {
		return path.replace(BirdConstant.separator_char_in_config_file, File.separatorChar);
	}
}
