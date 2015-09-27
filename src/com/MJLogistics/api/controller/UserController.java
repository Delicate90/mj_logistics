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
import com.jfinal.upload.UploadFile;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:30:52 
* @Explain 用户功能
*/
public class UserController extends Controller{

	public void login(){
		String username = getPara("mobile","");
		String validateNum = getPara("validateNumber","");
		String deviceId = getPara("deviceId","");
		JSONObject items = User.login(username, validateNum, deviceId);
		renderJson("items", items);
	}
	public void add(){
		
	}
	@Before(TokenInterceptor.class)
	public void edit(){
		String username = getPara("username", "");
		System.out.println(username);
		String nickName = getPara("nickname", "");
		String name = getPara("name", "");
		int sex = getParaToInt("sex", 0);
		int role = getParaToInt("role", 0);
		String telephone = getPara("telephone", "");
		String area = getPara("area", "");
		String address = getPara("address", "");
		JSONObject items = User.edit(username, role, name, nickName, sex, telephone, area, address);
		System.out.println(items.toJSONString());
		renderJson("items",items);
	}
	@Before(TokenInterceptor.class)
	public void headerEdit(){
		UploadFile header = getFile("header");
		String username = getPara("username", "");
		JSONObject items = User.headerEdit(username, header.getFile());
		renderJson("items", items);
	}
	public void query(){
		
	}
}
