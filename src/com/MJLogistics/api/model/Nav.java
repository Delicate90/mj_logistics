package com.MJLogistics.api.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class Nav {

	public static JSONObject query(){
		JSONObject items = new JSONObject();
		List<Record> index = Db.find("SELECT vn.*,fnf.value AS flag FROM v_nav AS vn LEFT JOIN f_nav_flag AS fnf ON vn.flag = fnf.id WHERE type = 1 ORDER BY sort ASC");
		List<Record> more = Db.find("SELECT vn.*,fnf.value AS flag FROM v_nav AS vn LEFT JOIN f_nav_flag AS fnf ON vn.flag = fnf.id WHERE type = 2 ORDER BY sort ASC");
		items.put("index", JsonKit.toJson(index));
		items.put("more", JsonKit.toJson(more));
		return items;
	}
	
}
