package com.yueqingRMS.business.knowledgebase.uploadfile.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yueqingRMS.business.knowledgebase.uploadfile.service.IUploadFileService;
import com.yueqingRMS.platform.base.web.BaseController;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：UploadFileController   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月3日 下午4:49:21   
* 修改人：   
* 修改时间：2017年8月3日 下午4:49:21   
* 修改备注：   
* @version    
*    
*/
@Controller
@RequestMapping("business/knowledgebase/uploadfile")
public class UploadFileController  extends BaseController {
	
	@Autowired
	private IUploadFileService uploadFileServiceImpl;
	
	/**
	 * @return UploadFileIndex 界面
	 */
	@RequestMapping("/findUpFile_IndexPage")
	public String findUpFile_IndexPage(){
		return "business/knowledgebase/uploadfile/UploadFileIndex";	
	}
	
	/**
	 *CommonsMultipartResolver
	 *spring上传功能 
	 *文件上传 
	 * 
	 **/
	@RequestMapping("/upLoadFileBySpring")
	public ModelAndView upLoadFileBySpring(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		return uploadFileServiceImpl.upLoadFileBySpring(request);
	}
	
	/**
	 * 
	 *commons-fileupload-1.2.2.jar上传功能 
	 *文件上传 
	 * @throws Exception 
	 * 
	 **/
	@RequestMapping("/upLoadFileByCommonsFileupload")
	public ModelAndView upLoadFileByCommonsFileupload(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return uploadFileServiceImpl.upLoadFileByCommonsFileupload(model,request,response);
	}
	
	
	
}