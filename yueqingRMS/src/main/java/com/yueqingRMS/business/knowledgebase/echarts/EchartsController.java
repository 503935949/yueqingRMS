package com.yueqingRMS.business.knowledgebase.echarts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqingRMS.platform.base.web.BaseController;





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
@RequestMapping("/business/echarts")
public class EchartsController  extends BaseController {
	
	
	
	@RequestMapping("/findNps_main_demoPage")
	public String findNps_main_demoPage(){
		return "business/knowledgebase/echarts/npm_demos/nps_main_demo/demo0";	
	}
	
	@RequestMapping("/findNps_main_demo_4one")
	public String findNps_main_demo_4one(){
		return "business/knowledgebase/echarts/npm_demos/nps_one_demo/demo1";	
	}
	
	@RequestMapping("/find_chinamaps")
	public String find_chinamaps(){
		return "business/knowledgebase/echarts/maps/chinaMap";	
	}
	
	@RequestMapping("/find_bar_demo1")
	public String find_bar_demo1(){
		return "business/knowledgebase/echarts/bar/BAR_DEMO";	
	}
	
	@RequestMapping("/find_bar_3D_BAR_DEMO")
	public String find_bar_3D_BAR_DEMO(){
		return "business/knowledgebase/echarts/bar/3D_BAR_DEMO";	
	}
	
//	@RequestMapping("/findHomePage")
//	public String findHomePage(){
//		return "business/common/home/home";	
//	}
	
}
