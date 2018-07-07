package com.yueqingRMS.platform.tools.dataSourceTool;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：DataSourceContextHolder   
* 类描述：   数据库切换的工具类：DataSourceContextHolder，用来切换数据库
* 创建人：林曌   
* 创建时间：2017年8月22日 下午12:42:04   
* 修改人：   
* 修改时间：2017年8月22日 下午12:42:04   
* 修改备注：   
* @version    
*    
*/
public class DataSourceContextHolder { 
	
    /**
     * 获取ThreadLocal
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
 
    /**
     * @param dbType
     */
    public static void setDbType(String dbType) {  
           contextHolder.set(dbType);  
    }  
 
    /**
     * @return
     */
    public static String getDbType() {  
           return ((String) contextHolder.get());  
    }  
 
    /**
     * 
     */
    public static void clearDbType() {  
           contextHolder.remove();  
    }  
}  