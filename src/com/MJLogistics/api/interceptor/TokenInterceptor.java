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
		
//		String token = invocation.getController().getResponse().getHeader("agentToken");
		String token = invocation.getController().getRequest().getHeader("agentToken");
		if(token != null && !token.equals("")){
			Set<String> tokenSet = CacheKit.get("token", "tokenSet", new IDataLoader() {
				@Override
				public Object load() {
					Set<String> tokenSet = new HashSet<String>();
					return tokenSet;
				}
			});
			int oldSize = tokenSet.size();
			tokenSet.add(token);
			int newSize = tokenSet.size();
			if(oldSize == newSize){			
				invocation.invoke();
			}else{
				invocation.getController().renderJson("err", "token missing");
			}			
		}else{
			invocation.getController().renderJson("err", "token missing");
		}
	}

}
