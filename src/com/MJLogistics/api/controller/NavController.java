package com.MJLogistics.api.controller;

import com.MJLogistics.api.model.Nav;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:30:52 
* @Explain 九宫格-十六宫格导航功能
*/
public class NavController extends Controller{

	public void query(){
		JSONObject items = Nav.query();
		renderJson("items", items);
	}
	
}
