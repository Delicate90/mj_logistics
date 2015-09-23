package com.MJLogistics.api.interceptor;

import java.util.ArrayList;
import java.util.List;

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
		List<String> testList = CacheKit.get("testList", "testList", new IDataLoader() {
			@Override
			public Object load() {
				List<String> newList = new ArrayList<String>();
				return newList;
			}
		});
		System.out.println("inInterceptor:"+testList.toString());
		invocation.invoke();
	}

}
