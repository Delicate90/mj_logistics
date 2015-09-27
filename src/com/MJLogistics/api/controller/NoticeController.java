package com.MJLogistics.api.controller;

import com.MJLogistics.api.interceptor.TokenInterceptor;
import com.MJLogistics.api.model.Notice;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/** 
* @author Delicate
* @version 创建时间：2015年9月27日 下午9:55:12 
* @Explain 
*/
public class NoticeController extends Controller{
	@Before(TokenInterceptor.class)
	public void query(){
		String username = getPara("username","");
		System.out.println(username);
		JSONObject items = Notice.query(username);
		renderJson("items", items);
	}
	
}
