package com.yueqingRMS.business.knowledgebase.email.service;


import javax.servlet.http.HttpServletRequest;

import com.yueqingRMS.business.knowledgebase.email.domain.MailDomain;
import com.yueqingRMS.platform.base.service.IBaseService;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：IMailService   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月7日 下午4:59:15   
* 修改人：   
* 修改时间：2017年8月7日 下午4:59:15   
* 修改备注：   
* @version    
*    
*/
public interface IMailService extends IBaseService<MailDomain> {

	
	/**
	 * sendMail_bySpring
	 * @param mailDomain
	 * @return
	 */
	public String sendMail_bySpring(MailDomain mailDomain);

	/**
	 * 自己封装工具类方式
	 * @param mailDomain
	 * @return
	 */
	public String sendMail_byJavaMail(MailDomain mailDomain);

	/**
	 * 自己封装工具类方式(wenjian)
	 * @param mailDomain
	 * @return
	 */
	public String sendMail_byJavaMailWithFile(MailDomain mailDomain);

	/**
	 * 自己封装工具类方式(wenjian)
	 * @param mailDomain
	 * @return
	 */
	public String sendMail_byJavaMailWithFile(MailDomain mailDomain, HttpServletRequest request);
	
	
}
