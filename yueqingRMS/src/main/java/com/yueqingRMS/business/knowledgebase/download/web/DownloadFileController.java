package com.yueqingRMS.business.knowledgebase.download.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yueqingRMS.business.knowledgebase.download.service.IDownloadFileService;
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
@RequestMapping("business/knowledgebase/downloadfile")
public class DownloadFileController extends BaseController {
	
	@Autowired
	private IDownloadFileService downloadFileServiceImpl;
	
	/**
	 * @return UploadFileIndex 界面
	 */
	@RequestMapping("/findDownFile_IndexPage")
	public String findDownFile_IndexPage(){
		return "business/knowledgebase/downloadfile/DownloadFileIndex";	
	}
	
	/**
	 *CommonsMultipartResolver
	 *spring上传功能 
	 *文件上传 
	 * 
	 **/
//	@RequestMapping("/upLoadFileBySpring")
//	public ModelAndView upLoadFileBySpring(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
//		return uploadFileServiceImpl.upLoadFileBySpring(request);
//	}
	
	/**
	 * 
	 *文件下载
	 * @throws Exception 
	 * 
	 **/
	@RequestMapping("/downLoadFileCanOnLine")
	public void downLoadFileCanOnLine(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam("filePath") String filePath) throws Exception{
		 downloadFileServiceImpl.downLoad( filePath,  response,  false);
//		 downloadFileServiceImpl.downloadLocal(filePath,response);
//		 downloadFileServiceImpl.download(filePath,  response );
		 //downloadFileServiceImpl.downloadNet(response );
		 
	}
	
	/**
	 * 
	 *文件下载
	 * @throws Exception 
	 * 
	 **/
	@RequestMapping("/downLoadFileByHttpServletResponse")
	public HttpServletResponse downLoadFileByHttpServletResponse(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam("filePath") String filePath,@RequestParam("isOnLine") boolean isOnLine) throws Exception{
		 return downloadFileServiceImpl.download(filePath,  response );
	}
	
	/**
	 * 
	 *文件下载
	 * @throws Exception 
	 * 
	 **/
	@RequestMapping("/downLoadFileBySpring")
	public ResponseEntity<byte[]> downLoadFileBySpring(Model model,HttpServletRequest request,HttpServletResponse response,@RequestParam("filePath") String filePath,@RequestParam("file") String file) throws Exception{
		 return downloadFileServiceImpl.downLoadFileBySpring(filePath,  file );
	}
	
}