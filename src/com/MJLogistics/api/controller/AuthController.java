package com.MJLogistics.api.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/** 
* @author Delicate
* @version 创建时间：2015年9月29日 上午12:33:25 
* @Explain 
*/
public class AuthController extends Controller{

	public void query(){
		String username = getPara("username", "");
	}
	@ActionKey("/api/auth/human/edit")
	public void humanEdit(){
		
	}
	@ActionKey("/api/auth/goods/edit")
	public void goodsEdit(){
		
	}
	@ActionKey("/api/auth/trucks/edit")
	public void trucksEdit(){
		
	}
	
}
