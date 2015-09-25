package com.MJLogistics.api.model;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:36:07 
* @Explain User逻辑实现
*/
public class User extends Model<User>{

	private String Salting = "developByDelicate";
	public Record init(String username){
		
		Record user = Db.findFirst("SELECT * FROM b_users WHERE username = ? AND status = 0");
		if(user == null){
			//user初始化
			user = new Record();
			user.set("username", username).set("mobile", username).set("status", 0);
			Db.save("b_user", user);
		}
		return user;
	}
	
	public boolean save(String username,String head,String role,String name,String sex,String mobile,String area,String address){
	
		return false;
	}
	public static JSONObject login(String username,String validateNum,String deviceId){
		JSONObject items = new JSONObject();
		boolean rs = false;
		if(!username.equals("")&&!validateNum.equals("")&&!deviceId.equals("")){
			//status 0=初始化 1=正常
			Record user = Db.findFirst("SELECT bu.*,fur.content AS roleValue,fus.content AS sexValue FROM b_user AS bu LEFT JOIN f_user_role AS fur ON bu.role = fur.id LEFT JOIN f_user_sex AS fus ON bu.sex = fus.id WHERE bu.username = ? AND bu.validateNum = ? AND bu.status < 2",username,validateNum);
			Long nowTime = System.currentTimeMillis();
			if(user != null ){
				if((nowTime-user.getTimestamp("validateTime").getTime())>=300000){
					Db.update("UPDATE b_user SET validateNum = ? WHERE username = ?","----",user.getStr("username"));
					items.put("user", user.toJson());
					String agentToken = Token.add(username, deviceId);
					System.out.println(agentToken);
					items.put("agentToken", agentToken);
					if(user.getInt("role") != 0){				
						Record auth = Db.findFirst("SELECT * FROM b_auth WHERE username = ? AND role = ?",username,user.getInt("role"));
						if(auth != null){
							items.put("auth", auth.toJson());
						}
					}
					rs = true;
				}
			}			
		}
		items.put("rs", rs);
		return items;
	}
}
