package com.yueqingRMS.business.knowledgebase.demo.domain;

import com.yueqingRMS.platform.tools.page.Page;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：DemoQuery   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 下午4:03:23   
* 修改人：   
* 修改时间：2017年7月18日 下午4:03:23   
* 修改备注：   
* @version    
*    
*/
public class DemoDomain extends Page {

	/**
	 * 
	 */

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8717871936242573882L;
	/**
	 *  orgID
	 */
	private String orgId;
	/**
	 *  orgName
	 */
	private String orgName;
	
	/**
	 * @return orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
