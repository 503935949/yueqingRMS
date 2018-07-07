package com.yueqingRMS.business.knowledgebase.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqingRMS.business.knowledgebase.demo.domain.DemoDomain;
import com.yueqingRMS.business.knowledgebase.demo.service.IDemoService;
import com.yueqingRMS.platform.base.web.BaseController;
import com.yueqingRMS.platform.tools.page.Page;


/**   
*    
* 项目名称：yueqingRMS   
* 类名称：DemoController   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 下午2:16:15   
* 修改人：   
* 修改时间：2017年7月18日 下午2:16:15   
* 修改备注：   
* @version    
*    
*/
@Controller
@RequestMapping("/business/knowledgebase/demo")
public class DemoController extends BaseController{
	
	@Autowired
	private IDemoService  demoService;
	
	
	/**
	 * @return DEMO_LIST 界面
	 */
	@RequestMapping("/findDemo_List_IndexPage")
	public String findDemo_List_IndexPage(){
//		DemoDomain demoDomain = new DemoDomain();
//		demoDomain.setPageSize(10);
//		demoDomain.setPage(2);
//		demoService.findByWhereForPage(demoDomain);
		return "business/knowledgebase/demo/DemoIndex";	
	}
	
	/**
	 * @return findByWhereForPage 界面
	 */
	@RequestMapping("/findByWhereForPage")
	@ResponseBody
	public Page findByWhereForPage(DemoDomain demoDomain,
			HttpServletRequest request,HttpServletResponse response){
		String page = (String) request.getAttribute("page");
		String pageSize = (String) request.getAttribute("pageSize");
		
		return demoService.findByWhereForPage(demoDomain);
	}
	
//	李耀池...
	
	
}
