package me.huqiao.wss.sys.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 内存存储器
 * @author NOVOTS
 * @version Version 1.0
 */
public class MemoryStorage {
	public static String KEY_USER_KEY = "user_key";
	private static MemoryStorage instance = new MemoryStorage();
	private String path;
	public MemoryStorage(){}
	public static MemoryStorage getInstance(){
		return instance;
	}
	private HashMap<String,Serializable> db = null;
	public void init(String path){
		try{
			this.path = path;
			File dataFile = new File(path);
			if(!dataFile.exists()){
				dataFile.createNewFile();
			}
			FileInputStream fi = new FileInputStream(path);
			ObjectInputStream si = new ObjectInputStream(fi);
			db = (HashMap)si.readObject();
			if(db==null){
				db = new HashMap<String,Serializable>();
			}
			si.close();
			fi.close();
		}catch(Exception e){
			db = new HashMap<String,Serializable>();
			//e.printStackTrace();
		}
	}
	
	public void flush() throws Exception{
		FileOutputStream fo = new FileOutputStream(path);
		ObjectOutputStream so = new ObjectOutputStream(fo);
		so.writeObject(db);
		so.flush();
		so.close();
	}
	
	/**
	 * 注册user key
	 * @param key
	 * @param email
	 */
	public synchronized void storeUserKey(String key,String email){
		getUserKeyMap().put(key, email);
		try {
			flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String,String> getUserKeyMap(){
		HashMap<String,String> userKeyMap = (HashMap<String,String>)db.get(KEY_USER_KEY);
		if(userKeyMap==null){
			userKeyMap = new HashMap<String,String>();
			db.put(KEY_USER_KEY, userKeyMap);
		}
		return userKeyMap;
	}
	
	/**
	 * 验证key是否有效
	 * @param key
	 * @return boolean 是否有效
	 */
	public boolean verifyUserkey(String key){
		return getUserKeyMap().get(key)!=null;
	}
	
	
}
