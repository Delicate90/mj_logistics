package com.MJLogistics.api.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/** 
* @author Delicate
* @version 创建时间：2015年9月23日 下午10:36:07 
* @Explain User逻辑实现
*/
public class User extends Model<User>{

	public boolean init(String username){
		
		Record user = Db.findFirst("SELECT * FROM b_users WHERE username = ? AND ");
		
		return false;
	}
	
	public boolean save(String username,String head,String role,String name,String sex,String mobile,String area,String address){
		
//		String username = 
		
		
		return false;
	}
	
}
