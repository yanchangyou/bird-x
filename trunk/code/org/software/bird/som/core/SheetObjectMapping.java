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

package org.software.bird.som.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 瀵瑰簲閰嶇疆鏂囦欢鐨勭被<br>
 * 
 * 姣忎釜som鏍囩閮借瑙ｆ瀽鎴愪竴涓猄heetObjectMapping瀹炰緥锛岀劧鍚庣▼搴忔牴鎹畇om瑙ｆ瀽excel銆�<br>
 * 涓嬩竴鐗堜腑灏嗕娇鐢╬roterpty绫讳唬鏇縈ap
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: SheetObjectMapping.java,v0.1 2007-12-5 涓嬪崍03:42:01 cyyan Exp$
 */
public class SheetObjectMapping {

	/**
	 * 瀵筫xcel鐨勫紩鐢�, 鐢变簬excel鐨勬柟绋嬶紝鏍峰紡绛夌敱workbook寮曠敤, 姝ゅ彉閲忎綔鐢ㄥ氨鏄敤浜庡叏灞�寮曠敤鐨�
	 * 锛堣繖涓嶆槸涓�绉嶅緢濂界殑澶勭悊鏂瑰紡锛屾湁寰呮敼鍠勶紝瀹冧笌杩欎釜绫荤殑鑱岃矗涓嶅尮閰嶏級
	 */
	public ExcelReference excelReference = new ExcelReference();

	/**
	 * 瑕佹槧灏勭殑绫�
	 */
	private Class objClass;	

	/**
	 * 宸ヤ綔琛ㄥ悕
	 */
	private String sheetName;

	/**
	 * 鍦ㄥ伐浣滆〃涓爣棰樿鐨勮鍙� 1-based锛屼互1寮�濮嬭鏁�
	 */
	private int titleRowIndex = 0;

	/**
	 * 鍦ㄥ伐浣滆〃涓涓�鏉℃暟鎹锛� 1-based锛屼互1寮�濮嬭鏁�
	 */
	private int firstDataRowIndex = 1;

	/**
	 * 鍦ㄥ伐浣滆〃涓暟鎹鍙栫殑鏈�澶氳鏁�
	 */
	private int dataRowMaxNumber = -1;

	/**
	 * 瀵硅薄鐨勫睘鎬у埌宸ヤ綔琛ㄧ殑鍒楀ご鐨勬槧灏�
	 */
	private Map propertyTitleMap;

	/**
	 * 宸ヤ綔琛ㄥ垪澶村埌宸ヤ綔琛ㄥ垪鐨勬槧灏�
	 */
	private Map titleColumnMap;

	/**
	 * 瀵硅薄灞炴�у埌宸ヤ綔琛ㄥ垪鐨勬槧灏�
	 */
	private Map propertyColumnMap;

	/**
	 * 灞炴�х殑瀛楃瑙勫垯鏄犲皠
	 */
	private Map propertyStringRuleMap;

	/**
	 * 鑾峰彇瀵硅薄鐨凜lass
	 * 
	 * @return 杩斿洖瀵硅薄鐨凜lass
	 */
	public Class getObjClass() {
		return objClass;
	}

	/**
	 * 璁剧疆瀵硅薄鐨凜lass
	 * 
	 * @param objClass 瑕佽缃殑Class
	 */
	public void setObjClass(Class objClass) {
		this.objClass = objClass;
	}

	/**
	 * 鑾峰彇瀵硅薄灞炴�э紙property锛夊埌宸ヤ綔琛ㄥ垪澶寸殑鏄犲皠
	 * @return 杩斿洖鏄犲皠
	 */
	public Map getPropertyTitleMap() {
		return propertyTitleMap;
	}

	/**
	 * 璁剧疆瀵硅薄灞炴�у埌宸ヤ綔琛ㄥ垪澶寸殑鏄犲皠
	 * @param propertyTitleMap
	 */
	public void setPropertyTitleMap(Map propertyTitleMap) {
		this.propertyTitleMap = propertyTitleMap;
	}

	/**
	 * 鑾峰彇宸ヤ綔琛ㄧ殑鍚嶇О
	 * @return 宸ヤ綔琛ㄧ殑鍚嶇О
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * 璁剧疆宸ヤ綔琛ㄧ殑鍚嶇О
	 * @param sheetName 宸ヤ綔琛ㄧ殑鍚嶇О
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 鑾峰彇宸ヤ綔琛ㄥ垪澶村埌鍒楃殑鏄犲皠
	 * @return 宸ヤ綔琛ㄥ垪澶村埌鍒楃殑鏄犲皠
	 */
	public Map getTitleColumnMap() {
		return titleColumnMap;
	}

	/**
	 * 璁剧疆宸ヤ綔琛ㄥ垪澶村埌鍒楃殑鏄犲皠
	 * @param titleColumnMap 宸ヤ綔琛ㄥ垪澶村埌鍒楃殑鏄犲皠
	 */
	public void setTitleColumnMap(Map titleColumnMap) {
		this.titleColumnMap = titleColumnMap;
	}

	/**
	 * 杞崲浣嶅瓧绗︿覆
	 */
	public String toString() {
		return sheetName + ":\n" + titleColumnMap + "\n" + objClass + ":\n"
				+ propertyTitleMap;
	}

	/**
	 * 鑾峰彇灞炴�у垪鐨勬槧灏�
	 * @return
	 */
	public Map getPropertyColumnMap() {
		if (propertyColumnMap == null) {
			configPropertyColumnMap();
		}
		return propertyColumnMap;
	}

	/**
	 * 閰嶇疆灞炴�у埌鍒楃殑鏄犲皠
	 *
	 */
	public void configPropertyColumnMap() {
		propertyColumnMap = new HashMap();
		Set propertySet = propertyTitleMap.keySet();
		Iterator propertyIt = propertySet.iterator();
		while (propertyIt.hasNext()) {
			Object property = propertyIt.next();
			Object column = titleColumnMap.get(propertyTitleMap.get(property));
			propertyColumnMap.put(property, column);
		}
	}

	/**
	 * 鑾峰彇绗竴琛屾暟鎹湪宸ヤ綔琛ㄧ殑琛屾暟
	 * @return 琛屾暟
	 */
	public int getFirstDataRowIndex() {
		return firstDataRowIndex;
	}

	/**
	 * 璁剧疆绗竴涓暟鎹浣嶇疆
	 * @param firstDataRowIndex
	 */
	public void setFirstDataRowIndex(int firstDataRowIndex) {
		this.firstDataRowIndex = firstDataRowIndex;
	}

	/**
	 * 鑾峰彇宸ヤ綔琛ㄥ垪澶磋浣嶇疆
	 * @return 宸ヤ綔琛ㄦ爣棰樿浣嶇疆
	 */
	public int getTitleRowIndex() {
		return titleRowIndex;
	}

	/**
	 * 璁剧疆宸ヤ綔琛ㄤ腑鍒楀ご琛岀殑浣嶇疆
	 * @param titleRowIndex 鍒楀ご琛岀殑浣嶇疆
	 */
	public void setTitleRowIndex(int titleRowIndex) {
		this.titleRowIndex = titleRowIndex;
	}

	/**
	 * 鑾峰彇灞炴�у瓧绗﹁鍒欐槧灏刴ap
	 * @return 灞炴�у瓧绗﹁鍒欐槧灏刴ap
	 */
	public Map getPropertyStringRuleMap() {
		return propertyStringRuleMap;
	}

	/**
	 * 璁剧疆灞炴�у瓧绗﹁鍒欐槧灏刴ap
	 * @param propertyStringRuleMap 灞炴�у瓧绗﹁鍒欐槧灏刴ap
	 */
	public void setPropertyStringRuleMap(Map propertyStringRuleMap) {
		this.propertyStringRuleMap = propertyStringRuleMap;
	}
	
	/**
	 * 璁剧疆瀵硅薄灞炴�у埌宸ヤ綔琛ㄥ垪鐨勬槧灏�
	 * @param propertyColumnMap 瀵硅薄灞炴�у埌宸ヤ綔琛ㄥ垪鐨勬槧灏�
	 */
	public void setPropertyColumnMap(Map propertyColumnMap) {
		this.propertyColumnMap = propertyColumnMap;
	}

	/**
	 * 鑾峰彇鎸囧畾鑳借幏鍙栫殑鏈�澶ф暟鎹鏁�
	 * @return 鏈�澶ц鏁�
	 */
	public int getDataRowMaxNumber() {
		return dataRowMaxNumber;
	}

	/**
	 * 璁剧疆鎸囧畾鑳借幏鍙栫殑鏈�澶ф暟鎹鏁�
	 * @param dataRowMaxNumber 鎸囧畾鑳借幏鍙栫殑鏈�澶ф暟鎹鏁�
 	 */
	public void setDataRowMaxNumber(int dataRowMaxNumber) {
		this.dataRowMaxNumber = dataRowMaxNumber;
	}

	/**
	 * 鎻愪緵excel鐨勫叏灞�寮曠敤, 澶勭悊鍑芥暟绛夊彧鑳芥槸涓�涓猠xcel鍐呴儴浣跨敤
	 * @author cyyan
	 *
	 */
	class ExcelReference {

		/**
		 * poi涓伐浣滆杽瀵硅薄
		 */
		public HSSFWorkbook workbook;

		/**
		 * poi涓伐浣滆杽瀵硅薄
		 */
		public HSSFSheet sheet;

		/**
		 * poi涓瀵硅薄
		 */
		public HSSFRow row;

		/**
		 * 鑾峰彇poi琛屽璞�
		 * @return
		 */
		public HSSFRow getRow() {
			return row;
		}

		/**
		 * 璁剧疆poi琛屽璞�
		 * @param row poi琛屽璞�
		 */
		public void setRow(HSSFRow row) {
			this.row = row;
		}

		/**
		 * 鑾峰彇poi宸ヤ綔琛ㄥ璞�
		 * @return poi宸ヤ綔钖勫璞�
		 */
		public HSSFSheet getSheet() {
			return sheet;
		}

		/**
		 * 璁剧疆poi宸ヤ綔钖勫璞�
		 * @param sheet poi宸ヤ綔钖勫璞�
		 */
		public void setSheet(HSSFSheet sheet) {
			this.sheet = sheet;
		}

		/**
		 * 鑾峰彇poi宸ヤ綔钖勫璞�
		 * @return poi宸ヤ綔钖勫璞�
		 */
		public HSSFWorkbook getWorkbook() {
			return workbook;
		}

		/**
		 * 璁剧疆poi宸ヤ綔钖勫璞�
		 * @param workbook poi宸ヤ綔钖勫璞�
		 */
		public void setWorkbook(HSSFWorkbook workbook) {
			this.workbook = workbook;
		}

	}
}
