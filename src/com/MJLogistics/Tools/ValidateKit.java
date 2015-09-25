package com.MJLogistics.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateKit {

	public static boolean mobile(String mobile){
		
		Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
//        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        p = Pattern.compile("^(((13[0-9]{1})|145|147|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1})|170|177|178|176)+\\d{8})$"); // 验证手机号  
        m = p.matcher(mobile);  
        b = m.matches();   
        return b;  
	}
	
}
