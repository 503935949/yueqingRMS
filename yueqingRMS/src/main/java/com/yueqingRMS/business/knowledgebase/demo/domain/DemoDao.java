package com.yueqingRMS.business.knowledgebase.demo.domain;

import java.util.List;

import com.yueqingRMS.platform.base.domain.BaseDao;


/**   
*    
* 项目名称：yueqingRMS   
* 类名称：DemoDao   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 上午10:41:40   
* 修改人：   
* 修改时间：2017年7月18日 上午10:41:40   
* 修改备注：   
* @version    
*    
*/
public  interface DemoDao extends BaseDao<DemoDomain>{
    
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int insert(DemoDomain object);
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int updateByKey(DemoDomain object);
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int deleteByKey(DemoDomain object);
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	public  DemoDomain loadByKey(DemoDomain object);
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	public  List<DemoDomain> findByWhereForPage(DemoDomain object);
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return List
	 */
	public  List<DemoDomain> findByWhereForList(DemoDomain object);
}
