package com.yueqingRMS.business.knowledgebase.tablefilereadandwrite.service.Impl;


import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.yueqingRMS.business.knowledgebase.tablefilereadandwrite.service.ITableFileReadAndWriteService;
import com.yueqingRMS.platform.util.StringUtils;




@Transactional
@Service
public  class TableFileReadAndWriteServiceImpl  implements ITableFileReadAndWriteService{

	
	/**
	 * 
	 *commons-fileupload-1.2.2.jar上传功能 
	 *上传并解析ET/xls/xlsx文件内容
	 *兼容03/07/et文件操作（优化版）
	 * @throws Exception 
	 * 
	 **/
	public void upLoadFile4EtInfo03Or07(HttpServletRequest request) {

		//传入的文件名（最终保存的文件名称：不含有目录信息）
		String fileName= "";
		//文件后缀（类型）
		String fileType = "";
		//文件信息存储列表（DEMO用）
		List<List<List<List<String>>>> etInfos = new ArrayList<List<List<List<String>>>>();
		//文件处理
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
					
					InputStream ETreadFile = null;
					//ETreadFile = new FileInputStream("F:/ETExample.et");
					//指定要读取的文件，本例使用之前生成的ETExample.et
					Workbook  wb = null;
					//包装字节流
					try {
						ETreadFile =  file.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//判断文件名后缀*（文件格式）
					fileType = FilenameUtils.getExtension(fileName);
					if("et".equals(fileType) || "xls".equals(fileType)) {
						try {
							 wb = new HSSFWorkbook(ETreadFile);
							 HSSFSheet st =null;
							 HSSFRow row = null;
							 HSSFCell cell = null;
							 getEtInfoAs03Or07(wb, st, row, cell, etInfos, ETreadFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if("xlsx".equals(fileType)) {
						try {
							  wb = new XSSFWorkbook(ETreadFile);
							  XSSFSheet st =null;
							  XSSFRow row = null;
							  XSSFCell cell = null;
							  getEtInfoAs03Or07(wb, st, row, cell, etInfos, ETreadFile);
						} catch (IOException e) {
							e.printStackTrace();
						} 
					}
				}
			}
		}
	}
	//优化兼容前面两个方法
	/**
	 * 兼容03/07/et文件操作
	 * @param wb
	 * @param st
	 * @param row
	 * @param cell
	 * @param etInfos
	 * @param ETreadFile
	 */
	public void getEtInfoAs03Or07(Workbook  wb,Sheet st, Row row, Cell cell, List<List<List<List<String>>>> etInfos,InputStream ETreadFile) {
		//获得sheet数量
		int stNum = wb.getNumberOfSheets();
		List<List<List<String>>> stInfos = new ArrayList<List<List<String>>>();
		//循环sheet层
		for(int si=0;si<stNum;si++) {
			//创建一个工作表对象，从指定的文件流中创建，即上面指定了的文件流
//			XSSFSheet st = wb.getSheet("表格工作表第1页"); 
			//获取名称为"表格工作表第1页"的工作表，可以用getSheetAt(int)方法取得Sheet
			st = wb.getSheetAt(si); 
			
			//获得Row数量
			int rowNum = 0;
			if(st!=null) {
				rowNum = st.getLastRowNum();
			}
			List<List<String>> rowInfos = new ArrayList<List<String>>();
			
			//屏蔽第一行的说明，但需要获取空行数量，大于一定值(暂定为1)后跳出循环以定位有效数据，从第二行开始读取数据
			int nullNUm = 0;
			//获得cell数量
			int cellNum = 0;
			//循环Row层
			for(int ri=0;ri<rowNum;ri++) {
				row = st.getRow(ri); 
				if(row==null){
					nullNUm++;
				}
				if(ri == 0) {
					cellNum = row.getLastCellNum();
					continue;
				}
				if(nullNUm>0) {
					break;
				}
				//获得第ri行，同上，如果此行没有被创建过则抛出异常
				
				List<String> cellInfos = new ArrayList<String>();
				for(int ci=0;ci<cellNum;ci++) {
					//获取第ci个单元格，如果没有被创建过则抛出异常
					cell = row.getCell(ci); 
//					System.out.println(cell.getRichStringCellValue()); 
					//把cell中的内容按字符串方式读取出来，并显示在控制台上
					if(cell != null) {
//						if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
							if(StringUtils.isNotEmpty(getCellValue(cell))) {
								cellInfos.add(getCellValue(cell));
							}
//						}
					}else {
						//可能在行结尾会有非必填项
						cellInfos.add("");
					}
					
				}	
				//cellInfos.size()>1排除text的说明部分
				//添加字段信息
				if(cellInfos.size()>1) {
					//此处可循环遍历该行所有字段，来进行数据表操作
					//可以通过修改两个函数参数，传入工作组的index来判定当前要操作的数据表方法，也可传入工作薄名称判断
					insertInfoFromEt(si,cellInfos);
					//此DEMO为便于展示添加到数组中
					rowInfos.add(cellInfos);
				}else {
					nullNUm++;
				}
			}
			//添加行信息
			if(rowInfos.size()>0){
				stInfos.add(rowInfos);
			}
			
		}
		//添加工作薄信息
		if(stInfos.size()>0) {
			etInfos.add(stInfos);
		}
		
		try {
			ETreadFile.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //记得关闭流 

		System.out.println(etInfos);
	}
	
	/**
	 * 获得cell内容
	 * @param cell
	 * @return ret
	 */
	public String getCellValue(Cell cell) {  
	    String ret;  
	    switch (cell.getCellType()) {  
	    case Cell.CELL_TYPE_BLANK:  
	        ret = "";  
	        break;  
	    case Cell.CELL_TYPE_BOOLEAN:  
	        ret = String.valueOf(cell.getBooleanCellValue());  
	        break;  
	    case Cell.CELL_TYPE_ERROR:  
	        ret = null;  
	        break;  
	    case Cell.CELL_TYPE_FORMULA:  
	        Workbook wb = cell.getSheet().getWorkbook();  
	        CreationHelper crateHelper = wb.getCreationHelper();  
	        FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
	        ret = getCellValue(evaluator.evaluateInCell(cell));  
	        break;  
	    case Cell.CELL_TYPE_NUMERIC:  
	            ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
	        break;  
	    case Cell.CELL_TYPE_STRING:  
	        ret = cell.getRichStringCellValue().getString();  
	        break;  
	    default:  
	        ret = null;  
	    }  
	      
	    return ret; //有必要自行trim  
	}  
	/**
	 * 不同sheet操作的路由方法
	 * 
	 */
	public int insertInfoFromEt (int sheetIndex,List<String> cellInfos ) {
		//是否执行成功
		int seccessNum = 0;
		switch(sheetIndex) {
			case 1:  seccessNum = 1;
					break;  
			case 2:  seccessNum = 1;
					break;  
			case 3:  seccessNum = 1;
					break;  
			case 4:  seccessNum = 1;
					break;  
		}
		return seccessNum;
	}
	
	
	//下面方法为初步设计方法，暂时舍弃不用
	/**
	 * 
	 *commons-fileupload-1.2.2.jar上传功能 
	 *上传并解析ET/xls/xlsx文件内容
	 * @throws Exception 
	 * 
	 *
//	@RequestMapping("/upLoadFile4EtInfo")
	public void upLoadFile4EtInfo(HttpServletRequest request) {

//		ModelAndView mav = new ModelAndView( new MappingJackson2JsonView());
		// Java输入输出临时路径  
//		String sysTemp = System.getProperty("java.io.tmpdir");
		// 用户的主目录 
//		String sysTemp = System.getProperty("user.home"); 
		//传入的文件名（最终保存的文件名称：不含有目录信息）
		String fileName= "";
		//文件后缀（类型）
		String fileType = "";
		//文件名（最终保存的文件名称：不含有目录信息）
//		String Name="";
		//保存路径（不带有文件名的路径）
		//跨平台分隔符--File.separator
//		String path = sysTemp+File.separator;
		//创建存储资源文件夹
//		File f = new File(path);
		//当前使用系统目录，不需要验证是否存在，更不可删除、创建
//		if(!f.exists()){
//			f.mkdirs();
//		}
		List<List<List<List<String>>>> etInfos = new ArrayList<List<List<List<String>>>>();
		
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
			while(iter.hasNext()){
				MultipartFile file = multiRequest.getFile((String)iter.next());
				fileName =  file.getOriginalFilename();
				if(file != null){
					//////////////////////////////////////////////////
					FileInputStream ETreadFile = null;
					//ETreadFile = new FileInputStream("F:/ETExample.et");
					//指定要读取的文件，本例使用之前生成的ETExample.et
					Workbook  wb = null;
					//包装字节流
					try {
						ETreadFile = (FileInputStream) file.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//判断文件名后缀*（文件格式）
					fileType = FilenameUtils.getExtension(fileName);
					if("et".equals(fileType) || "xls".equals(fileType)) {
						try {
							 wb = new HSSFWorkbook(ETreadFile);
							getEtInfoAs03(wb, etInfos, ETreadFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if("xlsx".equals(fileType)) {
						try {
							  wb = new XSSFWorkbook(ETreadFile);
							getEtInfoAs07(wb, etInfos, ETreadFile);
						} catch (IOException e) {
							e.printStackTrace();
						} 
					}
					
					
					
					
					
					
					//////////////////////////////////////////////////
					//创建文件路径
//					fileName =  file.getOriginalFilename();
//					Name = fileName;
//					//根据文件名创建文件
////					File localFile = new File(sysTemp,path);//下同
//					File localFile = new File(f,fileName);
//					//如果已存在同名文件，在文件名称末尾加上（n）
//					if(localFile.exists()){
//						do{
//							String strDate = DateUtil.dateFormatString(new Date(), "yyyyMMddhhmmssSSS");
//							Name =SysUtil.getFilePrefix(SysUtil.getFileName(fileName))+strDate+"."+SysUtil.getFileSuf(fileName);
//							localFile = new File(f,Name);
//						}while(localFile.exists());
//							try {
//								file.transferTo(localFile);
//							} catch (IllegalStateException e) {
//								e.printStackTrace();
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//					//如果不存在同名文件，则直接上传	
//					}else{
//						try {
//							file.transferTo(localFile);
//						} catch (IllegalStateException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}	
				}	
			}	
		}
		
//		//如果已存在该文件
//		File serviceFile = new File(f,Name);
//		if(serviceFile.exists()) {
//			System.out.printf(path+"     "+Name);
//			//将文件存放路径，时间，上传用户，省市，机构，部门入库
//			//异步存放，将消息传回
//			mav.addObject("fileName", fileName);  //文件原始名称
//			mav.addObject("filePath", path); //文件存放位置
//			mav.addObject("newName", Name);    //文件新名称
//			return mav ;
//		}else{
//			return mav;
//		}
		
		 
		
		
	}
	
	
	
	public void getEtInfoAs03(Workbook  wb,List<List<List<List<String>>>> etInfos,InputStream ETreadFile) {
		//获得sheet数量
		int stNum = wb.getNumberOfSheets();
		List<List<List<String>>> stInfos = new ArrayList<List<List<String>>>();
		//循环sheet层
		HSSFSheet st =null;
		for(int si=0;si<stNum;si++) {
			//创建一个工作表对象，从指定的文件流中创建，即上面指定了的文件流
//			XSSFSheet st = wb.getSheet("表格工作表第1页"); 
			//获取名称为"表格工作表第1页"的工作表，可以用getSheetAt(int)方法取得Sheet
			st = (HSSFSheet) wb.getSheetAt(si); 
			HSSFRow row = null;
			//获得Row数量
			int rowNum = 0;
			if(st!=null) {
				rowNum = st.getLastRowNum();
			}
			List<List<String>> rowInfos = new ArrayList<List<String>>();
			
			//屏蔽第一行的说明，但需要获取空行数量，大于一定值(暂定为1)后跳出循环以定位有效数据，从第二行开始读取数据
			int nullNUm = 0;
			//获得cell数量
			int cellNum = 0;
			//循环Row层
			for(int ri=0;ri<rowNum;ri++) {
				row = st.getRow(ri); 
				if(row==null){
					nullNUm++;
				}
				if(ri == 0) {
					cellNum = row.getLastCellNum();
					continue;
				}
				if(nullNUm>0) {
					break;
				}
				//获得第ri行，同上，如果此行没有被创建过则抛出异常
				HSSFCell cell = null;
				List<String> cellInfos = new ArrayList<String>();
				for(int ci=0;ci<cellNum;ci++) {
					//获取第ci个单元格，如果没有被创建过则抛出异常
					cell = row.getCell(ci); 
//					System.out.println(cell.getRichStringCellValue()); 
					//把cell中的内容按字符串方式读取出来，并显示在控制台上
					if(cell != null) {
//						if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
							if(StringUtil.isNotEmpty(getCellValue(cell))) {
								cellInfos.add(getCellValue(cell));
							}
//						}
					}else {
						//可能在行结尾会有非必填项
						cellInfos.add("");
					}
					
				}	
				//cellInfos.size()>1排除text的说明部分
				//添加字段信息
				if(cellInfos.size()>1) {
					//此处可循环遍历该行所有字段，来进行数据表操作
					//可以通过修改两个函数参数，传入工作组的index来判定当前要操作的数据表方法，也可传入工作薄名称判断
					insertInfoFromEt(si,cellInfos);
					//此DEMO为便于展示添加到数组中
					rowInfos.add(cellInfos);
				}else {
					nullNUm++;
				}
			}
			//添加行信息
			if(rowInfos.size()>0){
				stInfos.add(rowInfos);
			}
			
		}
		//添加工作薄信息
		if(stInfos.size()>0) {
			etInfos.add(stInfos);
		}
		
		try {
			ETreadFile.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //记得关闭流 

		System.out.println(etInfos);
	}
	
	public void getEtInfoAs07(Workbook  wb,List<List<List<List<String>>>> etInfos,InputStream ETreadFile) {
		//获得sheet数量
		int stNum = wb.getNumberOfSheets();
		//为展示定义的LIST
		List<List<List<String>>> stInfos = new ArrayList<List<List<String>>>();
		//循环sheet层
		XSSFSheet st =null;
		for(int si=0;si<stNum;si++) {
			//创建一个工作表对象，从指定的文件流中创建，即上面指定了的文件流
//			XSSFSheet st = wb.getSheet("表格工作表第1页"); 
			//获取名称为"表格工作表第1页"的工作表，可以用getSheetAt(int)方法取得Sheet
			st = (XSSFSheet) wb.getSheetAt(si); 
			
			XSSFRow row = null;
			//获得Row数量
			int rowNum = 0;
			if(st!=null) {
				rowNum = st.getLastRowNum();
			}
			List<List<String>> rowInfos = new ArrayList<List<String>>();

			//屏蔽第一行的说明，但需要获取空行数量，大于一定值(暂定为1)后跳出循环以定位有效数据，从第二行开始读取数据
			int nullNUm = 0;
			//获得cell数量
			int cellNum = 0;
			//循环Row层
			for(int ri=0;ri<rowNum;ri++) {
				row = st.getRow(ri); 
				if(row==null){
					nullNUm++;
				}
				if(ri == 0) {
					cellNum = row.getLastCellNum();
					continue;
				}
				if(nullNUm>0) {
					break;
				}
				//获得第ri行，同上，如果此行没有被创建过则抛出异常
				XSSFCell cell = null;
				//获得Row数量
				List<String> cellInfos = new ArrayList<String>();
				for(int ci=0;ci<cellNum;ci++) {
					//获取第ci个单元格，如果没有被创建过则抛出异常
					cell = row.getCell(ci); 
//					System.out.println(cell.getRichStringCellValue()); 
					//把cell中的内容按字符串方式读取出来，并显示在控制台上
					if(cell != null) {
//						if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
							if(StringUtil.isNotEmpty(getCellValue(cell))) {
								cellInfos.add(getCellValue(cell));
							}
//						}
					}else {
						//可能在行结尾会有非必填项
						cellInfos.add("");
					}
					
				}	
				//cellInfos.size()>1排除text的说明部分
				//添加字段信息
				if(cellInfos.size()>1) {
					//此处可循环遍历该行所有字段，来进行数据表操作
					//可以通过修改两个函数参数，传入工作组的index来判定当前要操作的数据表方法，也可传入工作薄名称判断
					insertInfoFromEt(si,cellInfos);
					//此DEMO为便于展示添加到数组中
					rowInfos.add(cellInfos);
				}else {
					nullNUm++;
				}
			}
			//添加行信息
			if(rowInfos.size()>0){
				stInfos.add(rowInfos);
			}
			
		}
		//添加工作薄信息
		if(stInfos.size()>0) {
			etInfos.add(stInfos);
		}
		
		try {
			ETreadFile.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //记得关闭流 

		System.out.println(etInfos);
	}
	*/
	
	
	
	//通过Servlet程序实现下载
  	public void downLoadFile4EtInfo03Or07( HttpServletResponse response) {
  		
  			String filename ="text导出文件.xls";
  			Workbook wb = new HSSFWorkbook(); //创建一个空白的工作簿对象
  			Sheet st = wb.createSheet("表格工作表第1页"); //基于上面的工作簿创建属于此工作簿的工作表名为"表格工作表第1页"
  			Row row = st.createRow(0); //创建属于上面工作表的第1行，参数从0开始可以是0～65535之间的任何一个
  			
	  		for(int i = 0;i < 10;i++){  
	            Cell cell = row.createCell(i);  //创建属于上面行的单元格，参数从0开始可以是0～255之间的任何一个，
	  			cell.setCellType(Cell.CELL_TYPE_STRING); //设置此单元格的格式为文本，此句可以省略，WPS表格会自动识别。
	  			cell.setCellValue("测试数据"+i);   //直接在上面定义好的单元格输入内容("我是表格内容")，
	        }   
//  			FileOutputStream ETFile;
//  			try {
//  			//创建一个文件输出流，指定到F盘根目录下的ETExample.et空白的表格文件
//				ETFile = new FileOutputStream("F:/ETExample.et");
//				//把工作表内容写到流里
//				
//				ETFile.close(); //记得手动关闭流，POI不关闭用户打开的流。
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//  			catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
  		
  		
          try {
              // path是指欲下载的文件的路径。
              //File file = new File(path);
              // 取得文件名。
              //String filename = file.getName();
              // 以流的形式下载文件。
             // InputStream fis = new BufferedInputStream(new FileInputStream(path));
              //byte[] buffer = new byte[fis.available()];
              //fis.read(buffer);
              //fis.close();
              // 清空response
              response.reset();
              // 设置response的Header
//              response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
              response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));//解决中文问题
              OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
              response.setContentType("application/octet-stream");
              //toClient.write(buffer);
              wb.write(toClient);
              toClient.flush();
              toClient.close();
          } catch (IOException ex) {
              ex.printStackTrace();
          }
         // return response;
      }
	
}
