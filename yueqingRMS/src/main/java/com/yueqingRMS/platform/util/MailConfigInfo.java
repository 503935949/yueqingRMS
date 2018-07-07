package com.yueqingRMS.platform.util;

import java.io.InputStream;
import java.util.Properties;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：MailConfigInfo   
* 类描述：  使用spring自带邮件封装时，可通过资源文件中的数据进行配置邮差信息等，通过本类获取已有数据
* 创建人：林曌   
* 创建时间：2017年8月10日 下午2:17:41   
* 修改人：   
* 修改时间：2017年8月10日 下午2:17:41   
* 修改备注：   
* @version    
*    
*/
public class MailConfigInfo {

	    //mail
	    public static String mail_host;
	    public static int mail_port;
	    public static String mail_from;
	    public static String mail_username;
	    public static String mail_password;
	    public static int mail_smtp_timeout;
	    static{
	        initOrRefresh();
	    }
	    //初始化或更新缓存
	    public static void initOrRefresh(){
	        Properties p=new Properties();
	        try {
	            InputStream in = MailConfigInfo.class.getClassLoader().getResourceAsStream(PropertiesConfigUtil.PROPERTIES_DEFAULT);
	            p.load(in);
	            in.close();
	            mail_host = p.getProperty("mail_host","smtp.qq.com");
	            mail_port = Integer.parseInt(p.getProperty("mail_port","465"));
	            mail_from = p.getProperty("mail_from");
	            mail_username = p.getProperty("mail_username");
	            mail_password = p.getProperty("mail_password");
	            mail_smtp_timeout = Integer.parseInt(p.getProperty("mail_smtp_timeout","25000"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
