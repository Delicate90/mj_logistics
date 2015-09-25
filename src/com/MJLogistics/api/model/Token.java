package com.MJLogistics.api.model;

import java.util.HashSet;
import java.util.Set;

import com.MJLogistics.Tools.CodeKit;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/** 
* @author Delicate
* @version 创建时间：2015年9月24日 下午10:51:15 
* @Explain Token逻辑实现
*/
public class Token {

	public static String add(String username,String deviceId){
		String timestamp = String.valueOf(System.currentTimeMillis());
		String token = CodeKit.formatSHA((username + deviceId + timestamp)) + "_" + timestamp;
		Set<String> tokenSet = CacheKit.get("token", "tokenSet", new IDataLoader() {
			@Override
			public Object load() {
				Set<String> tokenSet = new HashSet<String>();
				return tokenSet;
			}
		});
		tokenSet.add(token);
		CacheKit.put("token", "tokenSet", tokenSet);
		return token;
	}
	
	
}
