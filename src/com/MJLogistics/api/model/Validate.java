package com.MJLogistics.api.model;

import java.sql.Timestamp;

import com.MJLogistics.Tools.SmsKit;
import com.MJLogistics.Tools.ValidateKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class Validate {

	public static boolean send(String mobile){
		if(!mobile.equals("")&&ValidateKit.mobile(mobile)){
			Record user = Db.findFirst("SELECT * FROM b_user WHERE mobile = ?");
			String rdString = SmsKit.onlyInt(4);
			String[] rd = {rdString};
			Timestamp now = new Timestamp(System.currentTimeMillis());
			if(user == null){
				user = new Record();
				user.set("username", mobile).set("mobile", mobile).set("status", 0);
				user.set("validetaNum", rdString).set("validateTime", now);
				boolean rs = SmsKit.sendToOne(mobile, "1", rd);
				if(rs){
					Db.save("b_user", user);		
					return true;
				}else{
					return false;
				}
			}else{
				user.set("validetaNum", rdString).set("validateTime", now);
				boolean rs = SmsKit.sendToOne(mobile, "1", rd);
				if(rs){
					Db.update("b_user", user);
					return true;
				}else{
					return false;
				}
			}
		}else{	
			return false;
		}		
	}
}
