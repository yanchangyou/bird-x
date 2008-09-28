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

import java.io.InputStream;
import java.util.Properties;

/**
 * Class description goes here.
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BootConfig.java,v0.1 2008-9-28 上午09:07:22 cyyan Exp$
 */
public class BootConfig {

	final public static String bootPropertiesPath = "ether.properties";
	final private static Properties bootProperties = new Properties();
	static {		
		try {
			InputStream configFileInputStream = CommonUtil.getInputStreamBySourceName(bootPropertiesPath);
			bootProperties.load(configFileInputStream);
		} catch (Exception e) {
			dealCompatibilityLE_1_5(bootProperties);
			System.out.println("Ether warning : ether.properties not found, this will deal with before 1.5;");
		}
	}
	
	public static String getProperty(String key) {
		return bootProperties.getProperty(key);
	}
	
	public static void main(String[] args) {
		String key = "ether.anima.bird.som.boot.path";
		System.out.println(BootConfig.getProperty(key));
	}
	
	/**
	 * 处理兼容性问题, 1.5之前使用的配置文件路径不规范, 从1.5之后修改成 config/config/bird/som/som.xml 
	 * 和 config/config/bird/rule/rule.xml 
	 * 此代码自动处理如果没有找到 ether.properties文件就认为是1.5之前的, 并自动替换为以前的路径
	 * @param bootProperties
	 */
	private static void dealCompatibilityLE_1_5(Properties bootProperties) {
		bootProperties.setProperty("ether.anima.bird.som.boot.path", "som_config|sheet_object_config.xml");
		bootProperties.setProperty("ether.anima.bird.rule.boot.path", "string_rule_config|string_rule_config.xml");
	}
	
}
