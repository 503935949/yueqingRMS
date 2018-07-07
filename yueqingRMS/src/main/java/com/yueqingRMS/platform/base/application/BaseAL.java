package com.yueqingRMS.platform.base.application;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yueqingRMS.platform.base.domain.BaseDao;


/**   
*    
* 项目名称：yueqingRMS   
* 类名称：BaseAL   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 上午10:41:26   
* 修改人：   
* 修改时间：2017年7月18日 上午10:41:26   
* 修改备注：   
* @version    
*    
*/
@Component
public abstract class BaseAL<T> {
	
	@Autowired
	private BaseDao<T> baseDao;
	
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	public abstract int insert(T object) ;
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	public abstract int updateByKey(T object) ;
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	public abstract int deleteByKey(T object) ;
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	public abstract T loadByKey(T object) ;
	
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return List
	 */
	public abstract List<T> findByWhereForList(T object) ;
	
	
	
	

	/**
	 * @return baseDao
	 */
	public  BaseDao<T> getBaseDao() {
		return baseDao;
	}

	/**
	 * @param baseDao
	 */
	public  void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	
	
}
