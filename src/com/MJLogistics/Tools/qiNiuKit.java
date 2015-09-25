package com.MJLogistics.Tools;

import java.io.File;

import com.jfinal.plugin.activerecord.Record;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class qiNiuKit {

	private static String ACCESS_KEY = "26MpzIPO1n19el1997D-RC8Gg-mb8UF5tkN7RRZK";
	private static String SECRET_KEY = "bQX8fdVeAvcTeb08t1vp5VNgQKYEkuE6a7wfsVyo";
	private static String bucket_name = "mjlogistics-2015";
	private static String QINIU_URL = "http://7xn22z.com1.z0.glb.clouddn.com/";

	public static String getUpToken(){
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String upToken = auth.uploadToken(bucket_name);
		return upToken;
	}
	public boolean uploadFile(File file,String key,String upToken){
		Response res;
		try {
			res = new UploadManager().put(file, key, upToken);
			return res.isOK();
		} catch (QiniuException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static String headSave(String fileName){
		return QINIU_URL+fileName;
	}
	public static Record recordSaveToInit(Record file,String fileName,int goodId){
		Record saveFile = file;
		saveFile.set("goodId", goodId);
		saveFile.set("url", QINIU_URL+fileName);
		saveFile.set("status", 0);
		return saveFile;
	}
	public static Record picSaveToInit(Record file,String fileName,int goodId){
		Record saveFile = file;
		saveFile.set("goodId", goodId);
		saveFile.set("image", QINIU_URL+fileName);
		saveFile.set("status", 1);
		return saveFile;
	}
}
