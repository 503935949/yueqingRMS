package com.yueqingRMS.business.knowledgebase.text;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqingRMS.business.knowledgebase.demo.domain.DemoDomain;
import com.yueqingRMS.business.knowledgebase.demo.service.IDemoService;
import com.yueqingRMS.platform.base.web.BaseController;


/**   
*    
* 项目名称：yueqingRMS   
* 类名称：TextController   
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
@RequestMapping("/business/knowledgebase/text")
public class TextController extends BaseController{
	
	@Autowired
	private IDemoService  demoService;
	
	
	/**
	 * @return DEMO_LIST 界面
	 * @throws Exception 
	 */
	@RequestMapping("/text_List")
	@ResponseBody
	public List<DemoDomain> text_List(DemoDomain DemoDomain) throws Exception{
//		return demoService.findByWhereForList(new DemoDomain());
//		return demoService.findByWhereForPage(new DemoDomain());
//		return "business/knowledgebase/demo/DemoIndex";	
		throw new Exception("0000");
	}
	
//	李耀池...
	
	
}
