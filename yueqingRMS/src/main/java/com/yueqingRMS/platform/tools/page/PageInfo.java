/*
 * 
 * 
 * 
 */
package com.yueqingRMS.platform.tools.page;

import java.io.Serializable;



/**
 * 分页
 * 
 * 
 * 
 */
public class PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 209308332185798784L;
	
	/** 每页行数 */
	private int pageSize = 1;  
	/** 页数 */
    private int page ;  
    /** 总记录数 */
    private int totalRecord; 
    
    
  
    public int getPage() {  
        return page;  
    }  
  
    public void setPage(int page) {  
        this.page = page;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public int getTotalRecord() {  
        return totalRecord;  
    }  
  
    public void setTotalRecord(int totalRecord) {  
        this.totalRecord = totalRecord;  
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
	
      
}  
