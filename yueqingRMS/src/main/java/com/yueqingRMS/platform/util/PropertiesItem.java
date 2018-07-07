package com.yueqingRMS.platform.util;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：PropertiesItem   
* 类描述：  以List读取配置文件返回类型  id:对应配置文件中的id值  name:对应配置文件中的name值
* 创建人：林曌   
* 创建时间：2017年8月10日 下午1:40:10   
* 修改人：   
* 修改时间：2017年8月10日 下午1:40:10   
* 修改备注：   
* @version    
*    
*/
public class PropertiesItem {
	
	/** 配置文件中的id值 */
	private String id; 
	
	/** 配置文件中的name值 */
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
