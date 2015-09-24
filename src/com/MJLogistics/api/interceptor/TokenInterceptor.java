package com.MJLogistics.api.interceptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:33:18 
* @Explain AgentToken
*/
public class TokenInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation invocation) {
		Set<String> newSet = CacheKit.get("token", "testList", new IDataLoader() {
			@Override
			public Object load() {
				Set<String> newSet = new HashSet<String>();
				return newSet;
			}
		});
		System.out.println("inInterceptor:"+newSet.toString());
		Set<String> newSet1 = CacheKit.get("token", "testList1", new IDataLoader() {
			@Override
			public Object load() {
				Set<String> newSet = new HashSet<String>();
				return newSet;
			}
		});
		System.out.println("inInterceptor1:"+newSet1.toString());
		invocation.invoke();
	}

}
