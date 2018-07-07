package com.yueqingRMS.platform.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException; 
  
public class SpringContextHolder implements ApplicationContextAware, DisposableBean  {// extends ApplicationObjectSupport{  
  
	 private static ApplicationContext applicationContext;

	    /**
	     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	     */
	    public void setApplicationContext(ApplicationContext applicationContext) {
	    	SpringContextHolder.applicationContext = applicationContext; // NOSONAR
	    }

	    /**
	     * 取得存储在静态变量中的ApplicationContext.
	     */
	    public static ApplicationContext getApplicationContext() {
	        checkApplicationContext();
	        return applicationContext;
	    }

	    /**
	     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	     */
	    @SuppressWarnings("unchecked")
	    public static <T> T getBean(String name) {
	        checkApplicationContext();
	        return (T) applicationContext.getBean(name);
	    }

	    /**
	     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	     */
	    public static <T> T getBean(Class<T> clazz) {
	        checkApplicationContext();
	        return (T) applicationContext.getBean(clazz);
	    }

	    /***
	     * 类似于getBean(String name)只是在参数中提供了需要返回到的类型。
	     *
	     * @param name
	     * @param requiredType
	     * @return
	     * @throws BeansException
	     */
	    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
	        return applicationContext.getBean(name, requiredType);
	    }

	    /**
	     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	     *
	     * @param name
	     * @return
	     * @throws NoSuchBeanDefinitionException
	     */
	    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
	        return applicationContext.getAliases(name);
	    }

	    /**
	     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	     *
	     * @param name
	     * @return boolean
	     * @throws NoSuchBeanDefinitionException
	     */
	    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
	        return applicationContext.isSingleton(name);
	    }

	    /**
	     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	     *
	     * @param name
	     * @return boolean
	     */
	    public static boolean containsBean(String name) {
	        return applicationContext.containsBean(name);
	    }

	    /**
	     * 清除applicationContext静态变量.
	     */
	    public static void cleanApplicationContext() {
	        applicationContext = null;
	    }

	    private static void checkApplicationContext() {
	        if (applicationContext == null) {
	            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
	        }
	    }

	    public static String getRootRealPath() {
	        String rootRealPath = "";
	        try {
	            rootRealPath = getApplicationContext().getResource("").getFile().getAbsolutePath();
	        } catch (IOException e) {
	            //获取系统根目录失败
	        }
	        return rootRealPath;
	    }

	    public static String getResourceRootRealPath() {
	        String rootRealPath = "";
	        try {
	            rootRealPath = new DefaultResourceLoader().getResource("").getFile().getAbsolutePath();
	        } catch (IOException e) {
	            //获取系统根目录失败
	        }
	        return rootRealPath;
	    }

	    @Override
	    public void destroy() throws Exception {
	    	SpringContextHolder.cleanApplicationContext();
	    }
}  