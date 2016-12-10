package com.example.entity;

import java.net.CookieManager;
import java.net.CookiePolicy;


import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Server {
 static OkHttpClient client;
 
 static{
	CookieManager cookieManager=new CookieManager();
	cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
	
	client=new OkHttpClient.Builder()
			.cookieJar(new JavaNetCookieJar(cookieManager))
			.build();
 }
 
 public static  OkHttpClient getShareClient(){
	 return client;
	 
 }
 
 public static Request.Builder requestBuilderWithApi(String api){
	 return new Request.Builder()
			 .url("http://172.27.0.40:8080/membercenter/api/"+api);
 }
 
}
