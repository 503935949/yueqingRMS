//package com.yueqingRMS.util;
//
//import java.io.File;
//
//import org.apache.tools.ant.Project;
//import org.apache.tools.ant.taskdefs.Expand;
//
//
///**   
//*    
//* 项目名称：yueqingRMS   
//* 类名称：UnZipHelper   
//* 类描述：   
//* 创建人：林曌   
//* 创建时间：2017年8月10日 下午5:18:46   
//* 修改人：   解压zip格式压缩包  
//* 修改时间：2017年8月10日 下午5:18:46   
//* 修改备注：   
//* @version    
//*    
//*/
//public class UnZipHelper {
//	/**  
//	 * 解压zip格式压缩包  
//	 * 对应的是ant.jar  
//	 */
//	public static void unzip(String sourceZip, String destDir) throws Exception {
//		try {
//			Project p = new Project();
//			Expand e = new Expand();
//			e.setProject(p);
//			e.setSrc(new File(sourceZip));
//			e.setOverwrite(false);
//			e.setDest(new File(destDir));
//			/*  
//			ant下的zip工具默认压缩编码为UTF-8编码，  
//			而winRAR软件压缩是用的windows默认的GBK或者GB2312编码  
//			所以解压缩时要制定编码格式  
//			*/
//			e.setEncoding("gbk");
//			e.execute();
//		} catch (Exception e) {
//			throw e;
//		}
//	}
//
//	/**
//	 * 解压缩
//	 * @param sourceFile zip源文件
//	 * @param destDir 解压后指定路径
//	 * @throws Exception
//	 */
//	public static void deCompress(String sourceFile) throws Exception {
//		String destDir = sourceFile.substring(0, sourceFile.lastIndexOf("."));
//		//保证文件夹路径最后是"/"或者"\"   
//		char lastChar = destDir.charAt(destDir.length() - 1);
//		if (lastChar != '/' && lastChar != '\\') {
//			destDir += File.separator;
//		}
//		//根据类型，进行相应的解压缩   
//		String type = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
//		if (type.equals("zip")) {
//			UnZipHelper.unzip(sourceFile, destDir);
//		} else {
//			throw new Exception("只支持zip格式的压缩包！");
//		}
//	}
//
//}
//
