package com.yueqingRMS.platform.tags.yrmsTags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

public class DemoTag extends BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7302635513791565049L;
	
	
	
	/**
	 * 属性
	 */
	public String attr;
	
	/**
	 * 资源名称
	 */
	public String resName;
	
	/**
	 * 是否重复
	 */
	public String again;
	
	
	//标签开始时调用的处理方法    
//    public int doStartTag() throws JspException {  
//        //表示需要处理标签体    
//        return EVAL_BODY_BUFFERED;  
//    }  
//  
//    //判断了标签体内容之后调用的处理方法    
//    public int doAfterBody() throws JspException {  
//        //取得标签体对象    
//        BodyContent body = getBodyContent();  
//        //取得标签体的字符串内容    
//        String content = body.getString();  
//        //清除标签体中的内容    
//        body.clearBody();  
//        //将内容转换成大写    
//        content = content.toUpperCase();  
//        //在pageContext对象中保存变量    
//       // pageContext.setAttribute(var, content);  
//        //结束对标签体的处理    
//        return SKIP_BODY;  
//    }  
    
    
    /**
     * 遇到标签开始时会呼叫的方法
     */
    public int doStartTag() throws JspException {  
    	
        System.out.println("*********  doStartTag()........\n");   
        
       if(attr == "true")
       {
    	   //正常显示标签中的内容
           return Tag.EVAL_BODY_INCLUDE;   
       }
       else
       {
    	   //隐藏显示标签中的内容(可配合下文方法进行替换)
           return Tag.SKIP_BODY;
       }
       
  
    }   
  
   
    /**
     * 显示完标签间文字之后呼叫的
     */
    public int doAfterBody() throws JspException {   
    	
        System.out.println("*********  doAfterBody()........\n");   
  
        	//再显示一次标签间的文字
//        	return EVAL_BODY_AGAIN;
     	   	//继续执行标签处理的下一步
        	return Tag.SKIP_BODY;   
        
    }   
  
    /**
     * 在遇到标签结束时呼叫的方法
     */
    public int doEndTag() throws JspException {  
    	
        System.out.println("*********  doEndTag().. "+ resName +"......\n");   
        JspWriter out = this.pageContext.getOut();   
  
        //取得标签体对象    
//        BodyContent body = getBodyContent();  
        //取得标签体的字符串内容    
//        String content = body.getString();  
        //清除标签体中的内容    
//        body.clearBody();
        try {   
            out.println("Hello !!!!");   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        //返回值有EVAL_BODY_AGAIN与SKIP_BODY，
        //前者会再显示一次标签间的文字，后者则继续执行标签处理的下一步。
//        return EVAL_BODY_AGAIN;
//        return SKIP_BODY;
        return super.doEndTag();   
        
    }

	/**
	 * @return attr
	 */
	public String getAttr() {
		return attr;
	}

	/**
	 * @param attr
	 */
	public void setAttr(String attr) {
		this.attr = attr;
	}

	/**
	 * @return resName
	 */
	public String getResName() {
		return resName;
	}

	/**
	 * @param resName
	 */
	public void setResName(String resName) {
		this.resName = resName;
	}

	/**
	 * @return again
	 */
	public String getAgain() {
		return again;
	}


	/**
	 * @param again
	 */
	public void setAgain(String again) {
		this.again = again;
	}


	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}   
    
    
    
    
	/**
	 *
	 *  doStartTag(),doEndTag()，doAfterBody(),
	    doStartTag()方法是遇到标签开始时会呼叫的方法，其合法的返回值是EVAL_BODY_INCLUDE与SKIP_BODY,前者表示将显示标签间的文字，后者表示不显示标签间的文字；
	    doEndTag()方法是在遇到标签结束时呼叫的方法，其合法的返回值是EVAL_PAGE与 SKIP_PAGE，前者表示处理完标签后继续执行以下的JSP网页，后者是表示不处理接下来的JSP网页
	    doAfterBody()这个方法是在显示完标签间文字之后呼叫的，其返回值有EVAL_BODY_AGAIN与SKIP_BODY，前者会再显示一次标签间的文字，后者则继续执行标签处理的下一步。
		EVAL_BODY_INCLUDE：把Body读入存在的输出流中，doStartTag()函数可用
		EVAL_PAGE：继续处理页面，doEndTag()函数可用
		SKIP_BODY：忽略对Body的处理，doStartTag()和doAfterBody()函数可用
		SKIP_PAGE：忽略对余下页面的处理，doEndTag()函数可用
		EVAL_BODY_TAG：已经废止，由EVAL_BODY_BUFFERED取代
		EVAL_BODY_BUFFERED：申请缓冲区，由setBodyContent()函数得到的BodyContent对象来处理tag的body，如果类实现了BodyTag，那么doStartTag()可用，否则非法
		EVAL_BODY_BUFFERED 要将BodyContent的内容输出 如：
		JspWriter w = pageContext.getOut();
		  if (bodyContent != null) {
		   if (w instanceof BodyContent) {
		    w = ((BodyContent) w).getEnclosingWriter();
		   }
		  }
		  String cnt = this.bodyContent.getString();
		  try {
		   w.write(cnt);
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		
		   预定的处理顺序是：doStartTag()返回SKIP_BODY,doAfterBodyTag()返回SKIP_BODY,doEndTag()返回EVAL_PAGE.
		   如果继承了TagSupport之后，如果没有改写任何的方法，标签处理的执行顺序是：
		
		   doStartTag() ->不显示文字 ->doEndTag()->执行接下来的网页
		
		  如果您改写了doStartTag(),则必须指定返回值，如果指定了EVAL_BODY_INCLUDE,则执行顺序是
		
		   doStartTag()->显示文字->doAfterBodyTag()->doEndTag()->执行下面的网页
	 * 
	 **/
    
    

}
