package com.MJLogistics.api.model;

import java.io.File;

import com.MJLogistics.Tools.qiNiuKit;
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
	public static JSONObject headerEdit(String username,File header){
		JSONObject items = new JSONObject();
		boolean rs = false;
		if(!username.equals("")){
			Record user = Db.findFirst("SELECT * FROM b_user WHERE username = ? AND status = 1",username);
			if(user != null){
				System.out.println(header.getName());
				String fileName = "head_"+username+String.valueOf(System.currentTimeMillis())+"."+header.getName().split("\\.")[1];
				String QNToken = qiNiuKit.getUpToken();
				boolean qnRs = new qiNiuKit().uploadFile(header, fileName, QNToken);
				if(qnRs){
					user.set("head", qiNiuKit.headSave(fileName));
					rs = Db.update("b_user", user);
					if(rs){
						user = Db.findFirst("SELECT bu.*,fur.content AS roleValue,fus.content AS sexValue FROM b_user AS bu LEFT JOIN f_user_role AS fur ON bu.role = fur.id LEFT JOIN f_user_sex AS fus ON bu.sex = fus.id WHERE bu.username = ? AND bu.status = 1",username);
						items.put("user", user.toJson());
					}
				}
			}
		}
		items.put("rs", rs);
		return items;
	}
	public static JSONObject edit(String username,int role,String name,String nickName,int sex,String mobile,String area,String address){
		JSONObject items = new JSONObject();
		boolean rs = false;
		if(!username.equals("")){
			Record user = Db.findFirst("SELECT * FROM b_user WHERE username = ? AND status = 1",username);
			if(user != null){
				if(role != 0){
					user.set("role", role);
				}
				if(!name.equals("")){
					user.set("name", name);
				}
				if(!nickName.equals("")){
					user.set("nickName", nickName);
				}
				if(!address.equals("")){
					user.set("address", address);
				}
				if(!area.equals("")){
					String[] areas = area.split("\\|");
					if(areas.length>1){
						String[] location = areas[1].split("\\-");
						user.set("lon", location[0]);
						user.set("lat", location[1]);						
					}
					String[] province_city_area = areas[0].split("\\-");
					user.set("province", province_city_area[0]);
					System.out.println(province_city_area[0]);
					System.out.println(province_city_area[1]);
					user.set("city", province_city_area[1]);
					if(province_city_area.length>2){
						user.set("area", province_city_area[2]);
					}
				}
				if(sex != 0){
					user.set("sex", sex);
				}
				rs = Db.update("b_user", user);
				if(rs){
					user = Db.findFirst("SELECT bu.*,fur.content AS roleValue,fus.content AS sexValue FROM b_user AS bu LEFT JOIN f_user_role AS fur ON bu.role = fur.id LEFT JOIN f_user_sex AS fus ON bu.sex = fus.id WHERE bu.username = ? AND bu.status = 1",username);
					items.put("user", user.toJson());
				}
			}			
		}
		items.put("rs", rs);
		return items;
	}
	public static JSONObject login(String username,String validateNum,String deviceId){
		JSONObject items = new JSONObject();
		boolean rs = false;
		if(!username.equals("")&&!validateNum.equals("")&&!deviceId.equals("")){
			//status 0=初始化 1=正常
			Record user = Db.findFirst("SELECT bu.*,fur.content AS roleValue,fus.content AS sexValue FROM b_user AS bu LEFT JOIN f_user_role AS fur ON bu.role = fur.id LEFT JOIN f_user_sex AS fus ON bu.sex = fus.id WHERE bu.username = ? AND bu.validateNum = ? AND bu.status < 2",username,validateNum);
			Long nowTime = System.currentTimeMillis();
			if(user != null ){
				if((nowTime-user.getTimestamp("validateTime").getTime())<300000){
					Db.update("UPDATE b_user SET validateNum = ? , status = 1 WHERE username = ?","----",user.getStr("username"));
					items.put("user", user.toJson());
					String agentToken = Token.add(username, deviceId);
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
