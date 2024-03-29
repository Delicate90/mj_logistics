package com.MJLogistics.api.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/** 
* @author Delicate
* @version 创建时间：2015年9月27日 下午11:27:03 
* @Explain 
*/
public class News {

	public static JSONObject queryList(int lastId){
		JSONObject items = new JSONObject();
		List<Record> newsList;
		if(lastId == 0){
			newsList = Db.find("SELECT vn.*,vne.browse,vne.share FROM v_news AS vn LEFT JOIN v_news_extend AS vne ON vn.id = vne.newsId WHERE vn.type = 2 AND vn.status = 3 ORDER BY vn.updateTime DESC LIMIT 10");
		}else{
			newsList = Db.find("SELECT vn.*,vne.browse,vne.share FROM v_news AS vn LEFT JOIN v_news_extend AS vne ON vn.id = vne.newsId WHERE vn.id < ? AND vn.type = 2 AND vn.status = 3 ORDER BY vn.updateTime DESC LIMIT 10",lastId);
		}
		items.put("list", JsonKit.toJson(newsList));
		return items;
	}
	public static JSONObject query(int newsId){
		JSONObject items = new JSONObject();
		Record news = Db.findById("v_news", newsId);
		items.put("news", news.toJson());
		return items;
	}
	public static boolean browse(int newsId){
		boolean rs = false;
		Record news = Db.findFirst("SELECT * FROM v_news WHERE type = 2 AND status = 3 AND id = ?",newsId);
		if(news != null){
			int result = Db.update("UPDATE v_news_extend SET browse = browse + 1 WHERE newsId = ?",newsId);
			rs = true;
		}
		return rs;
	}
	public static boolean share(int newsId){
		boolean rs = false;
		Record news = Db.findFirst("SELECT * FROM v_news WHERE type = 2 AND status = 3 AND id = ?",newsId);
		if(news != null){
			int result = Db.update("UPDATE v_news_extend SET share = share + 1 WHERE newsId = ?",newsId);
			rs = true;
		}
		return rs;
	}
}
