package com.yueqingRMS.business.common.web;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.yueqingRMS.business.common.domain.InternaDomain;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：CommonController   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月7日 下午12:48:54   
* 修改人：   
* 修改时间：2017年7月7日 下午12:48:54   
* 修改备注：   
* @version    
*    
*/
@Controller
@RequestMapping("/business/common")
public class CommonController {
	
	
	
	@RequestMapping("/main")
	public String findMainPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		Locale locale = null;
//		locale = Locale.CHINA;
		Locale sessionlocale = (Locale)request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		if(sessionlocale != null && sessionlocale !=locale)  {
			locale = sessionlocale;
		}else {
			locale = Locale.getDefault();
		}
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		return "business/common/main/main";	
	}
	
	
	@RequestMapping("/findHomePage")
	public String findHomePage(){
		return "business/common/home/home";	
	}
	
	/** 
	* @Title: interna 
	* @Description: 中英文切换
	* @param @param language
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/interna", method = RequestMethod.POST)
	@ResponseBody
	protected  InternaDomain interna(@ModelAttribute("internaDomain") InternaDomain internaDomain,ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		
//		String language = request.getParameter("language");
		String language = internaDomain.getLanguage();
		Locale locale = null;
		//转换至国际标准名称
		switch(language)
		{
			case "Chinese" : locale = Locale.CHINA; break;
			case "English" : locale = Locale.US; break;
		}
		//传回标准名称，便于前台 jq.i18n 取值
		internaDomain.setLocale(locale); 
//		if ("Chinese".equals(language)) {
//			locale = Locale.CHINA;
//		} else {
//			locale = Locale.US;
//		}
		//判断是否真的改变语言
		Locale newLocale = null;
		newLocale = (Locale)request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		if(locale != null && locale.equals(newLocale)) {
			internaDomain.setIsReload("false");
		} else {
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
			internaDomain.setIsReload("true");
		}
		
		return internaDomain;
		
//		try {
			
//			LoginUserEntity currentUser = WebUtils.getLoginInfo();
//			if(Util.isNotNull(currentUser.getUser())){
//			if("Chinese".equals(language)){
//				UserEntity o=getService().find(currentUser.getUser().getId());
//				o.setInterna(language);
//				getService().update(o);
//				WebUtils.getLoginInfo().setUser(o);
//		return "1";
//			}
//			return "0";
//		} catch (Exception e) {
//			return "0";
//		}
	}
	
}
