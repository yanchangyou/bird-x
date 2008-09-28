/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.software.bird.common.util;

import java.io.File;

import org.software.bird.common.BirdConstant;

/**
 * 所有配置文件通用函数
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ConfigUtil.java,v0.1 2007-12-16 下午03:06:16 cyyan Exp$
 */
public class ConfigUtil {

	/**
	 * 获取配置文件的真实路径
	 * 配置文件的路径分隔符是｜，此方法就将｜转换为对于操作系统的路径分隔符
	 * @param path
	 * @return
	 */
	public static String getRealPath(String path) {		
		return path == null ? null : path.replace(BirdConstant.separator_char_in_config_file, File.separatorChar);
	}
}
