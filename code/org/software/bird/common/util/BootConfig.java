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
 * 澶勭悊鏍稿績閰嶇疆鏂囦欢锛屽叾瀹冪殑閰嶇疆鏂囦欢璺緞閮藉湪姝ゆ枃浠朵腑杩涜閰嶇疆锛岀▼搴忓氨閽堝姝ゆ枃浠惰繘琛屽鐞嗭紝涓�涓粺涓�鐨勫叆鍙�
 *
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: BootConfig.java,v0.1 2008-9-28 涓婂崍09:07:22 cyyan Exp$
 */
public class BootConfig {

	/**
	 * 鏍稿績閰嶇疆鏂囦欢璺緞
	 */
	final public static String bootPropertiesPath = "ether.properties";
	final private static Properties bootProperties = new Properties();
	static {
		/**
		 * 鑷姩鍒濆鍖�, 鍔犺浇閰嶇疆鏂囦欢
		 */
		try {
			InputStream configFileInputStream = CommonUtil.getInputStreamBySourceName(bootPropertiesPath);
			bootProperties.load(configFileInputStream);
		} catch (Exception e) {
			dealCompatibilityLE_1_5(bootProperties);
			System.out.println("Ether warning : ether.properties not found, this will deal with before 1.5;");
		}
	}
	/**
	 * 鑾峰彇鏍归厤缃枃浠剁殑灞炴��
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return bootProperties.getProperty(key);
	}
	
	public static void main(String[] args) {
		String key = "ether.anima.bird.som.boot.path";
		System.out.println(BootConfig.getProperty(key));
	}
	
	/**
	 * 澶勭悊鍏煎鎬ч棶棰�, 1.5涔嬪墠浣跨敤鐨勯厤缃枃浠惰矾寰勪笉瑙勮寖, 浠�1.5涔嬪悗淇敼鎴� config/config/bird/som/som.xml 
	 * 鍜� config/config/bird/rule/rule.xml 
	 * 姝や唬鐮佽嚜鍔ㄥ鐞嗗鏋滄病鏈夋壘鍒� ether.properties鏂囦欢灏辫涓烘槸1.5涔嬪墠鐨�, 骞惰嚜鍔ㄦ浛鎹负浠ュ墠鐨勮矾寰�
	 * @param bootProperties
	 */
	private static void dealCompatibilityLE_1_5(Properties bootProperties) {
		bootProperties.setProperty("ether.anima.bird.som.boot.path", "som_config|sheet_object_config.xml");
		bootProperties.setProperty("ether.anima.bird.rule.boot.path", "string_rule_config|string_rule_config.xml");
	}
	
}
