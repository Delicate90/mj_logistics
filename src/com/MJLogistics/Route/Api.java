package com.MJLogistics.Route;

import com.MJLogistics.api.controller.NavController;
import com.MJLogistics.api.controller.UserController;
import com.MJLogistics.api.controller.ValidateController;
import com.jfinal.config.Routes;
/** 
 * api接口路由配置
 * 崔翔-Delicate
 * 2015-09-23 22:07
 * **/
public class Api extends Routes{

	@Override
	public void config() {
		
		add("/api/user", UserController.class);
		add("/api/nav", NavController.class);
		add("/api/validate", ValidateController.class);
		
	}

}
