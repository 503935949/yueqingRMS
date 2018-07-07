package com.yueqingRMS.business.knowledgebase.demo.application;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yueqingRMS.business.knowledgebase.demo.domain.DemoDao;
import com.yueqingRMS.business.knowledgebase.demo.domain.DemoDomain;
import com.yueqingRMS.platform.base.application.BaseAL;
import com.yueqingRMS.platform.tools.page.Page;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：DemoAL   
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
public class DemoAL extends BaseAL<DemoDomain>{
	
	@Autowired
	private DemoDao demoDao;
	
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	public int insert(DemoDomain object) {
		return demoDao.insert(object);
	}
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int updateByKey(DemoDomain object) {
		return demoDao.updateByKey(object);
	}
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	public  int deleteByKey(DemoDomain object) {
		return demoDao.deleteByKey(object);
	}
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	public  DemoDomain loadByKey(DemoDomain object) {
		return demoDao.loadByKey(object);
	}
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	public Page findByWhereForPage(DemoDomain object) {
		Page page = new Page();
		page.setPageInfo(object.getPageInfo());
		page.setResult(demoDao.findByWhereForPage(object));
		return page;
	}

	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return List
	 */
	@Override
	public List<DemoDomain> findByWhereForList(DemoDomain object) {
		// TODO Auto-generated method stub
		return demoDao.findByWhereForList(object);
	}
	

	/**
	 * @return demoDao
	 */
	public DemoDao getDemoDao() {
		return demoDao;
	}

	/**
	 * @param demoDao
	 */
	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}



	
	
}
