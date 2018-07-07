package com.yueqingRMS.business.knowledgebase.download.service.Impl;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yueqingRMS.business.knowledgebase.download.service.IDownloadFileService;
import com.yueqingRMS.platform.exception.AppBusinessException;




/**   
*    
* 项目名称：yueqingRMS   
* 类名称：DownloadFileServiceImpl   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月4日 上午9:16:04   
* 修改人：   
* 修改时间：2017年8月4日 上午9:16:04   
* 修改备注：   
* @version    
*    
*/
@Transactional
@Service
public  class DownloadFileServiceImpl  implements IDownloadFileService{

//	@Autowired
	
	/**
	 *文件下载
	 *
	 */
	@Override
	public ResponseEntity<byte[]> downLoadFileBySpring(String path,String filename) {

		
		ResponseEntity<byte[]> re = null;
//		if(serviceFile.exists()) {
//			System.out.printf(f+"     "+Name);
//			//将文件存放路径，时间，上传用户，省市，机构，部门入库
//			return pdserviceAL.upLoadFile(request);
//		}else{
//			return null;
//		}
		//导出这个文件到本地
		if((new File(path)!= null)) {
			HttpHeaders headers = new HttpHeaders();
			try {
				headers.setContentDispositionFormData("attachment",new String( filename.getBytes("UTF-8"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				re = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(path)),headers,HttpStatus.OK);
				return re;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
//				//对于临时导出功能，删除服务端的该文件。
//				File file= new File(path);
//				if(file.exists()){
//					file.delete();
				}
			}
			
		 return null;
	}

    public void downloadLocal(String filePath,HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "Operator.doc".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
//        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        try {
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//解决中文问题
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //通过Servlet程序实现下载
  	public HttpServletResponse download(String path, HttpServletResponse response) {
          try {
              // path是指欲下载的文件的路径。
              File file = new File(path);
              // 取得文件名。
              String filename = file.getName();
              
              // 以流的形式下载文件。
              InputStream fis = new BufferedInputStream(new FileInputStream(path));
              byte[] buffer = new byte[fis.available()];
              fis.read(buffer);
              fis.close();
              // 清空response
              response.reset();
              // 设置response的Header
              response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
//              response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));//解决中文问题
              response.addHeader("Content-Length", "" + file.length());
              OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
              response.setContentType("application/octet-stream");
              toClient.write(buffer);
              toClient.flush();
              toClient.close();
          } catch (IOException ex) {
              ex.printStackTrace();
          }
          return response;
      }
  	//支持在线打开文件的一种方式
    public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
//            response.sendError(404, "File not found!");
//            return;
        	throw new AppBusinessException("文件不存在");
        }
        // 取得文件的后缀名。
        //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
        //byte[] buffer = new byte[1024];
        byte[] buffer = new byte[fis.available()];
     // 清空response
        response.reset(); // 非常重要
     // 设置response的Header
        response.addHeader("Content-Length", "" + file.length());
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
//            response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
            response.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));//解决中文问题
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        }
        //舍弃基本写法
//      int len = 0;
//      OutputStream out = response.getOutputStream();
//      	while ((len = fis.read(buffer)) > 0){
//        	out.write(buffer, 0, len);
//        }
//      fis.close();
//      out.close();
        
        //利用Buffered 直接读 写    
        fis.read(buffer);
        fis.close();
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        
    }
    
    public void downLoadFromStream(InputStream in,String fileName, HttpServletResponse response) throws Exception {
        // 取得文件流
        InputStream fis = new BufferedInputStream(in);
        //byte[] buffer = new byte[1024];
        byte[] buffer = new byte[fis.available()];
     // 清空response
        response.reset(); // 非常重要
     // 设置response的Header
        response.setContentType("application/x-msdownload");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));//解决中文问题
        //利用Buffered 直接读 写    
        fis.read(buffer);
        fis.close();
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        
    }

    /** 
     * 从网络Url中下载文件 (1)
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */  
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      
  
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
  
  
        System.out.println("info:"+url+" download success");   
  
    }  
  
  
  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
  
    public static void main(String[] args) {  
        try{  
            downLoadFromUrl("http://101.95.48.97:8005/res/upload/interface/apptutorials/manualstypeico/6f83ce8f-0da5-49b3-bac8-fd5fc67d2725.png",  
                    "百度.jpg","d:/resource/images/diaodiao/country/");  
        }catch (Exception e) {  
            // TODO: handle exception  
        }  
    }  
    
    /** 
     * 从网络Url中下载文件 (2)
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */
    @SuppressWarnings("resource")
	public void downloadNet(HttpServletResponse response) throws MalformedURLException {
        // 下载网络文件到本地服务器
        int bytesum = 0;
        int byteread = 0;

        URL url = new URL("windine.blogdriver.com/logo.gif");

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("C:/Users/Administrator/Desktop/downloadtext");

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	
	
}
