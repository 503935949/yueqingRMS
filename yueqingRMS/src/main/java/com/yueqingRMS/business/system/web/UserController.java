package com.yueqingRMS.business.system.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：EchartsController   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月10日 上午11:16:55   
* 修改人：   
* 修改时间：2017年7月10日 上午11:16:55   
* 修改备注：   
* @version    
*    
*/
@Controller
@RequestMapping("/business/system/user")
public class UserController  {
	
	
//	/** 
//	* @Title: interna 
//	* @Description: 中英文切换
//	* @param @param language
//	* @param @return    设定文件 
//	* @return String    返回类型 
//	* @throws 
//	*/
//	@RequestMapping(value = "/interna", method = RequestMethod.POST)
//	@ResponseBody
//	protected  String interna(@RequestParam("language") String language,ModelMap model,HttpServletRequest request,HttpServletResponse response) {
//		
////		String language = request.getParameter("language");
//		Locale locale = null;
//		if ("Chinese".equals(language)) {
//			locale = Locale.CHINA;
//		} else {
//			locale = Locale.US;
//		}
//		Locale newLocale = null;
//		newLocale = (Locale)request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
//		if(locale != null && locale.equals(newLocale)) {
//			return "0";
//		} else {
//			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
//			return "1";
//		}
//		
////		try {
//			
////			LoginUserEntity currentUser = WebUtils.getLoginInfo();
////			if(Util.isNotNull(currentUser.getUser())){
////			if("Chinese".equals(language)){
////				UserEntity o=getService().find(currentUser.getUser().getId());
////				o.setInterna(language);
////				getService().update(o);
////				WebUtils.getLoginInfo().setUser(o);
////		return "1";
////			}
////			return "0";
////		} catch (Exception e) {
////			return "0";
////		}
//	}
	
	/**
	 * @return DEMO_LIST 界面
	 */
	@RequestMapping("/findUser_List_IndexPage")
	public String findUser_List_IndexPage(){
		return "business/system/user/UserIndex";	
	}
	
	
	
}
