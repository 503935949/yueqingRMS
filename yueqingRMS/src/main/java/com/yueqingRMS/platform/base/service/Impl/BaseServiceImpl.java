package com.yueqingRMS.platform.base.service.Impl;

import org.springframework.transaction.annotation.Transactional;

import com.yueqingRMS.platform.base.service.IBaseService;


@Transactional
public abstract class BaseServiceImpl<T> implements IBaseService<T>{

//	@Autowired
//	private BaseAL<T> baseAL;
	
//	/**
//	 * 新增
//	 * @param object 对象，传参用
//	 * @return int
//	 */
//	@Transactional
//	public  int insert(T object) {
//		return 0;
//	}
//	
//	/**
//	 * 修改 
//	 * @param object 对象，传参用
//	 * @return int
//	 */
//	@Transactional
//	public  int updateByKey(T object) {
//		return 0;
//	}
//	
//	/**
//	 * 删除 
//	 * @param object 对象，传参用
//	 * @return int
//	 */
//	@Transactional
//	public  int deleteByKey(T object) {
//		return 0;
//	}
//	
//	/**
//	 * 查询  
//	 * @param object 对象，传参用
//	 * @return object
//	 */
//	@Transactional
//	public  T loadByKey(T object) {
//		return null;
//	}
//	
//	/**
//	 * 查询分页  
//	 * @param object 对象，传参用
//	 * @return Page
//	 */
//	public  Page<T> findByWhereForPage(T object) {
//		return null;
//	}
//	
//	/**
//	 * 查询
//	 * @param object 对象，传参用
//	 * @return Page
//	 */
//	public  List<T> findByWhereForList(T object) {
//		return null;
//	}
//
//	
//	/**
//	 * @return baseAL
//	 */
//	public BaseAL<T> getBaseAL() {
//		return baseAL;
//	}
//
//	/**
//	 * @param baseAL
//	 */
//	public void setBaseAL(BaseAL<T> baseAL) {
//		this.baseAL = baseAL;
//	}

	
}
