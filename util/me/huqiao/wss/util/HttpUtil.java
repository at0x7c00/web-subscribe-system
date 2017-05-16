package me.huqiao.wss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class HttpUtil {

	static Logger log = Logger.getLogger(HttpUtil.class);
	
	public static void main(String[] args) throws ConnectException {
		
		String address = "http://wms.cdfg.com.cn/wmprd/wms/wmsapp.ctrl";
		String data = "I|LOGINSLOT:0|0|_focus|password=zhangyang1&S|G|P=16B0088D0A99FB734D8E5D82B2D5B981&S|G|E=0A13274CDEFF2D7B61586CFCC9560D4A&S|G|N=Login%20Screen&S|G|W=0&S|G|Z=homeInteractionId&S|G|IN=%E4%B8%BB%E9%A1%B5&S|G|ST=1&S|G|O=0&S|G|OIN=&S|G|loginRequest=25454&S|G|CS=0&S|G|VI=ctrl_click_0&S|G|Y=0&S|G|AE=0&H=loginTrigger&T=1&loginPage=1&loginTriggerClicked=1";
		
		System.out.println(">>>"+postRequest(address,data));
		
	}
	
	/**
	 * 发起POST请求
	 * @param address
	 * @param params
	 * @return
	 */
	public static String postRequest(String address,String data)throws ConnectException{
		data = data.replaceAll("\r", "");
		data = data.replaceAll("\n", "");
		long current = System.currentTimeMillis();
		URL u = null;
		HttpURLConnection con = null;
		try {
			u = new URL(address);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(data);
			osw.flush();
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		//log.info(address+",data=" + data);
		//log.info("time ask ShuZhiYun:"+(System.currentTimeMillis() - current));
		return buffer.toString();
	}
	
	/**
	 * 发起GET请求
	 * @param address
	 * @return
	 */
	public static String getRequest(String address){
		//log.info("request url :" + address);
		URLConnection conn = null;
		StringBuffer content = new StringBuffer();
		try{
			URL url = new URL(address);
			conn = url.openConnection();
			InputStream is = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int i;
			while((i = is.read(buffer))!=-1){
				content.append(new String(buffer,0,i,"UTF8"));
			}
			try{
				is.close();
			}catch(Exception e){
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content.toString();
		
	}
	
}
