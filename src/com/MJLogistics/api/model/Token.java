package com.MJLogistics.api.model;

import java.util.Set;

import com.MJLogistics.Tools.CodeKit;
import com.jfinal.plugin.ehcache.CacheKit;

/** 
* @author Delicate
* @version 创建时间：2015年9月24日 下午10:51:15 
* @Explain Token逻辑实现
*/
public class Token {

	public void add(String username,String deviceId){
		String timestamp = String.valueOf(System.currentTimeMillis());
		String token = CodeKit.formatSHA((username + deviceId + timestamp)) + "_" + timestamp;
//		Set<String> tokenSet = CacheKit.get("", key, dataLoader)
	}
	
	
}
