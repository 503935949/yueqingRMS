package com.yueqingRMS.business.knowledgebase.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yueqingRMS.business.knowledgebase.demo.application.DemoAL;
import com.yueqingRMS.business.knowledgebase.demo.domain.DemoDomain;
import com.yueqingRMS.business.knowledgebase.demo.service.IDemoService;
import com.yueqingRMS.platform.tools.page.Page;



@Transactional
@Service
public  class DemoServiceImpl  implements IDemoService{

	@Autowired
	private DemoAL demoAL;
	
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int insert(DemoDomain object) {
		return 0;
	}
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int updateByKey(DemoDomain object) {
		return 0;
	}
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int deleteByKey(DemoDomain object) {
		return 0;
	}
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	@Transactional
	public  DemoDomain loadByKey(DemoDomain object) {
		return null;
	}
	
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	@Transactional
	public  Page findByWhereForPage(DemoDomain object) {
//		return toPage (demoAL.findByWhereForPage(object));
		return demoAL.findByWhereForPage(object);
	}
	
	public Page toPage (List<DemoDomain> list) {
		Page page = new Page();
		
		return page;
	}
	
	/**
	 * list分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	@Transactional
	public  List<DemoDomain> findByWhereForList(DemoDomain object) {
		return demoAL.findByWhereForList(object);
	}
	

	
	/**
	 * @return demoAL
	 */
	public DemoAL getBaseAL() {
		return demoAL;
	}

	/**
	 * @param demoAL
	 */
	public void setBaseAL(DemoAL demoAL) {
		this.demoAL = demoAL;
	}

	
	
}
