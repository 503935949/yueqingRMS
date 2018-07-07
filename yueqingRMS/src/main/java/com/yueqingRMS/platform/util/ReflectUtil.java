package com.yueqingRMS.platform.util;

import java.lang.reflect.Field;

import org.apache.commons.lang.reflect.FieldUtils;  

  
/**   
*    
* 项目名称：yueqingRMS   
* 类名称：ReflectHelper   
* 类描述： 利用反射进行操作的一个工具类  
* 创建人：林曌   
* 创建时间：2017年8月28日 上午10:34:44   
* 修改人：   
* 修改时间：2017年8月28日 上午10:34:44   
* 修改备注：   
* @version    
*    
*/
public class ReflectUtil {  
      
	
	/**
     * 利用反射获取指定对象的指定属性
     * 
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    public static Object getFieldValue(Object obj , String fieldName ){  
          
        if(obj == null){  
            return null ;  
        }  
          
        Field targetField = getTargetField(obj.getClass(), fieldName);  
          
        try {  
            return FieldUtils.readField(targetField, obj, true ) ;  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }   
        return null ;  
    }  
      
    /**
     * 利用反射获取指定对象里面的指定属性
     * 
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    public static Field getTargetField(Class<?> targetClass, String fieldName) {  
        Field field = null;  
  
        try {  
            if (targetClass == null) {  
                return field;  
            }  
  
            if (Object.class.equals(targetClass)) {  
                return field;  
            }  
  
            field = FieldUtils.getDeclaredField(targetClass, fieldName, true);  
            if (field == null) {  
                field = getTargetField(targetClass.getSuperclass(), fieldName);  
            }  
        } catch (Exception e) {  
        }  
  
        return field;  
    }  
      
    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * 
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @param value 目标值
     */
    public static void setFieldValue(Object obj , String fieldName , Object value ){  
        if(null == obj){return;}  
        Field targetField = getTargetField(obj.getClass(), fieldName);    
        try {  
             FieldUtils.writeField(targetField, obj, value) ;  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }   
    }   
}  
