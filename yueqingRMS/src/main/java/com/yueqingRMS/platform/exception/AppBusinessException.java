package com.yueqingRMS.platform.exception;


public class AppBusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8707495951449952372L;
	
	public AppBusinessException(){}
	
	public AppBusinessException(String msg){
		super(msg);
	}
	
	public AppBusinessException(String msg,Throwable e){
		super(msg,e);
	}
	
	public AppBusinessException(Throwable e){
		super(e);
	}
	

}
