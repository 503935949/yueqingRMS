package com.yueqingRMS.platform.base.service;

import java.util.List;

import com.yueqingRMS.platform.tools.page.Page;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：IBaseService   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 上午10:41:46   
* 修改人：   
* 修改时间：2017年7月18日 上午10:41:46   
* 修改备注：   
* @version    
*    
*/
public abstract interface IBaseService<T> {

	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int insert(T object);
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int updateByKey(T object);
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int deleteByKey(T object);
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	public  T loadByKey(T object);
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	public  Page findByWhereForPage(T object);
	
	/**
	 * 查询 
	 * @param object 对象，传参用
	 * @return Page
	 */
	public  List<T> findByWhereForList(T object);


	
}
