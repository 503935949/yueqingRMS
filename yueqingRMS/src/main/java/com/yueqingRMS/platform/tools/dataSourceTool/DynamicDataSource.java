package com.yueqingRMS.platform.tools.dataSourceTool;

import java.util.logging.Logger;  
  
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  
  
/**   
*    
* 项目名称：yueqingRMS   
* 类名称：DynamicDataSource   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月22日 下午5:05:01   
* 修改人：   
* 修改时间：2017年8月22日 下午5:05:01   
* 修改备注：   
* @version    
*    
*/
public class DynamicDataSource extends AbstractRoutingDataSource {  
  
    /**
     * 
     * @see org.springframework.jdbc.datasource.AbstractDataSource#getParentLogger()
     */
    @Override  
     public Logger getParentLogger() {  
            return null;  
     }  
  
    /**
     * 
     * @see org.springframework.jdbc.datasource.AbstractDataSource#getParentLogger()
     */
    @Override  
     protected Object determineCurrentLookupKey() {  
            return DataSourceContextHolder.getDbType();  
     }  
  
}  