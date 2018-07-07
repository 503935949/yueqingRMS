/*
 * 
 * 
 * 
 */
package com.yueqingRMS.platform.tools.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;





/**   
*    
* 项目名称：yueqingRMS   
* 类名称：Page   
* 类描述：分页类   
* 创建人：林曌   
* 创建时间：2017年8月30日 上午9:35:17   
* 修改人：   
* 修改时间：2017年8月30日 上午9:35:17   
* 修改备注：   
* @version    
*    
*/
public class Page  implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2973877342110242867L;
	
	/** 内容 */
	@SuppressWarnings("rawtypes")
	public List result = new ArrayList();
	
	/** 分页信息 */
	//@Autowired
	private  PageInfo pageInfo = new PageInfo();
	
	public Page () {}
  
    public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getPage() {  
        return pageInfo.getPage();  
    }  
  
    public void setPage(int page) {  
    	pageInfo.setPage(page);
    }  
  
    public int getPageSize() {  
        return pageInfo.getPageSize();  
    }  
  
    public void setPageSize(int pageSize) {  
    	pageInfo.setPageSize(pageSize);  
    }  
  
    public int getTotalRecord() {  
        return pageInfo.getTotalRecord();  
    }  
  
    public void setTotalRecord(int totalRecord) {  
    	pageInfo.setTotalRecord(totalRecord);
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@SuppressWarnings("rawtypes")
	public List getResult() {
		return result;
	}  
	
	@SuppressWarnings("rawtypes")
	public void setResult(List result) {  
        this.result = result;  
    } 
	
	@SuppressWarnings("unchecked")
	public void add(Object object) {
		result.add(object);
	}
	
	@SuppressWarnings("unchecked")
	public void add(int index,Object object) {
		result.add(index, object);;
	}
	
	@SuppressWarnings("unchecked")
	public void addAll(List<?> resultList) {
		for(Object ob:(List<?>) resultList){
			result.add(ob);
    	}
	}
	
	public int size() {
		return result.size();
	}
	
	public Object get(int index) {
		return result.get(index);
	}
	
	public Object remove(int index) {
		return result.remove(index);
	}
	
	public void clear() {
		result.clear();
	}
	
	public boolean isEmpty() {
		return result.isEmpty();
	}
	
	public boolean equals(Object object) {
		return result.equals(object);
	}


	
	
	
      
}  
