package com.yueqingRMS.platform.freemarker;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;

import com.yueqingRMS.platform.util.PropertiesConfigUtil;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：FreeMarkerTemplateFactory   
* 类描述：   
* 创建人：林曌   
* 创建时间：2018年8月27日 下午5:53:35   
* 修改人：   
* 修改时间：2018年8月27日 下午5:53:35   
* 修改备注：   
* @version    
*    
*/
public class FreeMarkerTemplateFactory {
	
	private static final Configuration CONFIG= new Configuration(Configuration.VERSION_2_3_23);
	
	private static final String PROFIX = "/";
	
	private static final String SUFFIX = ".ftl";
	
	private static final ConcurrentHashMap<String, Template> TEMPLATE_CACHE = new ConcurrentHashMap<String, Template>();
	
	private static String BASE_PATH = "/template";
	
	private static final int MAXLENGTH = 2048;
	
	static {
		init();
	}

	private static void init() {
		
		String path = PropertiesConfigUtil.getValue("email.template.path");
		if(!StringUtils.isBlank(path)){
			BASE_PATH = path;
		}
		if(!BASE_PATH.startsWith(PROFIX)){
			BASE_PATH = PROFIX + BASE_PATH;
		}
		ServletContext servletContext =  ContextLoader.getCurrentWebApplicationContext().getServletContext();
		CONFIG.setServletContextForTemplateLoading(servletContext, BASE_PATH);
		
	}
	
	
	/**
	 * @param name
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 */
	private static Template geTemplate (String name) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException{
		if(TEMPLATE_CACHE.containsKey(name)){
			return TEMPLATE_CACHE.get(name);
		}else{
			Template template = null;
			template = CONFIG.getTemplate(name);
			TEMPLATE_CACHE.put(name, template);
			return template;
		}
	}
	
	
	/**
	 * @param name
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 */
	public static Template getEmailTemplate(String name) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException{
		if(StringUtils.isBlank(name)){
			return null;
		}
		if(!name.endsWith(SUFFIX)){
			name = name + SUFFIX;
		}
		return geTemplate(name);
	}
	
	/**
	 * @param templateName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static String getTemplateOnString(String templateName,Object args) throws Exception{
		Template  template = FreeMarkerTemplateFactory.getEmailTemplate(templateName);
		Map<String, Object> argsmap = new HashMap<String,Object>();
		if(!(args instanceof Map)){
			argsmap = FreeMarkerTemplateFactory.beanToMap(args);
		}else{
			argsmap = (Map<String, Object>) args;
		}
		Writer out = new StringWriter(MAXLENGTH);
		template.process(argsmap, out);
		return out.toString();
	}
	
	//bean转换为Map
	private static Map<String , Object> beanToMap(Object obj){
		if(obj==null) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor p: propertyDescriptors){
				String key = p.getName();
				if(!key.equals("class")){
					Method getter = p.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key,value);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	

}
