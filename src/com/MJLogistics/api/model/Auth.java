package com.MJLogistics.api.model;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/** 
* @author Delicate
* @version 创建时间：2015年9月28日 下午9:49:00 
* @Explain 
*/
public class Auth {

	public static JSONObject query(String username){
		JSONObject items = new JSONObject();
		Record auth = Db.findFirst("SELECT * FROM b_auth WHERE username = ?",username);
		items.put("auth", auth.toJson());
		return items;
	}
	
	public static boolean humanEdit(String username,String name,String identification,File IDImage){
		boolean rs = false;
		Record auth = Db.findFirst("SELECT * FROM b_auth WHERE username = ?",username);
		if(!name.equals("")&&!identification.equals("")&&IDImage != null){
			auth.set("name", name).set("identification", identification);
			
		}
		return rs;
	}
	public static boolean trucksEdit(String username,String licensePlate,File driversLicenceImage,File driveingLicenceImage,File trucksImage){
		boolean rs = false;
		Record auth = Db.findFirst("SELECT * FROM b_auth WHERE username = ?",username);
		
		return rs;
	}
	public static boolean goodsEdit(String username,File businessLicenceImage,File companyImage){
		boolean rs = false;
		Record auth = Db.findFirst("SELECT * FROM b_auth WHERE username = ?",username);
		
		return rs;
	}
}
