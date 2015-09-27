package com.MJLogistics.api.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/** 
* @author Delicate
* @version 创建时间：2015年9月27日 下午9:57:45 
* @Explain 
*/
public class Notice {

	public static JSONObject query(String username){
		JSONObject items = new JSONObject();
		List<Record> notices = Db.find("SELECT * FROM b_notice WHERE username = ? AND status = 1",username);
		Long newNum = Db.queryLong("SELECT COUNT(*) FROM b_notice WHERE username = ? AND isRead = 0 AND status = 1",username);
		boolean isNew = false;
		if(newNum > 0){
			isNew = true;
		}
		items.put("isNew", isNew);
		if(notices.size()>0){
			System.out.println(JsonKit.toJson(notices));
			items.put("notices", JsonKit.toJson(notices));			
		}
		return items;
	}
	
}
