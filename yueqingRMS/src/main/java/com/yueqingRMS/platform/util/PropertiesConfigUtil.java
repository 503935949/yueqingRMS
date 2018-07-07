package com.yueqingRMS.platform.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：PropertiesConfigUtil   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月10日 下午1:59:02   
* 修改人：   
* 修改时间：2017年8月10日 下午1:59:02   
* 修改备注：   
* @version    
*    
*/
/**   
*    
* 项目名称：yueqingRMS   
* 类名称：PropertiesConfigUtil   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月10日 下午2:00:17   
* 修改人：   
* 修改时间：2017年8月10日 下午2:00:17   
* 修改备注：   
* @version    
*    
*/
/**   
*    
* 项目名称：yueqingRMS   
* 类名称：PropertiesConfigUtil   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月10日 下午2:00:21   
* 修改人：   
* 修改时间：2017年8月10日 下午2:00:21   
* 修改备注：   
* @version    
*    
*/

public class PropertiesConfigUtil {

	
	/**
	 * 项目资源文件路径
	 */
	public static final String PROPERTIES_DEFAULT = "database.properties";//类路径下的属性文件名
	
    /**
     * 构造函数
     */
    public PropertiesConfigUtil() {
    }

    /**
     * 定义文件对象
     */
    private static Properties props = new Properties();
    
    /**
     * 读入数据（初始化数据）
     */
    static {
    	 initOrRefresh();
    }

    
    /**
     * 初始化或更新缓存
     */
    public static void initOrRefresh(){
    	try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_DEFAULT));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	//刷新邮件功能的初始化设置
    	MailConfigInfo.initOrRefresh();
    }
    
    /**
     * 简单获取值
     */
    public static String getValue(String key) {
        return props.getProperty(key);
    }

    /**
     * 获取值（int）
     */
    public static int getIntValue(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    /**
     * 获取值（Long）
     */
    public static long getLongValue(String key) {
        return Long.parseLong(props.getProperty(key));
    }

    /**
     * 获取值（boolean）
     */
    public static boolean getBooleanValue(String key) {
        return Boolean.valueOf(props.getProperty(key));
    }
    
    
    /**
     * 获取值并抛出可能异常
     */
    public static String getProperty(String key) throws IOException {
        return getProperty(PropertiesConfigUtil.PROPERTIES_DEFAULT,key);
    }
    
    /**
     * 设置单个值并抛出可能异常
     */
    public static Object setProperty(String propertyName,String propertyValue) throws URISyntaxException, IOException {
        return setProperty(PropertiesConfigUtil.PROPERTIES_DEFAULT,propertyName,propertyValue);
    }
    
    /**
     * 设置多个值并抛出可能异常
     */
    public static void setProperties(Set<Entry<String, Object>> data) throws IOException, URISyntaxException{
        setProperties(PropertiesConfigUtil.PROPERTIES_DEFAULT,data);
    }
    
    /**
     * 读取Properties的全部信息并抛出可能异常
     */
    public static Map<String,String> getAllProperties() throws IOException {
//        Properties props = new Properties();
//        InputStream in =MailPropertiesConfigUtil.class.getClassLoader().getResourceAsStream(PropertiesConfigUtil.PROPERTIES_DEFAULT);
//        props.load(in);
//        in.close();
        Enumeration<?> en = props.propertyNames(); // 得到配置文件的名字
        Map<String,String> map = new HashMap<String,String>();
        while (en.hasMoreElements()) {
            String strKey =  en.nextElement().toString();
            map.put(strKey,props.getProperty(strKey));
        }
        return map;
    }
    
    /**
     * 获取值并抛出可能异常
     * @param filePath
     * @param key
     * @return
     * @throws IOException
     */
    public static String getProperty(String filePath,String key) throws IOException {
        Properties props = new Properties();
        InputStream in = PropertiesConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        props.load(in);
        in.close();
        return props.getProperty(key);
    }
    
    
    /**
     * 设置单个值并抛出可能异常
     * @param filePath
     * @param propertyName
     * @param propertyValue
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static Object setProperty(String filePath,String propertyName,String propertyValue) throws URISyntaxException, IOException {
        Properties props=new Properties();
        InputStream in = PropertiesConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        props.load(in);//
        in.close();
        Object o = props.setProperty(propertyName,propertyValue);//设置属性值，如属性不存在新建
        OutputStream out=new FileOutputStream(new File(PropertiesConfigUtil.class.getClassLoader().getResource(PropertiesConfigUtil.PROPERTIES_DEFAULT).toURI()));//输出流
        props.store(out,"modify");//设置属性头，如不想设置，请把后面一个用""替换掉
        out.flush();//清空缓存，写入磁盘
        out.close();//关闭输出流
        initOrRefresh();//刷新缓存
        return o;
    }
    
    
    /**
     * 设置多个值并抛出可能异常
     * @param filePath
     * @param data
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void setProperties(String filePath,Set<Entry<String, Object>> data) throws IOException, URISyntaxException{
        Properties props=new Properties();
        InputStream in = PropertiesConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        props.load(in);//
        in.close();
        for ( Entry<String,Object> entry : data) { //先遍历整个 people 对象  
        	props.setProperty( entry.getKey(),entry.getValue().toString());//设置属性值，如属性不存在新建
        }  
        OutputStream out=new FileOutputStream(new File(PropertiesConfigUtil.class.getClassLoader().getResource(PropertiesConfigUtil.PROPERTIES_DEFAULT).toURI()));//输出流
        props.store(out,"modify");//设置属性头，如不想设置，请把后面一个用""替换掉
        out.flush();//清空缓存，写入磁盘
        out.close();//关闭输出流
        initOrRefresh();//刷新缓存
    }
    
    
    
}
