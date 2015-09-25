package com.MJLogistics.Tools;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;

public class SmsKit {


	private static String serverIP, accountSid, accountToken, appId, serverPort;

	private static void getConfig() {
		serverIP = "sandboxapp.cloopen.com";
		accountSid = "aaf98f894e91da24014e94f2eeaa0200";
		accountToken = "f7fc5dad46e54c36918dee4a70618545";
		appId = "8a48b5514e8a7522014e94f40b1e0c8c";
		serverPort = "8883";
	}
	public static String onlyInt(int length){
		
		double Dresult = Math.random();
		String result = String.valueOf(Dresult).substring(2, length+2);
		
		return result;
	}
	public static boolean sendToOne(String phoneNum,String appNum,String[] keys) {
		boolean rs = send(phoneNum, appNum, keys);
		return rs;
	}

	public static boolean sendToMany(Set<String> phoneNumSet,String appNum,String[] keys) {
		String phoneNums = "";
		if(phoneNumSet.size()>0){
			for(String num : phoneNumSet){
				phoneNums += num;
				phoneNums += ",";
			}
			if(phoneNums.substring(phoneNums.length()-2).equals(","))phoneNums = phoneNums.substring(0, phoneNums.length()-2);
		}
		boolean rs = send(phoneNums, appNum, keys);
		return rs;
	}

	private static boolean send(String phoneNum,String appNum,String[] keys) {
		getConfig();
		HashMap result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(serverIP, serverPort);
		restAPI.setAccount(accountSid, accountToken);
		restAPI.setAppId(appId);
		result = restAPI.sendTemplateSMS(phoneNum, appNum, keys);
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
			return true;
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
			return false;
		}
	}
	
}
