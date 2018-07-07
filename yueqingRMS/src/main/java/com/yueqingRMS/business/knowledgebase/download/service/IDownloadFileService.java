package com.yueqingRMS.business.knowledgebase.download.service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

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
public interface IDownloadFileService {


	public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception;

	public void downloadLocal(String filePath, HttpServletResponse response) throws FileNotFoundException;

	public HttpServletResponse download(String filePath, HttpServletResponse response);

	public void downloadNet(HttpServletResponse response) throws MalformedURLException;

	public ResponseEntity<byte[]> downLoadFileBySpring(String path, String filename);


	/**
	 *CommonsMultipartResolver
	 *spring上传功能 
	 *文件上传 
	 * 
	 **/



	

	
	
	
}
