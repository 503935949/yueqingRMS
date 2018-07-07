package com.yueqingRMS.business.common.domain;

import java.io.Serializable;
import java.util.Locale;


/**   
*    
* 项目名称：yueqingRMS   
* 类名称：InternaDomain   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 下午4:03:23   
* 修改人：   
* 修改时间：2017年7月18日 下午4:03:23   
* 修改备注：   
* @version    
*    
*/
public class InternaDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -966380595601861146L;

	/**
	 * 
	 */

	
	private String language ;
	
	private String isReload ;
	
	private Locale locale;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getIsReload() {
		return isReload;
	}

	public void setIsReload(String isReload) {
		this.isReload = isReload;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
