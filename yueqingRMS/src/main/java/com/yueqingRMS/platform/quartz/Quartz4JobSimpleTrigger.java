package com.yueqingRMS.platform.quartz;

import com.yueqingRMS.platform.util.DateUtil;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：Quartz4JobSimpleTrigger   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月21日 下午3:57:53   
* 修改人：   
* 修改时间：2017年8月21日 下午3:57:53   
* 修改备注：   
* @version    
*    
*/
public class Quartz4JobSimpleTrigger {
	
	public void doJob() {  
		System.out.println("SimpleTrigger调度进行中..."+DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));  
	}  
	
}
