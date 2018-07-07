package com.yueqingRMS.platform.quartz;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yueqingRMS.platform.util.DateUtil;


/**   
*    
* 项目名称：yueqingRMS   
* 类名称：TaskJob   
* 类描述：   springTask 定时任务实现
* 创建人：林曌   
* 创建时间：2017年8月21日 下午4:18:26   
* 修改人：   
* 修改时间：2017年8月21日 下午4:18:26   
* 修改备注：   
* @version    
*    
*/
@Lazy(false) 
@Component("taskJob")
public class TaskJob {  
      
	@Scheduled(cron = "0/100 * * * * ?") 
    public void doJob() {  
    	System.out.println("springTask_-调度进行中..."+DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));  
    }  
}  
