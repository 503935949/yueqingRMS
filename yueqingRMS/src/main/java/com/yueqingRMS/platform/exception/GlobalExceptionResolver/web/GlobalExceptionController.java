package com.yueqingRMS.platform.exception.GlobalExceptionResolver.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GlobalExceptionController {

	/** 
	 * 请求异常 
	 * @return 
	 * @throws Exception 
	 * String 
	 */  
	@RequestMapping(value = "/error_404", produces = "text/html;charset=UTF-8")  
	@ResponseBody  
	public String error_404() throws Exception {   
	     return "{\"msg\":\"找不到页面\",\"code\":\"1000001\"}";  
	}  
	/** 
	 * 服务器异常 
	 * @return 
	 * String 
	 */  
	@RequestMapping(value ="/error_500", produces = "text/html;charset=UTF-8")  
	@ResponseBody 
	public String error_500() {     
//		<pre name="code" class="java">               
	return "{\"msg\":\"服务器处理失败\",\"code\":\"1000002\"}";  
	}  
}
