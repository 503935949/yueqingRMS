package com.yueqingRMS.business.knowledgebase.uploadfile.service.Impl;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.yueqingRMS.business.knowledgebase.uploadfile.service.IUploadFileService;
import com.yueqingRMS.platform.util.DateUtil4Timeslecte;
import com.yueqingRMS.platform.util.FileUtil;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：UploadFileServiceImpl   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月3日 下午4:49:30   
* 修改人：   
* 修改时间：2017年8月3日 下午4:49:30   
* 修改备注：   
* @version    
*    
*/
@Transactional
@Service
public  class UploadFileServiceImpl  implements IUploadFileService{

//	@Autowired
	
	
	
	/**
	 *CommonsMultipartResolver
	 *spring上传功能 
	 *文件上传 
	 * 
	 * 
	 **/
	@Override
	public ModelAndView upLoadFileBySpring(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView( new MappingJackson2JsonView());
		// Java输入输出临时路径  
		String sysTemp = System.getProperty("java.io.tmpdir");
		// 用户的主目录 
//		String sysTemp = System.getProperty("user.home"); 
		//传入的文件名（最终保存的文件名称：不含有目录信息）
		String fileName="";
		//文件名（最终保存的文件名称：不含有目录信息）
		List<Map<String, String>> fNames = new ArrayList<Map<String, String>>();
		//保存路径（不带有文件名的路径）
		//跨平台分隔符--File.separator
		String path = sysTemp+File.separator;
		//创建存储资源文件夹
		File f = new File(path);
		//当前使用系统目录，不需要验证是否存在，更不可删除、创建
//		if(!f.exists()){
//			f.mkdirs();
//		}
		
		//文件处理
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver(request.getSession().getServletContext());
		//设置编码  
		multipartResolver.setDefaultEncoding("utf-8");  
		 //判断 request 是否有文件上传,即多部分请求...  
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
			MultipartHttpServletRequest  multiRequest = (MultipartHttpServletRequest)request;	
//					MultipartHttpServletRequest  multiRequest = multipartResolver.resolveMultipart(request);	
			//迭代器遍历所有文件名称
			Iterator<String>  iter = multiRequest.getFileNames();
//			int fi = 0;
			while(iter.hasNext()){
				
				MultipartFile file = multiRequest.getFile((String)iter.next());
				Map<String, String> fileMap = new HashMap<String, String>();
				if(file != null){
					//创建文件路径
					fileName =  file.getOriginalFilename();
					fileMap.put("fileName", fileName);  //文件原始名称
					//根据文件名创建文件
//					File localFile = new File(sysTemp,path);//下同
					File localFile = new File(f,fileName);
					fileMap.put("filePath", path); //文件存放位置
					//如果已存在同名文件，在文件名称末尾加上（n）
					if(localFile.exists()){
						
//						int i=0;
						do{
							//仿Windows复制名称
//							if(i==0){
//								Name =FileUtil.getFilePrefix(FileUtil.getFileName(fileName))+ "- 副本"+"."+FileUtil.getFileSuf(fileName);
//								i++;
//							}else {
//								Name =FileUtil.getFilePrefix(FileUtil.getFileName(fileName))+ "- 副本("+(i++)+")"+"."+FileUtil.getFileSuf(fileName);
//							}
							//结尾直接加（数字）
//							Name =FileUtil.getFilePrefix(FileUtil.getFileName(fileName))+ "("+(++i)+")"+"."+FileUtil.getFileSuf(fileName);
							//结尾直接加时间戳
							String strDate = DateUtil4Timeslecte.dateFormatString(new Date(), "yyyyMMddhhmmssSSS");
							fileName =FileUtil.getFilePrefix(FileUtil.getFileName(fileName))+strDate+"."+FileUtil.getFileSuf(fileName);
							localFile = new File(f,fileName);
							
							fileMap.put("newName", fileName);    //文件新名称
							
						}while(localFile.exists());
							try {
								file.transferTo(localFile);
							} catch (IllegalStateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					//如果不存在同名文件，则直接上传	
					}else{
						fileMap.put("newName", fileName);    //文件新名称
						try {
							file.transferTo(localFile);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					fNames.add(fileMap);
					
				}	
//				fi++;
			}	
		}
		
		
		String ofName = null;
		String nfName = null;
		String fPath = null;
		//如果已存在该文件
		if(fNames.size() == 1) {
			 ofName = fNames.get(0).get("fileName");
			 nfName = fNames.get(0).get("newName");
			 fPath = fNames.get(0).get("filePath");
//			File serviceFile = new File(f,nfName);
//			if(serviceFile.exists()) {
				System.out.printf("\n"+path+"     "+nfName+"\n");
				//将文件存放路径，时间，上传用户，省市，机构，部门入库
				//异步存放，将消息传回
				mav.addObject("fileName", ofName);  //文件原始名称
				mav.addObject("newName", nfName);    //文件新名称
				mav.addObject("filePath", fPath); //文件存放位置
				
				return mav ;
//			}
		}
		else{
			int ffi = 0;
			for(Map<String, String> file:fNames) {
				ffi++;
				ofName = file.get("fileName");
				nfName = file.get("newName");
				fPath = file.get("filePath");
//				File serviceFile = new File(f,nfName);
//				if(serviceFile.exists()) {
					
					System.out.printf("\n"+ffi+"    "+path+"     "+nfName+"\n");
//				}
			}
		}
		return mav;
	}
	
	/*******
	 * commons-fileupload-1.2.2.jar
	 * 课件上传
	 * 存放位置：upload/file
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView upLoadFileByCommonsFileupload(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		ModelAndView mav = new ModelAndView( new MappingJackson2JsonView());
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		// 存放位置段路径
		String shortPath = "upload" + File.separator + "file" + File.separator;
		// 设置文件存储位置
		String foldPath = request.getSession().getServletContext().getRealPath("/") + shortPath;
		//这里用java临时地址
//		String foldPath = System.getProperty("java.io.tmpdir");
		File file = new File(foldPath);
		if (!file.exists())
			file.mkdirs();
		factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setSizeMax(10002400000l);
		// 设置整个request的最大值
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");
		/*SpringMVC MultipartHttpServletRequest中已经使用parseRequest方式封装，在SpringMVC中需要转换成此格式
		 	下是两种方式解决这个问题
		public String upload(HttpServletRequest request,  
	            @RequestParam(value = "file") MultipartFile[] files) {  
	        try {  
	            for (MultipartFile mf : files) {  
	                if(!mf.isEmpty()){  
	                      
	                }  
	            }  
	  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return "upload";  
	    }  
		
		public void upload2(HttpServletRequest request) {  
	        // 转型为MultipartHttpRequest  
	        try {  
	            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	            List<MultipartFile> fileList = multipartRequest.getFiles("file");  
	            for (MultipartFile mf : fileList) {  
	                if(!mf.isEmpty()){  
	                      
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	          
	    }*/
		
		
		MultipartHttpServletRequest  multipartHttpServletRequest= (MultipartHttpServletRequest)request;
		try {
//			List<?> items = upload.parseRequest(request);
			List<?> items =  multipartHttpServletRequest.getFiles("file");  
//			FileItem item = null;
			MultipartFile item = null;
			String fileName = null;
			String newName = null;
			for (int i = 0; i < items.size(); i++) {
//				item = (FileItem) items.get(i);
				item = (MultipartFile) items.get(i);
				// 保存文件
//				if (!item.isFormField() && item.getName().length() > 0) {
				if (item.getName().length() > 0) {
//					fileName = FileUtil.getFileName(item.getName());
					fileName = item.getOriginalFilename();
					String fileType = FileUtil.getFileSuf(fileName);
					
					if(FileUtil.checkZipFileType(fileType)){
						newName = FileUtil.createNewName(fileName);  //
						BufferedInputStream in = new BufferedInputStream(item.getInputStream());
						// 文件存储在工程的upload/file目录下
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(foldPath + newName)));
						byte[] data = new byte[1]; 
						while (in.read(data) != -1) {
							out.write(data);
						}						
						mav.addObject("fileName", fileName);  //课件原始名称
						mav.addObject("filePath", shortPath); //课件存放位置
						mav.addObject("newName", newName);    //课件新名称
						
						out.flush(); // 关闭串流
						in.close();
						out.close();
						//解压文件
						String zipPath = foldPath + newName;
//						UnZipHelper.deCompress(zipPath);
					}else{
						mav.addObject("msg", "上传文件格式非法！");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////
	/*******
	 * commons-fileupload-1.2.2.jar
	 * 课程管理图片上传
	 * 存放位置：upload/classImage
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView uploadImageFile(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		ModelAndView mav = new ModelAndView( new MappingJackson2JsonView());
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		// 存放位置段路径
		String shortPath = "upload" + File.separator + "classImage" + File.separator;
		// 设置文件存储位置
		String foldPath = request.getSession().getServletContext().getRealPath("/") + shortPath;
		File file = new File(foldPath);
		if (!file.exists())
			file.mkdirs();
		factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setSizeMax(10002400000l);
		// 设置整个request的最大值
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");
		try {
			List<?> items = upload.parseRequest(request);
			FileItem item = null;
			String fileName = null;
			String newName = null;
			for (int i = 0; i < items.size(); i++) {
				item = (FileItem) items.get(i);
				// 保存文件
				if (!item.isFormField() && item.getName().length() > 0) {
					fileName = FileUtil.getFileName(item.getName());
					String fileType = FileUtil.getFileSuf(fileName);
					
					if(FileUtil.checkImgType(fileType)){
						newName = FileUtil.createNewName(fileName);
						BufferedInputStream in = new BufferedInputStream(item.getInputStream());
						// 文件存储在工程的upload/classImage目录下
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(foldPath + newName)));
						byte[] data = new byte[1]; 
						while (in.read(data) != -1) {
							out.write(data);
						}
						mav.addObject("fileName", fileName);  //文件原始名称
						mav.addObject("filePath", shortPath); //文件存放位置
						mav.addObject("newName", newName);    //文件新名称
						
						out.flush(); // 关闭串流
						in.close();
						out.close();
					}else{
						mav.addObject("msg", "上传图片文件格式非法！");
					}
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return mav;
	}

	
	/*******
	 * commons-fileupload-1.2.2.jar
	 * 讲师LOGO上传
	 * 存放位置：upload/teaImage
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView uploadLogoFile(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		ModelAndView mav = new ModelAndView( new MappingJackson2JsonView());
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		// 存放位置段路径
		String shortPath = "upload" + File.separator + "teaImage" + File.separator;
		// 设置文件存储位置
		String foldPath = request.getSession().getServletContext().getRealPath("/") + shortPath;
		File file = new File(foldPath);
		if (!file.exists())
			file.mkdirs();
		factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setSizeMax(10002400000l);
		// 设置整个request的最大值
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");
		try {
			List<?> items = upload.parseRequest(request);
			FileItem item = null;
			String fileName = null;
			String newName = null;
			for (int i = 0; i < items.size(); i++) {
				item = (FileItem) items.get(i);
				// 保存文件
				if (!item.isFormField() && item.getName().length() > 0) {
					fileName = FileUtil.getFileName(item.getName());
					String fileType = FileUtil.getFileSuf(fileName);
					
					if(FileUtil.checkImgType(fileType)){
						newName = FileUtil.createNewName(fileName);
						BufferedInputStream in = new BufferedInputStream(item.getInputStream());
						// 文件存储在工程的upload/teaImage目录下
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(foldPath + newName)));
						byte[] data = new byte[1]; 
						while (in.read(data) != -1) {
							out.write(data);
						}
						mav.addObject("fileName", fileName);  //LOGO原始名称
						mav.addObject("filePath", shortPath); //LOGO存放位置
						mav.addObject("newName", newName);    //LOGO新名称
						
						out.flush(); // 关闭串流
						in.close();
						out.close();
					}else{
						mav.addObject("msg", "上传图片文件格式非法！");
					}
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	
	
	/**上传附件  删除之前上传过得 
	 * commons-fileupload-1.2.2.jar
	 * 注意事项：   
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception   
	*/
	public ModelAndView uploadMail(String shortFileName, Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		ModelAndView mav = new ModelAndView( new MappingJackson2JsonView());
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		// 存放位置段路径
//		String shortPath = "upload" + File.separator + "mailFile" + File.separator;
		String shortPath = "upload" + File.separator + shortFileName + File.separator;
		// 设置文件存储位置
		String foldPath = request.getSession().getServletContext().getRealPath("/") + shortPath;
		File file = new File(foldPath);
		if (!file.exists())
			file.mkdirs();
		factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setSizeMax(10002400000l);
		// 设置整个request的最大值
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");
		try {
			List<?> items = upload.parseRequest(request);
			FileItem item = null;
			String fileName = null;
			String newName = null;
			for (int i = 0; i < items.size(); i++) {
				item = (FileItem) items.get(i);
				// 保存文件
				if (!item.isFormField() && item.getName().length() > 0) {
					fileName = FileUtil.getFileName(item.getName());
//					String fileType = FileUtil.getFileSuf(fileName);//文件类型 后缀名
					
					//if(FileUtil.checkFileType(fileType)){
						newName = FileUtil.createNewName(fileName);  //
						BufferedInputStream in = new BufferedInputStream(item.getInputStream());
						// 文件存储在工程的upload/mail目录下
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(foldPath + newName)));
						byte[] data = new byte[1024*8]; 
						while (in.read(data) != -1) {
							out.write(data);
						}						
						mav.addObject("fileName", fileName);  //课件原始名称
						mav.addObject("filePath", shortPath); //课件存放位置
						mav.addObject("newName", newName);    //课件新名称
						out.flush(); // 关闭串流
						in.close();
						out.close();
//					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	
	
}
