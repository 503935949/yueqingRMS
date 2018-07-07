package com.yueqingRMS.business.knowledgebase.tablefilereadandwrite.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqingRMS.business.knowledgebase.tablefilereadandwrite.service.ITableFileReadAndWriteService;
import com.yueqingRMS.platform.base.web.BaseController;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：TableFileReadAndWriteController   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月3日 下午4:49:12   
* 修改人：   
* 修改时间：2017年8月3日 下午4:49:12   
* 修改备注：   
* @version    
*    
*/
@Controller
@RequestMapping("business/knowledgebase/tablefilereadandwrite")
public class TableFileReadAndWriteController  extends BaseController  {
	
	@Autowired
	private ITableFileReadAndWriteService tableFileReadAndWriteServiceImpl;
	
	/**
	 * @return UploadFileIndex 界面
	 */
	@RequestMapping("/findTableFileReadAndWrite_IndexPage")
	public String findTableFileReadAndWrite_IndexPage(){
		return "business/knowledgebase/tablefilereadandwrite/TableFileReadAndWriteIndex";	
	}
	
	
	/**
	 * 
	 *commons-fileupload-1.2.2.jar上传功能 
	 *上传并解析ET/xls/xlsx文件内容
	 * @throws Exception 
	 * 
	 **/
	@RequestMapping("/upLoadFile4EtInfo03Or07")
	public void upLoadFile4EtInfo(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		tableFileReadAndWriteServiceImpl.upLoadFile4EtInfo03Or07(request);
	}
	
	
	/**
	 * 
	 * 文件下载
	 * EtInfo03Or07 导出
	 * @throws Exception 
	 * 
	 **/
	@RequestMapping("/downLoadFile4EtInfo03Or07")
	public void downLoadFile4EtInfo03Or07(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		tableFileReadAndWriteServiceImpl.downLoadFile4EtInfo03Or07( response );
	}
	
	
}