package com.yueqingRMS.platform.exception;


public class AppSystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799508996704161667L;

	/**
	 * 
	 */
	
	public AppSystemException(){}
	
	public AppSystemException(String msg){
		super(msg);
	}
	
	public AppSystemException(String msg,Throwable e){
		super(msg,e);
	}
	
	public AppSystemException(Throwable e){
		super(e);
	}
	

}
