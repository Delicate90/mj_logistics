package com.MJLogistics.config;

import java.util.Properties;

import com.MJLogistics.Route.Admin;
import com.MJLogistics.Route.Api;
import com.MJLogistics.Route.WebSite;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
/** 
 * 项目基本配置
 * 崔翔-Delicate
 * 2015-09-23 22:07
 * **/
public class baseConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setDevMode(true);
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		//加载配置文件
		Properties properties = loadPropertyFile("config.properties");
		String url = "jdbc:mysql://"+properties.getProperty("sys_mysql_url")+":"+properties.getProperty("sys_mysql_port")+"/"+properties.getProperty("sys_mysql_sqlname");
		String username = properties.getProperty("sys_mysql_username");
		String password = properties.getProperty("sys_mysql_password");
		//连接池
		DruidPlugin druidPlugin = new DruidPlugin(url, username, password);
		me.add(druidPlugin);
		//arp
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		me.add(activeRecordPlugin);
	}

	@Override
	public void configRoute(Routes me) {
		//加载Route集合
		me.add(new Admin());
		me.add(new Api());
		me.add(new WebSite());
	}

}
