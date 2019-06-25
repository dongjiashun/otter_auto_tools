//package com.yh.spring.ssm.util;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.collections4.map.LinkedMap;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//@SuppressWarnings({ "unchecked", "rawtypes" })
//public class JsonUtils {
//	 public static Map json2Map(String json) {    
//	        LinkedMap map = new LinkedMap();    
//	        JSONObject js = JSONObject.fromObject(json);    
//	        populate(js, map);    
//	        return map;    
//	    }    
//	  
//	    /** 
//	      *<p>类描述：json中的键值对解析成map。</p> 
//	     */  
//	    private static Map populate(JSONObject jsonObject, Map map) {   
//	        for (Iterator iterator = jsonObject.entrySet().iterator(); iterator    
//	                .hasNext();) {    
//	            String entryStr = String.valueOf(iterator.next());    
//	            String key = entryStr.substring(0, entryStr.indexOf("="));    
//	            String value = entryStr.substring(entryStr.indexOf("=") + 1,    
//	                    entryStr.length());    
//	            if (jsonObject.get(key).getClass().equals(JSONObject.class)) {    
//	                HashMap _map = new HashMap();    
//	                map.put(key, _map);    
//	                populate(jsonObject.getJSONObject(key), ((Map) (_map)));    
//	            } else if (jsonObject.get(key).getClass().equals(JSONArray.class)) {    
//	                ArrayList list = new ArrayList();    
//	                map.put(key, list);    
//	                populateArray(jsonObject.getJSONArray(key), list);    
//	            } else {    
//	                map.put(key, jsonObject.get(key));    
//	            }    
//	        }    
//	  
//	        return map;    
//	    }    
//	  
//	    /** 
//	      *<p>类描述：如果是键对应数组,则返回一个list到上级的map里。</p> 
//	     */  
//	
//		private static void populateArray(JSONArray jsonArray, List list) {    
//	        for (int i = 0; i < jsonArray.size(); i++)    
//	            if (jsonArray.get(i).getClass().equals(JSONArray.class)) {    
//	                ArrayList _list = new ArrayList();    
//	                list.add(_list);    
//	                populateArray(jsonArray.getJSONArray(i), _list);    
//	            } else if (jsonArray.get(i).getClass().equals(JSONObject.class)) {    
//	                HashMap _map = new HashMap();    
//	                list.add(_map);    
//	                populate(jsonArray.getJSONObject(i), _map);    
//	            } else {    
//	                list.add(jsonArray.get(i));    
//	            }    
//	    }   
//}
