package com.yueqingRMS.business.knowledgebase.email.service.Impl;

import java.io.File;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.yueqingRMS.business.knowledgebase.email.domain.MailDomain;
import com.yueqingRMS.business.knowledgebase.email.service.IMailService;
import com.yueqingRMS.platform.exception.AppBusinessException;
import com.yueqingRMS.platform.tools.page.Page;
import com.yueqingRMS.platform.util.MailUtil;
import com.yueqingRMS.platform.util.SpringContextHolder;
import com.yueqingRMS.platform.util.StringUtils;


@Transactional
@Service
public  class MailServiceImpl  implements IMailService{

	private static Logger logger = Logger.getLogger(MailServiceImpl.class);
	//@Autowired
	//private MailAL mailAL;
	
    private SimpleMailMessage simpleMailMessage = SpringContextHolder.getBean("simpleMailMessage");
    private JavaMailSender javaMailSender = SpringContextHolder.getBean("javaMailSender");
	/**
	 * 新增
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int insert(MailDomain object) {
		return 0;
	}
	
	/**
	 * 修改 
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int updateByKey(MailDomain object) {
		return 0;
	}
	
	/**
	 * 删除 
	 * @param object 对象，传参用
	 * @return int
	 */
	@Transactional
	public  int deleteByKey(MailDomain object) {
		return 0;
	}
	
	/**
	 * 查询  
	 * @param object 对象，传参用
	 * @return object
	 */
	@Transactional
	public  MailDomain loadByKey(MailDomain object) {
		return null;
	}
	
	
	/**
	 * 查询分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	@Transactional
	public  Page findByWhereForPage(MailDomain object) {
//		return toPage (mailAL.findByWhereForPage(object));
		return null;
	}
	
//	public Page toPage (List<MailDomain> list) {
//		Page page = new Page();
//		
//		return page;
//	}
	
	/**
	 * list分页  
	 * @param object 对象，传参用
	 * @return Page
	 */
	@Transactional
	public  List<MailDomain> findByWhereForList(MailDomain object) {
//		return mailAL.findByWhereForList(object);
		return null;
	}

	/**
	 * sendMail_bySpring
	 * @param mailDomain
	 * @return
	 */
	@Transactional
	public String sendMail_bySpring(MailDomain mailDomain) {
		
		//获取配置
		// 建立邮件消息
        MimeMessage message = javaMailSender.createMimeMessage();
        
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            // 设置发件人邮箱
            if (mailDomain.getEmailFrom()!=null) {
                messageHelper.setFrom(mailDomain.getEmailFrom());
            } else {
                messageHelper.setFrom(simpleMailMessage.getFrom());
            }
            
            // 设置收件人邮箱
            if (mailDomain.getToEmails()!=null) {
                String[] toEmailArray = mailDomain.getToEmails().split(";");
                List<String> toEmailList = new ArrayList<String>();
                if (null == toEmailArray || toEmailArray.length <= 0) {
                    throw new AppBusinessException("收件人邮箱不得为空！");
                } else {
                    for (String s : toEmailArray) {
                        if (s!=null&&!s.equals("")) {
                            toEmailList.add(s);
                        }
                    }
                    if (null == toEmailList || toEmailList.size() <= 0) {
                        throw new AppBusinessException("收件人邮箱不得为空！");
                    } else {
                        toEmailArray = new String[toEmailList.size()];
                        for (int i = 0; i < toEmailList.size(); i++) {
                            toEmailArray[i] = toEmailList.get(i);
                        }
                    }
                }
                messageHelper.setTo(toEmailArray);
            } else {
                messageHelper.setTo(simpleMailMessage.getTo());
            }
            
            // 邮件主题
            if (mailDomain.getSubject()!=null) {
                messageHelper.setSubject(mailDomain.getSubject());
            } else {
                
                messageHelper.setSubject(simpleMailMessage.getSubject());
            }
            
            // true 表示启动HTML格式的邮件
            messageHelper.setText(mailDomain.getContent(), true);
            // 添加图片
            if (null != mailDomain.getPictures()) {
                for (Iterator<Map.Entry<String, String>> it = mailDomain.getPictures().entrySet()
                        .iterator(); it.hasNext();) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
                    }
                    
                    File file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("图片" + filePath + "不存在！");
                    }
                    
                    FileSystemResource img = new FileSystemResource(file);
                    messageHelper.addInline(cid, img);
                }
            }
            
            // 添加附件
            if (null != mailDomain.getAttachments()) {
                for (Iterator<Map.Entry<String, String>> it = mailDomain.getAttachments()
                        .entrySet().iterator(); it.hasNext();) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
                    }
                    
                    File file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("附件" + filePath + "不存在！");
                    }
                    
                    FileSystemResource fileResource = new FileSystemResource(file);
                    messageHelper.addAttachment(cid, fileResource);
                }
            }
            messageHelper.setSentDate(new Date());
            // 发送邮件
            javaMailSender.send(message);
            logger.info("------------发送邮件完成----------");
            
        } catch (MessagingException e) {
            
            e.printStackTrace();
            return "error";
        }

		return "success";
	}
	
	
	/**
	 * 自己封装工具类方式
	 * @param mailDomain
	 * @return
	 */
	public String sendMail_byJavaMail(MailDomain mailDomain){
		if(StringUtils.isEmpty(mailDomain.getToEmails())
//				||StringUtils.isEmpty(mailDomain.getEmailFrom())||
//					StringUtils.isEmpty(mailDomain.getSubject())
					){
			return JSON.toJSONString("error");
		}
		String content =  "<html lang='zh-CN'><head ><meta charset='utf-8'></head><body>"
						+ "内容：这是我发的一封Java邮件"
		        		+ "<a href='http://www.baidu.com'>【百度一下】</a>"
		        		+ "</body></html>";
		if(StringUtils.isNotEmpty(mailDomain.getContent())) {
			content = mailDomain.getContent();
		}
		
        try {
			MailUtil.sendHtmlMail(mailDomain.getEmailFrom(),mailDomain.getToEmails(), mailDomain.getSubject(), content);
			return JSON.toJSONString("success");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSON.toJSONString("error");
		}
	} 
	
	
	/**
	 * 自己封装工具类方式(wenjian)
	 * @param mailDomain
	 * @return
	 */
	public String sendMail_byJavaMailWithFile(MailDomain mailDomain){
		if(StringUtils.isEmpty(mailDomain.getToEmails())
//				||StringUtils.isEmpty(mailDomain.getEmailFrom())||
//					StringUtils.isEmpty(mailDomain.getSubject())
					){
			return JSON.toJSONString("error");
		}
		String content =  "<html lang='zh-CN'><head ><meta charset='utf-8'></head><body>"
						+ "内容：这是我发的一封Java邮件"
		        		+ "<a href='http://www.baidu.com'>【百度一下】</a>"
		        		+ "<img src=\"cid:apple\">"
		        		+ "</body></html>";
		if(StringUtils.isNotEmpty(mailDomain.getContent())) {
			content = mailDomain.getContent();
		}
		
        try {
			MailUtil.sendFileMail(mailDomain.getToEmails(), mailDomain.getSubject(), content,
					"apple","C:\\Users\\Administrator\\Desktop\\apple-icon.png");
			return JSON.toJSONString("success");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSON.toJSONString("error");
		}
	} 
	
	/**
	 * 自己封装工具类方式(wenjian)
	 * @param mailDomain
	 * @return
	 */
	public String sendMail_byJavaMailWithFile(MailDomain mailDomain,HttpServletRequest request){
		if(StringUtils.isEmpty(mailDomain.getToEmails())
//				||StringUtils.isEmpty(mailDomain.getEmailFrom())||
//					StringUtils.isEmpty(mailDomain.getSubject())
					){
			return JSON.toJSONString("error");
		}
		String content =  "<html lang='zh-CN'><head ><meta charset='utf-8'></head><body>"
						+ mailDomain.getContent()
		        		+ "</body></html>";
//		if(StringUtils.isNotEmpty(mailDomain.getContent())) {
//			content = mailDomain.getContent();
//		}
		String[] to = null;
		 // 设置收件人邮箱
        if (mailDomain.getToEmails()!=null) {
//        	String[] toEmailArray = mailDomain.getToEmails().split("[\\p{P}\\p{M}\\p{Z}\\p{S}]");
        	String[] toEmailArray = mailDomain.getToEmails().split("[,，。；：;:、\\\\]");
            List<String> toEmailList = new ArrayList<String>();
            if (null == toEmailArray || toEmailArray.length <= 0) {
                throw new AppBusinessException("收件人邮箱不得为空！");
            } else {
                for (String s : toEmailArray) {
                    if (s!=null&&!s.equals("")) {
                        toEmailList.add(s);
                    }
                }
                if (null == toEmailList || toEmailList.size() <= 0) {
                    throw new AppBusinessException("收件人邮箱不得为空！");
                } else {
                    toEmailArray = new String[toEmailList.size()];
                    for (int i = 0; i < toEmailList.size(); i++) {
                        toEmailArray[i] = toEmailList.get(i);
                    }
                }
            }
            to = toEmailArray;
        } else {
            to = simpleMailMessage.getTo();
        }
		
        try {
			MailUtil.sendFileMail(to, mailDomain.getSubject(), content,request);
			return JSON.toJSONString("success");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSON.toJSONString("error");
		}
	}


	
	/**
	 * @return mailAL
	 */
//	public MailAL getBaseAL() {
//		return mailAL;
//	}

	/**
	 * @param mailAL
	 */
//	public void setBaseAL(MailAL mailAL) {
//		this.mailAL = mailAL;
//	}

	
	
}
