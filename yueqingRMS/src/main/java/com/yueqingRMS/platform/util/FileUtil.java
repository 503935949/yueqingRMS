package com.yueqingRMS.platform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：FileUtil   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月11日 上午11:13:08   
* 修改人：   
* 修改时间：2017年8月11日 上午11:13:08   
* 修改备注：   
* @version    
*    
*/
public class FileUtil {
	
	/**删除单个文件
	 * 注意事项：   
	 * @param filePath 文件的路径
	 * @return   
	*/
	public static boolean delFile(String filePath){
		//if(filePath != null && !"".equals(filePath)){
			File file = new File(filePath);
			if(file.exists()) {  
				file.delete();  
				return true;
			} 
		//}
		return false;
	}
	
	/**文件上传
	 * 获得上传文件名字（毫秒+随机数）,有后缀名
	 * @param fileName 
	 * @return
	 */
	public static String createNewName(String oldFileName) {
		String newName = null;
		String format = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
		newName = createRandomName() + "." + format;
		return newName;
	}
	
	/**文件上传
	 * 获得上传文件名字（毫秒+随机数）,无后缀名
	 * @return
	 */
	public static String createRandomName() {
		String fileName = null;
		String strDate = DateUtil4Timeslecte.dateFormatString(new Date(), "yyyyMMddhhmmssSSS");
		fileName = strDate + createCode(4);
		return fileName;
	}
	
	/**	生成len位随机数
	 * @return   
	*/
	public static String createCode(int len) {
		Random random = new Random();
		StringBuffer randomCode = new StringBuffer();
		for (int i = 0; i < len; i++) {
			String strRand = String.valueOf(random.nextInt(10));
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}
	
	/**
	 * 检查上传文件后缀名是否非法   
	 * @param fileType 只允许：zip\rar
	 * @return   
	*/
	public static boolean checkZipFileType(String fileType){
		boolean isTrue = false;
		if(fileType != null && !"".equals(fileType)){
			if("zip".equals(fileType) || "rar".equals(fileType)){
				isTrue = true;
			}
		}
		return isTrue;
	}
	
	/**
	 * 检查上传图片后缀名是否非法   
	 * @param fileType 只允许：png\jpg
	 * @return   
	*/
	public static boolean checkImgType(String fileType){
		boolean isTrue = false;
		if(fileType != null && !"".equals(fileType)){
			if("png".equals(fileType) || "jpg".equals(fileType)){
				isTrue = true;
			}
		}
		return isTrue;
	}
	
	/**
	 * 得到文件后缀名   
	 * @param fileName
	 * @return   
	*/
	public static String getFileSuf(String fileName){
		String format = fileName.substring(fileName.lastIndexOf(".")+1);
		return format;
	}
	
	/** 
	* @Title: copyFile 
	* @Description: 文件复制
	* @param @param src
	* @param @param dest
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void copyFile(String src, String dest) {
		try {
			FileInputStream in = new FileInputStream(src);
			File file = new File(dest);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			int c;
			byte buffer[] = new byte[1024];
			while ((c = in.read(buffer)) != -1) {
				for (int i = 0; i < c; i++)
					out.write(buffer[i]);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 截取文件后缀名，返回后缀名前面字符串
	 * @param fileName
	 * @return   
	*/
	public static String getFilePrefix(String fileName){
		int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
	}
	/**
	 * 得到文件名称   
	 * @param filePath 文件上传路径和文件名称
	 * @return   
	*/
	public static String getFileName(String filePath) {
		int num = filePath.lastIndexOf("\\");
		if(num < 0){
			num = filePath.lastIndexOf("/");
		}
		String format = filePath.substring(num + 1);
		return format;
	}

	/**
	 * 文件流转文件
	 * @param ins
	 * @param file
	 * @return
	 */
	public static File inputstreamtofile(InputStream ins,File file){
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		try {
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file;
	}
	
	/**
     * 解析传送来的文件
     * @param request
     * @return
     */
    public static Map<String, InputStream> upLoadFileStream(HttpServletRequest request){
		
		InputStream fileStream = null;
		//传入的文件名（最终保存的文件名称：不含有目录信息）
		String fileName= "";
		//文件信息存储列表（DEMO用）
		//文件处理
		Map<String, InputStream> files = new HashMap<String, InputStream>();
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver(request.getSession().getServletContext());
		//设置编码  
		multipartResolver.setDefaultEncoding("utf-8");  
		 //判断 request 是否有文件上传,即多部分请求...  
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
//			MultipartHttpServletRequest  multiRequest = multipartResolver.resolveMultipart(request);	
			MultipartHttpServletRequest  multiRequest = (MultipartHttpServletRequest)request;	
			//迭代器遍历所有文件名称
			Iterator<String>  iter = multiRequest.getFileNames();
			
			while(iter.hasNext()){
				
				MultipartFile file = multiRequest.getFile((String)iter.next());
				fileName =  file.getOriginalFilename();
				
				if(file != null){
					try {
						fileStream =  file.getInputStream();
						files.put(fileName,fileStream);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return files;
	}
}
