package com.MJLogistics.api.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.MJLogistics.api.interceptor.TokenInterceptor;
import com.MJLogistics.api.model.User;
import com.alibaba.fastjson.JSONObject;
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

	public void login(){
		String username = getPara("username","");
		String validateNum = getPara("validateNum","");
		String deviceId = getPara("deviceId","");
		JSONObject items = User.login(username, validateNum, deviceId);
		renderJson("items", items);
	}
	public void add(){
		
	}
	public void edit(){
		
	}
	public void query(){
		
	}
	@Before(TokenInterceptor.class)
	public void test(){
		String test = getPara("test","1");
		List sss = CacheKit.getKeys("token");
		System.out.println("sss1:"+sss.get(0));
		System.out.println("sss2:"+sss.get(1));
		Set<String> newSet = CacheKit.get("token", "testList", new IDataLoader() {
			@Override
			public Object load() {
				Set<String> newSet = new HashSet<String>();
				return newSet;
			}
		});
		Set<String> newSet1 = CacheKit.get("token", "testList1", new IDataLoader() {
			@Override
			public Object load() {
				Set<String> newSet = new HashSet<String>();
				return newSet;
			}
		});
		int oldLength = newSet.size();
		newSet.add(test);
		int newLength = newSet.size();
		newSet1.add("1111"+test);
		CacheKit.put("token", "testList1", newSet);
		CacheKit.put("token", "testList", newSet1);
		renderJson();
	}
}
