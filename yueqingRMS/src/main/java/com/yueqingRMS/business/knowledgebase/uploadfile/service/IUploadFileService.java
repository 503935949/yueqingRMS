package com.yueqingRMS.business.knowledgebase.uploadfile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：IUploadFileService   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 下午2:15:47   
* 修改人：   
* 修改时间：2017年7月18日 下午2:15:47   
* 修改备注：   
* @version    
*    
*/
public interface IUploadFileService {

	/**
	 *CommonsMultipartResolver
	 *spring上传功能 
	 *文件上传 
	 * 
	 **/
	public ModelAndView upLoadFileBySpring(HttpServletRequest request);

	/**
	 * 
	 *commons-fileupload-1.2.2.jar上传功能 
	 *文件上传 
	 * @throws Exception 
	 * 
	 **/
	public ModelAndView upLoadFileByCommonsFileupload(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;


	

	
	
	
}
