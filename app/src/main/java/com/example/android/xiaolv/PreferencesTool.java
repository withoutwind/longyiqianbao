package com.example.android.xiaolv;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

/**
 * @author zjc
 *
 */
public class PreferencesTool {

	/**
	 * 使用SharedPreferences保存用户登录状态
	 * @param context
	 * @param isSanfang
	 *
	 */
	public static void saveLogin(Context context,boolean isSanfang,String openid,String type,String token){
		//获取SharedPreferences对象
		SharedPreferences sharedPredisan=context.getSharedPreferences("configs", Context.MODE_PRIVATE);
		//获取Editor对象
		Editor editor=sharedPredisan.edit();
		//设置参数

		editor.putString("openid", openid);
		editor.putString("type", type);
		editor.putString("token", token);
		//提交
		editor.commit();
	}
	/**
	 * 获取用户登录方式
	 * @param context
	 * @return
	 */

	/**
	 * 获取用户登录方式
	 * @param context
	 * @return
	 */
	public static String getopenid(Context context){
		SharedPreferences sharedPre=context.getSharedPreferences("configs", Context.MODE_PRIVATE);
		String openid=sharedPre.getString("openid","");
		if ("".equals(openid)) {
			return "";
		}
		return openid;
	}
	/**
	 * 获取用户登录方式
	 * @param context
	 * @return
	 */
	public static String gettoken(Context context){
		SharedPreferences sharedPre=context.getSharedPreferences("configs", Context.MODE_PRIVATE);
		String token=sharedPre.getString("token","");
		if ("".equals(token)) {
			return "";
		}
		return token;
	}
	/**
	 * 获取用户登录方式
	 * @param context
	 * @return
	 */
	public static String gettype(Context context){
		SharedPreferences sharedPre=context.getSharedPreferences("configs", Context.MODE_PRIVATE);
		String type=sharedPre.getString("type","");
		if ("".equals(type)) {
			return "";
		}
		return type;
	}

	/**
	 * 使用SharedPreferences保存用户登录信息
	 * @param context
	 * @param username
	 * @param password
	 */
	public static void saveLoginInfo(Context context,String username,String password){
		//获取SharedPreferences对象
		SharedPreferences sharedPre=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		//获取Editor对象
		Editor editor=sharedPre.edit();
		//设置参数
		editor.putString("username", username);
		editor.putString("password", toBase2(password));
		//提交
		editor.apply();
	}
	/**
	 * 获取用户账号
	 * @param context
	 * @return
	 */
	public static String getUserName(Context context){
		SharedPreferences sharedPre=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String username=sharedPre.getString("username", "");
		if (username==null) {
			return "";
		}
		return username;
	}
	/**
	 * 获取用户密码
	 * @param context
	 * @return
	 */
	public static String getPassWord(Context context){
		SharedPreferences sharedPre=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String password=toString2(sharedPre.getString("password", ""));
		if (password==null) {
			return "";
		}
		return password;
	}

	/**
	 * 通过key来获取字符串
	 * @param context
	 * @return
	 */
	public static String getValue(Context context,String key){
		SharedPreferences sharedPre=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String value=sharedPre.getString(key, "");
		if (value==null) {
			return "";
		}
		return value;
	}
	/**
	 * 通过key来获取字符串
	 * @param context
	 * @return
	 */
	public static void saveValue(Context context,String key,String value){
		//获取SharedPreferences对象
		SharedPreferences sharedPre=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		//获取Editor对象
		Editor editor=sharedPre.edit();
		//设置参数
		editor.putString(key, value);
		//提交
		editor.commit();
	}
	public static String toBase(String str){
		// 在这里使用的是encode方式，返回的是byte类型加密数据，可使用new String转为String类型
		String strBase64 = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));

		return strBase64;
	}
	public static String toString(String str){
		// 对base64加密后的数据进行解密
		String enToStr = new String(Base64.decode(str.getBytes(), Base64.DEFAULT));

		return enToStr;
	}
	public static String toBase2(String str){
		// 双层加密
		String strBase64 = toBase(toBase(str));
		return strBase64;
	}
	public static String toString2(String str){
		// 双层解密
		String enToStr = toString(toString(str));
		return enToStr;
	}
}
