package com.yueqingRMS.platform.exception.GlobalExceptionResolver;

import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
import java.lang.reflect.UndeclaredThrowableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.yueqingRMS.platform.base.web.BaseController;
import com.yueqingRMS.platform.util.DateUtil;
import com.yueqingRMS.platform.util.StringUtils;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：GlobalExceptionResolver   
* 类描述：   统一异常处理
* 创建人：林曌   
* 创建时间：2017年8月22日 下午3:35:46   
* 修改人：   
* 修改时间：2017年8月22日 下午3:35:46   
* 修改备注：   
* @version    
*    
*/
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	
	Logger logger =  Logger.getLogger(BaseController.class);
	
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		String msg = null;
		if(ex.getCause() instanceof RuntimeException){  
            msg=ex.getCause().getLocalizedMessage();  
        } else if(ex instanceof RuntimeException){  
            msg= ex.getCause().getMessage();  
        } else if(ex instanceof UndeclaredThrowableException){  
            msg=((UndeclaredThrowableException) ex).getUndeclaredThrowable().getMessage();  
        } else {
            msg  = ex.getMessage();
        }
		logger.error(msg, ex);
	    logger.info("************* ------ 异常信息已记录(" + DateUtil.getNow("yyyy-MM-dd HH:mm:ss")+ ") ------- ***********");
	    ModelAndView mv = new ModelAndView();             
        /*  使用response返回    */  
//        response.setStatus(HttpStatus.OK.value()); //设置状态码  
        response.setStatus(1000001);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
        response.setCharacterEncoding("UTF-8"); //避免乱码  
        response.setHeader("Cache-Control", "no-cache, must-revalidate");  
        try {  
            response.getWriter().write("{\"success\":false,\"msg\":\"" + StringUtils.ltrim(msg, ":") + "\"}");  
        } catch (IOException e) {  
        	logger.error("与客户端通讯异常:"+ e.getMessage(), e);  
        }  
        
         

        logger.debug("异常:" + msg, ex);  
        return mv;  
	}
	
	
//	 @Override
//	 public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) {    
//       //需要定义返回的异常码，或是返回约定的数据
//		 logger.error(ex.getMessage(), ex);
//		 logger.info("************* ------ 异常信息已记录（" + DateUtil.getNow("yyyy-MM-dd HH:mm:ss")+ "） ------- ***********");
//		    
//		 ModelAndView mv = new ModelAndView();  
//         /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */  
//         FastJsonJsonView view = new FastJsonJsonView();  
//         Map<String, Object> attributes = new HashMap<String, Object>();  
//         attributes.put("code", "1000001");  
//         attributes.put("msg", ex.getMessage());  
//         view.setAttributesMap(attributes);  
//         mv.setView(view);   
//         logger.debug("异常:" + ex.getMessage(), ex);  
//         return mv;  
//	 }  

	
}
