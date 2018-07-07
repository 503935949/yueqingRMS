package com.yueqingRMS.business.system.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yueqingRMS.business.system.service.IUserService;
import com.yueqingRMS.platform.base.service.Impl.BaseServiceImpl;
import com.yueqingRMS.platform.tools.page.Page;
import com.yueqingRMS.business.system.application.UserAL;
import com.yueqingRMS.business.system.domain.UserDomain;



@Transactional
@Service
public  class UserServiceImpl extends BaseServiceImpl<UserDomain> implements IUserService{

	@Autowired
	private UserAL userAL;
	
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int insert(UserDomain object) {
		return 0;
	}
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int updateByKey(UserDomain object) {
		return 0;
	}
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int deleteByKey(UserDomain object) {
		return 0;
	}
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	@Transactional
	public  UserDomain loadByKey(UserDomain object) {
		return null;
	}
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	@Transactional
	public  Page findByWhereForPage(UserDomain object) {
		return userAL.findByWhereForPage(object);
	}
	
	
	/**
	 * list分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	@Transactional
	public  List<UserDomain> findByWhereForList(UserDomain object) {
		return userAL.findByWhereForList(object);
	}
	

	
	/**
	 * @return UserAL
	 */
	public UserAL getBaseAL() {
		return userAL;
	}

	/**
	 * @param UserAL
	 */
	public void setBaseAL(UserAL userAL) {
		this.userAL = userAL;
	}

	
	
}
