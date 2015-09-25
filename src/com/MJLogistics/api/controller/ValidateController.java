package com.MJLogistics.api.controller;

import com.MJLogistics.api.model.Validate;
import com.jfinal.core.Controller;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:30:52 
* @Explain 验证功能
*/
public class ValidateController extends Controller{

	public void send(){
		String mobile = getPara("mobile","");
		boolean rs = Validate.send(mobile);
		renderJson("rs", rs);
	}
	
}
