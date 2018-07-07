package com.yueqingRMS.platform.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import net.sf.cglib.beans.BeanGenerator;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：SysUtil   
* 类描述：   系统工具类
* 创建人：林曌   
* 创建时间：2017年8月10日 下午5:19:19   
* 修改人：   
* 修改时间：2017年8月10日 下午5:19:19   
* 修改备注：   
* @version    
*    
*/
public class SysUtil {
	
	
	

	
	/**
	 * 转换ajax提交的中文参数乱码   
	 * @param str
	 * @return   
	*/
	public static String converAjaxChn(HttpServletRequest request,String str){
		String encodeing = request.getCharacterEncoding();
		if (null == encodeing) {
			encodeing = "UTF-8";
		}
		try {
			if (null != str && !"".equals(str)) {
				str = new String(str.getBytes(encodeing), "UTF-8");
				str = URLDecoder.decode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		return str;
	}
	
	
	
	/**
	 *  转译非法字符至html
	 * @param str
	 * @return   
	*/
	public static String repaceValidInput(String str){
		String result = "";
		if(str != null && !"".equals(str.trim())){
			result = str.trim().replace("&#38;","&").replace("&lt","<").replace("&gt",">").replace("&#39","'");
		}
		return result;
	}
	
	/**
	 *  转译由用户输入的非法字符
	 * @param str
	 * @return   
	*/
	public static String tirmValidInput(String str){
		String result = "";
		if(str != null && !"".equals(str.trim())){
			result = str.trim().replace("&","&#38;").replace("<","&lt").replace(">","&gt").replace("'", "&#39");
		}
		return result;
	}

	/**
	 * lass<T> beanClass可以接受任何类型的javaBean,使用泛型调用者不用进行强转
	 * @param <T>
	 * @param request
	 * @param beanClass
	 * @return   
	*/
	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Title: createObject @Description: 根据类路径创建实例 @param @param
	 *         url @param @return @param @throws Exception 设定文件 @return Object
	 *         返回类型 @throws
	 */
	public static Object createObject(String url) throws Exception {
		Object o = (Object) Class.forName(url).newInstance();
		return o;
	}

	/**
	 * @Title: formatData @Description: 将double类型转换为指定格式 @param @param
	 *         pattern @param @param source @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	public static String formatData(String pattern, double source) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(source);
	}

	/**
	 * @Title: todouble @Description: 将字符串转化为double类型 （默认值为 0.0） @param @param o
	 *         需要转化的字符串对象 @param @return 设定文件 @return double 返回类型 @throws
	 */
	public static double todouble(Object o) {
		if (o != null) {
			return Double.parseDouble(o.toString());
		} else {
			return 0.0;
		}
	}

	/**
	 * @Title: tolong @Description: 将字符串转化为long类型 （默认值为 0） @param @param o
	 *         需要转化的字符串对象 @param @return 设定文件 @return long 返回类型 @throws
	 */
	public static long tolong(Object o) {
		if (o != null) {
			return Long.parseLong(o.toString());
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * <br/>
	 * 方法描述:将字符串转化为int类型 （默认值为 0）
	 * 
	 * @param o
	 *            需要转化的字符串对象
	 * @return int 数字
	 */
	public static int toint(Object o) {
		if (o != null) {
			return Integer.parseInt(o.toString());
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * <br/>
	 * 方法描述:判断对象是否为空
	 * 
	 * @param o
	 *            检测的对象
	 * @return boolean 表示为空 false 表示不为空
	 */
	public static boolean isNull(Object o) {
		if (o == null || "".equals(o) || "null".equals(o)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <br/>
	 * 方法描述:判断对象是否不为空
	 * 
	 * @param o
	 *            检测的对象
	 * @return boolean 表示为不为空 false 表示为空
	 */
	public static boolean isNotNull(Object o) {
		return !isNull(o);
	}

	/**
	 * 
	 * <br/>
	 * 方法描述:字符编码转换：从iso转化成Utf8
	 * 
	 * @param source
	 *            源字符串
	 * @return String 编码后的字符串
	 */
	public static String isoToUtf8(String source) {
		String target = "";
		try {
			target = new String(source.getBytes("iso-8859-5"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 
	 * <br/>
	 * 方法描述:字符编码转换：从iso转化成GBK
	 * 
	 * @param source
	 *            源字符串
	 * @return String 编码后的字符串
	 */
	public static String isoToGBK(String source) {
		String target = "";
		try {
			target = new String(source.getBytes("iso-8859-5"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 
	 * <br/>
	 * 方法描述:字符编码转换：从iso转化成GB2312
	 * 
	 * @param source
	 *            源字符串
	 * @return String 编码后的字符串
	 */
	public static String isoToGB2312(String source) {
		String target = "";
		try {
			target = new String(source.getBytes("iso-8859-5"), "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 基于map 动态创建bean
	 * 
	 * @param queryMap
	 *            查询属性封装 eg ： {eqName :int}
	 * @return Object
	 */
	public static Object createDynamicBean(Map<?, ?> queryMap) {
		Iterator<?> iterQuery = queryMap.keySet().iterator();// 初始化bean 主要是处理int
		// double的默认值
		BeanGenerator bg = new BeanGenerator(); // 基于配置信息，利用cglib，动态创建类
		Map<String, Integer> defaltSetMap = new HashMap<String, Integer>();// 处理int double
		// 因为基于cglib，创建bean的时候，会自动赋值为0
		while (iterQuery.hasNext()) {
			String key = (String) iterQuery.next();
			String name = key;
			String type = ((String) queryMap.get(key)).toLowerCase();
			if ("int".equals(type)) {
				bg.addProperty(name, Integer.TYPE);
				defaltSetMap.put(name, new Integer(-111111));// 设置-111111，
				// 作为整型的默认值
				continue;
			}
			if ("double".equals(type)) {
				bg.addProperty(name, Double.TYPE);
				defaltSetMap.put(name, new Integer(-222222));// 设置-222222，
				// 作为浮点型的默认值
				continue;
			}
			if ("string".equals(type)) {
				bg.addProperty(name, String.class);
				continue;
			}
			if ("list".equals(type)) {
				bg.addProperty(name, Collection.class);
				continue;
			}
		}
		Object bean = bg.create();// 动态创建查询对象实例
		Iterator<?> iter = defaltSetMap.keySet().iterator();// 初始化bean 主要是处理int
		// double的默认值
		while (iter.hasNext()) {
			String key = (String) iter.next();
			try {
				PropertyUtils.setProperty(bean, key, defaltSetMap.get(key));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bean;
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 依照指定属性集合排序
	 * 
	 * @param collection
	 *            需要排序的集合
	 * @param sortCol
	 *            排序字段
	 * @param isAsc
	 *            true 升序 false 降序
	 * @return List
	 */
	public static List<Object> sortCollection(List<Object> collection, String sortCol, boolean isAsc) {
		for (int i = 0; i < collection.size(); i++) {
			for (int j = i + 1; j < collection.size(); j++) {
				BeanWrapper bwi = new BeanWrapperImpl(collection.get(i));
				BeanWrapper bwj = new BeanWrapperImpl(collection.get(j));
				int leftI = (Integer) bwi.getPropertyValue(sortCol);
				int leftJ = (Integer) bwj.getPropertyValue(sortCol);
				if (isAsc) {
					if (leftI > leftJ) {
						Object obj = collection.get(j);
						collection.set(j, collection.get(i));
						collection.set(i, obj);
					}
				} else {
					if (leftI < leftJ) {
						Object obj = collection.get(j);
						collection.set(j, collection.get(i));
						collection.set(i, obj);
					}
				}
			}
		}
		return collection;
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 解析配置文件 ，得到的是一个Map 配置文件的写法如下: priceRule.subject.type.size=3
	 * priceRule.subject.type.0=0,\u5206\u652f\u79d1\u76ee
	 * priceRule.subject.type.1=1,\u586b\u62a5\u79d1\u76ee
	 * priceRule.subject.type.2=2,\u5206\u644a\u79d1\u76ee
	 * 
	 * @param fileName
	 *            要解析的文件全名，没有后缀名
	 * @param key
	 *            表示大小的key
	 * @return Map<String,String>
	 */
	public static Map<String, String> parsePropertiesReturnMap(String fileName, String key) {
		Map<String, String> map = new TreeMap<String, String>();
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		String value = rb.getString(key);
		int count = 0;
		if (null != value) {
			count = Integer.parseInt(value);
		}
		for (int i = 0; i < count; i++) {
			String subKey = key.replaceAll("size", (i + ""));
			map.put(rb.getString(subKey).split(",")[0], rb.getString(subKey).split(",")[1]);
		}
		return map;
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 解析配置文件 ，得到的是一个List 配置文件的写法如下: priceRule.subject.type.size=3
	 * priceRule.subject.type.0=0,\u5206\u652f\u79d1\u76ee
	 * priceRule.subject.type.1=1,\u586b\u62a5\u79d1\u76ee
	 * priceRule.subject.type.2=2,\u5206\u644a\u79d1\u76ee
	 * 
	 * @param fileName
	 *            要解析的文件全名，没有后缀名
	 * @param key
	 *            表示大小的key
	 * @return List<PropertiesItem>
	 */
	public static List<PropertiesItem> parsePropertiesReturnList(String fileName, String key) {
		List<PropertiesItem> list = new ArrayList<PropertiesItem>();
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		String value = rb.getString(key);
		int count = 0;
		if (null != value) {
			count = Integer.parseInt(value);
		}
		for (int i = 0; i < count; i++) {
			String subKey = key.replaceAll("size", (i + ""));
			PropertiesItem item = new PropertiesItem();
			item.setId(rb.getString(subKey).split(",")[0]);
			item.setName(rb.getString(subKey).split(",")[1]);
			list.add(item);
		}
		return list;
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 解析出配置文件。返回一个str
	 * 
	 * @param fileName
	 *            要解析的文件全名，没有后缀名
	 * @param key
	 *            key
	 * @return String
	 */
	public static String parsePropertiesReturnStr(String fileName, String key) {
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		return rb.getString(key);
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 解析出配置文件。返回一个str,逗号右边的
	 * 
	 * @param fileName
	 *            要解析的文件全名，没有后缀名
	 * @param key
	 *            key
	 * @return String
	 */
	public static String parsePropertiesReturnStrAfterComma(String fileName, String key) {
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		return rb.getString(key).split(",")[1];
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 解析出配置文件。返回一个str,逗号左边的
	 * 
	 * @param fileName
	 *            要解析的文件全名，没有后缀名
	 * @param key
	 *            key
	 * @return String
	 */
	public static String parsePropertiesReturnStrBeforeComma(String fileName, String key) {
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		return rb.getString(key).split(",")[0];
	}

	/**
	 * 
	 * <br/>
	 * 方法描述:获取uuid去掉“-” <br/>
	 * xlp
	 * 
	 * @return String
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	
	/**
	 * 判断字符串是否是数字
	 * @param numStr
	 * @return
	 */
	public static boolean isNumber(String numStr) {
		Pattern pattern = Pattern.compile("[0-9]*"); // Character.isDigit(char);
														// or ascii码
		return pattern.matcher(numStr).matches();
	}

	/**
	 * 判断字符串是否是整形
	 * @param value
	 * @return
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	
	/**
	 * 判断字符串是否是双精度浮点数
	 * @param value
	 * @return
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	
	public static boolean isInteger4Reg(String value) {
		// "^[0-9]*(\.[0])?$";
		Pattern pattern = Pattern.compile("^[0-9]*(\\.[0])?$"); // Character.isDigit(char);
																// or ascii码
		return pattern.matcher(value).matches();
	}

	/**
	 * 
	 * <br/>
	 * 方法描述: 按数据库总记录数与每页记录数计算出总页数
	 * 
	 * @param logCount
	 *            数据库的总页数
	 * @param pageSize
	 *            页面显示时的第页的记录数
	 * @return int 返回此次查询的总页数
	 */
	public static int calculateTotalPage(int logCount, int pageSize) {
		if (logCount % pageSize == 0) {
			return logCount / pageSize;
		} else {
			return (logCount / pageSize) + 1;
		}
	}

	// 将一整型数值转化为大写字符串,如将"123"转化为"一百二十三"
	public static String changeNum(int oldNumber) {
		String newNumber = "";
		char[] temp = String.valueOf(oldNumber).toCharArray();

		for (int i = 0; i < temp.length; i++) {
			boolean sign = true;
			switch (temp[i]) {
			case '1':
				newNumber += "一";
				break;
			case '2':
				newNumber += "二";
				break;
			case '3':
				newNumber += "三";
				break;
			case '4':
				newNumber += "四";
				break;
			case '5':
				newNumber += "五";
				break;
			case '6':
				newNumber += "六";
				break;
			case '7':
				newNumber += "七";
				break;
			case '8':
				newNumber += "八";
				break;
			case '9':
				newNumber += "九";
				break;
			case '0':
				if (i != temp.length - 1) {
					if (temp[i + 1] == '0') {
						sign = false;
						break;
					} else {
						newNumber += "零";
						break;
					}
				} else
					break;
			}
			if (!newNumber.endsWith("零") && sign == true) {

				switch (temp.length - i) {
				case 12:
					newNumber += "千";
					break;
				case 11:
					newNumber += "百";
					break;
				case 10:
					newNumber += "十";
					break;

				case 8:
					newNumber += "千";
					break;
				case 7:
					newNumber += "百";
					break;
				case 6:
					newNumber += "十";
					break;

				case 4:
					newNumber += "千";
					break;
				case 3:
					newNumber += "百";
					break;
				case 2:
					newNumber += "十";
					break;
				}
			}
			switch (temp.length - i) {
			case 9:
				newNumber += "亿";
				break;
			case 5:
				newNumber += "万";
				break;
			}
		}
		if (newNumber.startsWith("一十")) {
			newNumber = newNumber.replaceFirst("一十", "十");
		}
		newNumber = newNumber.replaceAll("亿万", "亿");
		newNumber = newNumber.replaceAll("零万", "万");
		return newNumber;
	}

	// 类似js中的join使用 比如说 ： List(^) --->> xxx^yyy^
	public static String join(Collection<String> s, String delimiter) {
		if (s.isEmpty())
			return "";
		Iterator<String> iter = s.iterator();
		StringBuffer buffer = new StringBuffer(iter.next());
		while (iter.hasNext())
			buffer.append(delimiter).append(iter.next());
		return buffer.toString();
	}

	/**
	 * 将输入流转为字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 去除集合中的Null对象
	 * 
	 * @param collection
	 *            原集合对象
	 * @return
	 */
	public static Collection<?> removeNullObject4Collection(Collection<?> collection) {
		if (collection == null)
			return collection;
		Iterator<?> iterator = collection.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			if (o == null)
				iterator.remove();
		}
		return collection;
	}

	/**
	 * @Title: formatSplitStr @Description: TODO(这里用一句话描述这个方法的作用) @param @param
	 * src @param @param step @param @param format @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	public static String formatSplitStr(String src, int step, String format) {
		if (isNull(src))
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < src.length(); i++) {
			if (i % step == 0) {
				int end = (i + step) > src.length() ? src.length() : (i + step);
				sb.append(src.substring(i, end) + format);
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 把clob转换成string @Title: oracleClob2Str @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @param clob @param @return @param @throws
	 * Exception 设定文件 @return String 返回类型 @throws
	 */
	public static String oracleClob2Str(Clob clob) throws Exception {
		return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
	}

	/**
	 * 判断列表是否为空
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isListEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * 将Number对象转换为Long类型
	 */
	public static Long ObjectToNumber(Object obj) {
		Number number = (Number) obj;
		return number.longValue();
	}

	/**
	 * 
	 * @Title: Copy @Description: copy entity @param @param source @param @param
	 * dest @param @throws Exception 设定文件 @return void 返回类型 @throws
	 */
	public static void Copy(Object source, Object dest) throws Exception {
		// 获取属性
		BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
		PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

		BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
		PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

		try {
			for (int i = 0; i < sourceProperty.length; i++) {

				for (int j = 0; j < destProperty.length; j++) {

					if (sourceProperty[i].getName().equals(destProperty[j].getName())) {
						// 调用source的getter方法和dest的setter方法
						destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("属性复制失败:" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: getIntervalDays @Description: 时间间隔 (分钟) @param @param
	 * startday @param @param endday @param @return 设定文件 @return int
	 * 返回类型 @throws
	 */
	public static double getIntervalDays(Date startday, Date endday) {
		if (startday == null || endday == null) {
			return 0;
		}
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		double sl = startday.getTime();
		double el = endday.getTime();
		double ei = el - sl;
		return ei / (1000 * 60);
	}
	
	
	
	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}