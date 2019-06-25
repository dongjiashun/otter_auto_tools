package com.yh.spring.ssm.util;

import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

public class CollectionUtils {
	 
	 
	 
	 public static Map<String,Object> listForMap(List<Map<String,Object>> a){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			for (Map<String, Object> map :  a) {
				String region = null;
				Object id = null;
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if ("userId".equals(entry.getKey())) {
						region = String.valueOf(  entry.getValue());
					} else if ("count".equals(entry.getKey())) {
						id =  entry.getValue();
					}
				}
				resultMap.put(region, id);
			}
		  return resultMap ;
	 }
}
