package com.ims.util.dic;

import java.util.HashMap;
import java.util.List;

import com.ims.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;

public class SDicHelper {
	private static HashMap<String,HashMap<String,String>> dic;

	public static HashMap<String, HashMap<String, String>> getDic() {
		return dic;
	}

	public static void setDic(HashMap<String, HashMap<String, String>> dic1) {
		dic = dic1;
	}
	
	public static HashMap<String, String> getOneDic(String op) {
		return dic.get(op);
	}
	
	public static String translate(String op,String en){
		if(dic == null || StringUtil.isNull(op)
				|| StringUtil.isNull(en)) 
			return "";
		HashMap<String,String> dicMap = dic.get(op);
		if(dicMap == null) return en;
		
		return dicMap.get(en) == null?en: dicMap.get(en);
	}
	
	public static String translateMany(String op,String ens){
		if(StringUtil.isNull(ens))
			return "";
		StringBuffer sb = new StringBuffer();
		String array[] = ens.split(",");
		for (int i = 0; i < array.length; i++) {
			String en = array[i];
			sb.append(translate(op, en));
			if(i != array.length -1) sb.append(",");
		}
		return sb.toString();
	}
	
	public static void loadDic(){
		try {
			String dicSql = "select en,ch,op from s_dic";
			List<Object[]> lists = Db.query(dicSql);
			if(lists != null){
				dic = new HashMap<String, HashMap<String, String>>();
				for (int i = 0; i < lists.size(); i++) {
					Object arr[] = (Object[]) lists.get(i);
					// 加载数据字典
					String en = (String)arr[0];
					String ch = (String)arr[1];
					String op =  (String)arr[2];

					HashMap<String, String> opMap = dic.get(op);
					if (dic.get(op) == null) {
						opMap = new HashMap<String, String>();
						dic.put(op, opMap);
					}
					opMap.put(en, ch);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public static void addOrUpd(String op,String en,String ch){
		try {
			HashMap<String, String> opMap = dic.get(op);
			if (dic.get(op) == null) {
				opMap = new HashMap<String, String>();
				dic.put(op, opMap);
			}
			opMap.put(en, ch);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
}
