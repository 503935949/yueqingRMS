/*
 * 
 * 
 * 
 */
package com.yueqingRMS.platform.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;

/**   
*    
* 项目名称：yueqingRMS   
* 类名称：JsonUtils   
* 类描述：   Utils - JSON
* 创建人：林曌   
* 创建时间：2017年8月10日 下午3:19:03   
* 修改人：   
* 修改时间：2017年8月10日 下午3:19:03   
* 修改备注：   
* @version    
*    
*/
public final class JsonUtils {

	/** ObjectMapper */
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 不可实例化
	 */
	private JsonUtils() {
	}

	/**
	 * 将对象转换为JSON字符串
	 * 
	 * @param value
	 *            对象
	 * @return JSOn字符串
	 */
	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param valueType
	 *            对象类型
	 * @return 对象
	 */
	@SuppressWarnings("deprecation")
	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param typeReference
	 *            对象类型
	 * @return 对象
	 */
	@SuppressWarnings("deprecation")
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		try {
			return mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param javaType
	 *            对象类型
	 * @return 对象
	 */
	@SuppressWarnings("deprecation")
	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		try {
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象转换为JSON流
	 * 
	 * @param writer
	 *            writer
	 * @param value
	 *            对象
	 */
	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	* @Title: jsonstrToList 
	* @Description: TODO将json字符串转换为List<Map<String,Object>>
	* @param @param optionJson
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> jsonstrToList(String optionJson){
		//json串格式
		//String optionJson ="[{\"content\":\"12313\",\"issee\":\"1\"},{\"content\":\"32323\",\"issee\":\"0\"},{\"content\":\"434343\",\"issee\":\"1\"}]";
		List<Map<String,Object>> mapListJson = new ArrayList<Map<String,Object>>();
		try {
			JSONArray jsonArray = JSONArray.fromObject(optionJson);  
	        mapListJson = (List<Map<String, Object>>)jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
		}
//        for (int i = 0; i < mapListJson.size(); i++) {  
//          Map<String,Object> obj=mapListJson.get(i);  
//          for(Entry<String,Object> entry : obj.entrySet()){  
//              String strkey1 = entry.getKey();  
//              Object strval1 = entry.getValue();  
//              System.out.println("KEY:"+strkey1+"  -->  Value:"+strval1+"\n");  
//          }
//        }
        return mapListJson;
	}

}