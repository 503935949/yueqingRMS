package com.yueqingRMS.platform.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 获得时间函数
	 * @param dateFormat
	 * @return
	 */
	public static String getNow(String dateFormat) {
		
		// 获取时间日期格式
		if(StringUtils.isNull(dateFormat)) {
			dateFormat = "yyyy-MM-dd" ;
		}
		// 取当前日期。
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		//返回格式时间
		return format.format(cal.getTime());
	}
	
	
	/**
	 * 两个string类型的日期比较大小
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compare_date(String DATE1, String DATE2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	
	
	
	/**
	 * 返回两个string类型日期之间相差的天数
	 * @param smdate
	 * @param bdate
	 * @return
	 */
	public static int daysBetween(String smdate,String bdate){  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        long time1 = 0;
        long time2 = 0;
        
        try{
             cal.setTime(sdf.parse(smdate));   
             time1 = cal.getTimeInMillis();    
             cal.setTime(sdf.parse(bdate)); 
             time2 = cal.getTimeInMillis();  
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }
	
	
	
	/**
	 * 返回两个string类型日期相差的小时数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int daysBetween2(String startTime, String endTime) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");  
        Calendar cal = Calendar.getInstance();    
        long time1 = 0;
        long time2 = 0;
        
        try{
             cal.setTime(sdf.parse(startTime));   
             time1 = cal.getTimeInMillis();    
             cal.setTime(sdf.parse(endTime)); 
             time2 = cal.getTimeInMillis();  
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }
	
	
}
