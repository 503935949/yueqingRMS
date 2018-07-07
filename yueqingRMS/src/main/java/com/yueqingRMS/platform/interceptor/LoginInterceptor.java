/*
 * 
 * 
 * 
 */
package com.yueqingRMS.platform.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * Interceptor - 日志记录
 * 
 * 
 * 
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	/** 默认忽略参数 */
	private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[] { "password", "rePassword", "currentPassword" };

	/** antPathMatcher */
	@SuppressWarnings("unused")
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	/** 忽略参数 */
	private String[] ignoreParameters = DEFAULT_IGNORE_PARAMETERS;
	
	/*
	 * 
	 * @Resource(name = "adminServiceImpl") private AdminService adminService;
	 */

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)     
	        throws Exception {   
		
		 //获取请求的URL  
        String url = request.getRequestURI();  
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制  
        if(url.indexOf("login.action")>=0){  
            return true;  
        }  
        //获取Session  
        HttpSession session = request.getSession();  
        String username = (String)session.getAttribute("username");  
          
        if(username != null){  
            return true;  
        }  
        //不符合条件的，跳转到登录界面  
//        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);  
        //request.getRequestDispatcher(url).forward(request, response);  
          
        return true;    
	}     
    public void postHandle(     
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)     
            throws Exception {     
    }     
    public void afterCompletion(     
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)     
            throws Exception {     
    } 

	/**
	 * 设置忽略参数
	 * 
	 * @return 忽略参数
	 */
	public String[] getIgnoreParameters() {
		return ignoreParameters;
	}

	/**
	 * 设置忽略参数
	 * 
	 * @param ignoreParameters
	 *            忽略参数
	 */
	public void setIgnoreParameters(String[] ignoreParameters) {
		this.ignoreParameters = ignoreParameters;
	}

}