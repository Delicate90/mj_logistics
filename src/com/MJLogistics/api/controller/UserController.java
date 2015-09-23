package com.MJLogistics.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.MJLogistics.api.interceptor.TokenInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:30:52 
* @Explain 用户功能
*/
public class UserController extends Controller{

	public void add(){
		
	}
	public void edit(){
		
	}
	public void query(){
		
	}
	@Before(TokenInterceptor.class)
	public void test(){
		String test = getPara("test","1");
//		List<String> sss = CacheKit.getKeys("testList");
//		System.out.println(sss.toString());
		List<String> testList = CacheKit.get("testList", "testList", new IDataLoader() {
			@Override
			public Object load() {
				List<String> newList = new ArrayList<String>();
				return newList;
			}
		});
		testList.add(test);
		System.out.println("inController:"+testList.toString());
		CacheKit.put("testList", "testList", testList);
	}
}
