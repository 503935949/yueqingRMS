///*
// * 
// * 
// * 
// */
//package com.yueqingRMS.interceptor;
//
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.cebcm.system.entity.UserEntity;
//import com.cebcm.util.Util;
//import com.cebcm.util.WebUtils;
//
///** 
//* @ClassName: Internationalization 
//* @Description: 国际化拦截
//* @author wjl 北京高阳明天信息技术有限公司   
//* @date 2016年7月31日 下午1:27:21 
//*  
//*/
//public class Internationalization extends HandlerInterceptorAdapter {
//	
//	@Autowired
//	private LocaleResolver localeResolver;
//
//	/* (非 Javadoc) 
//	* <p>Title: postHandle</p> 
//	* <p>Description: </p> 
//	* @param request
//	* @param response
//	* @param handler
//	* @param modelAndView
//	* @throws Exception 
//	* @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView) 
//	*/
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		try {
//			Locale l = Locale.getDefault();
//			if(Util.isNotNull(WebUtils.getLoginInfo())){
//				UserEntity user = WebUtils.getLoginInfo().getUser();
//				if (Util.isNotNull(user) && Util.isNotNull(user.getInterna())) {
//					String interna = user.getInterna();
//					if ("Chinese".equals(interna)) {
//						l = new Locale("zh", "CN");// 中文
//					} else if ("English".equals(interna)) {
//						l = new Locale("en", "US"); // 英文
//					} else {
//						l = new Locale("zh", "CN");
//					}
//				}
//				localeResolver.setLocale(request, response, l);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}