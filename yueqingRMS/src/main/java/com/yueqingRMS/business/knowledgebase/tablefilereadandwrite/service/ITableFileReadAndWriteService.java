package com.yueqingRMS.business.knowledgebase.tablefilereadandwrite.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
public interface ITableFileReadAndWriteService {


	/**
	 * 
	 *commons-fileupload-1.2.2.jar上传功能 
	 *上传并解析ET/xls/xlsx文件内容
	 * @throws Exception 
	 * 
	 **/
	public void upLoadFile4EtInfo03Or07(HttpServletRequest request);

	/**
	 * 
	 * 文件下载
	 * EtInfo03Or07 导出
	 * @throws Exception 
	 * 
	 **/
	public void downLoadFile4EtInfo03Or07(HttpServletResponse response);

	
	
	
}
