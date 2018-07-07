//package com.yueqingRMS.util;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.http.Header;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.log4j.Logger;
//
///**   
//*    
//* 项目名称：yueqingRMS   
//* 类名称：HttpClientUtils   
//* 类描述：   
//* 创建人：林曌   
//* 创建时间：2017年8月10日 下午3:18:43   
//* 修改人：   
//* 修改时间：2017年8月10日 下午3:18:43   
//* 修改备注：   
//* @version    
//*    
//*/
//public class HttpClientUtils {
//
//	private static Logger log = Logger.getLogger(HttpClientUtils.class);
//
//	private static String cookieStr = "";
//	private static CookieStore cookieStore;
//
//	public static String post(String url, Map<String, String> params) {
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		httpclient.setCookieStore(cookieStore);
//		String body = null;
//
//		log.info("create httppost:" + url);
//
//		HttpPost post = postForm(url, params);
//		post.setHeader("Cookie", cookieStr);
//
//		body = invoke(httpclient, post);
//
//		Header[] headers = post.getAllHeaders();
//		for (Header header : headers) {
//			System.out.println(header.getName() + ":" + header.getValue());
//		}
//
//		cookieStore = httpclient.getCookieStore();
//		List<Cookie> cookieList = cookieStore.getCookies();
//		StringBuffer tmpcookies = new StringBuffer();
//		for (Cookie c : cookieList) {
//			cookieStr = tmpcookies.append(c.toString() + ";").toString();
//		}
//
//		httpclient.getConnectionManager().shutdown();
//
//		return body;
//	}
//
//	public static String get(String url) {
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		String body = null;
//
//		log.info("create httppost:" + url);
//		HttpGet get = new HttpGet(url);
//		body = invoke(httpclient, get);
//
//		httpclient.getConnectionManager().shutdown();
//
//		return body;
//	}
//
//	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {
//
//		HttpResponse response = sendRequest(httpclient, httpost);
//		String body = paseResponse(response);
//
//		return body;
//	}
//
//	private static String paseResponse(HttpResponse response) {
//		String body = "";
//		try {
//			log.info("get response from http server..");
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				InputStream instreams = entity.getContent();
//				String str = convertStreamToString(instreams);
//				body = str;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return body;
//	}
//
//	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
//		log.info("execute post...");
//		HttpResponse response = null;
//
//		try {
//			response = httpclient.execute(httpost);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return response;
//	}
//
//	private static HttpPost postForm(String url, Map<String, String> params) {
//
//		HttpPost httpost = new HttpPost(url);
//		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//
//		Set<String> keySet = params.keySet();
//		for (String key : keySet) {
//			nvps.add(new BasicNameValuePair(key, params.get(key)));
//		}
//
//		try {
//			log.info("set utf-8 form entity to httppost");
//			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return httpost;
//	}
//
//	private static String convertStreamToString(InputStream is) {
//		StringBuilder sb1 = new StringBuilder();
//		byte[] bytes = new byte[4096];
//		int size = 0;
//
//		try {
//			while ((size = is.read(bytes)) > 0) {
//				String str = new String(bytes, 0, size, "UTF-8");
//				sb1.append(str);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return sb1.toString();
//	}
//}
