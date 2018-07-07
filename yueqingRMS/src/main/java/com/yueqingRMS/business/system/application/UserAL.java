package com.yueqingRMS.business.system.application;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yueqingRMS.platform.base.application.BaseAL;
import com.yueqingRMS.platform.tools.page.Page;
import com.yueqingRMS.business.system.domain.UserDomain;
import com.yueqingRMS.business.system.domain.UserDao;




/**   
*    
* 项目名称：yueqingRMS   
* 类名称：UserAL   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 下午2:15:43   
* 修改人：   
* 修改时间：2017年7月18日 下午2:15:43   
* 修改备注：   
* @version    
*    
*/
@Component
public class UserAL extends BaseAL<UserDomain>{
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	public int insert(UserDomain object) {
		return userDao.insert(object);
	}
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int updateByKey(UserDomain object) {
		return userDao.updateByKey(object);
	}
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int deleteByKey(UserDomain object) {
		return userDao.deleteByKey(object);
	}
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	public  UserDomain loadByKey(UserDomain object) {
		return userDao.loadByKey(object);
	}
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	public Page findByWhereForPage(UserDomain object) {
		Page page = new Page();
		page.setPageInfo(object.getPageInfo());
		page.setResult(userDao.findByWhereForPage(object));
		return page;
	}

	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return List
	 */
	@Override
	public List<UserDomain> findByWhereForList(UserDomain object) {
		// TODO Auto-generated method stub
		return userDao.findByWhereForList(object);
	}
	

	/**
	 * @return userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	
	
}
