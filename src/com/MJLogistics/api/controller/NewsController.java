package com.MJLogistics.api.controller;

import com.MJLogistics.api.interceptor.TokenInterceptor;
import com.MJLogistics.api.model.News;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:30:52 
* @Explain 新闻功能
*/
public class NewsController extends Controller{

	public void query(){
		int lastId = getParaToInt("lastId",0);
		JSONObject items = News.query(lastId);
		renderJson("items", items);
	}
	public void browse(){
		int newsId = getParaToInt("newsId",0);
		boolean rs = News.browse(newsId);
		renderJson("rs",rs);
	}
	@Before(TokenInterceptor.class)
	public void share(){
		int newsId = getParaToInt("newsId",0);
		boolean rs = News.share(newsId);
		renderJson("rs",rs);
	}
}
