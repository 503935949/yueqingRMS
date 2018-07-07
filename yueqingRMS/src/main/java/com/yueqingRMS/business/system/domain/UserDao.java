package com.yueqingRMS.business.system.domain;

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
public  interface UserDao extends BaseDao<UserDomain>{
    
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int insert(UserDomain object);
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int updateByKey(UserDomain object);
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int deleteByKey(UserDomain object);
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	public  UserDomain loadByKey(UserDomain object);
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	public  List<UserDomain> findByWhereForPage(UserDomain object);
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return List
	 */
	public  List<UserDomain> findByWhereForList(UserDomain object);
}
