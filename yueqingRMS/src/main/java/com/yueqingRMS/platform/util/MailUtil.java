package com.yueqingRMS.platform.util;


import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
	
	
    /**
     * 获得初始化数据，登录邮箱，获得邮差
     */
    public static JavaMailSenderImpl mailSender = createMailSender(MailConfigInfo.mail_host,MailConfigInfo.mail_port,MailConfigInfo.mail_username,
            MailConfigInfo.mail_password,MailConfigInfo.mail_smtp_timeout);
    
    /**
     * 获得发起人邮箱
     */
    public static String mailFrom = MailConfigInfo.mail_from;
    
    /**
     * 定义邮差
     * @param host
     * @param port
     * @param username
     * @param password
     * @param timeout
     * @return sender
     */
    private static JavaMailSenderImpl createMailSender(String host,int port,String username,String password,int timeout){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        
        
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.ssl.enable", "true");//"true"
        p.setProperty("mail.smtp.connectiontimeout", "5000");
        p.setProperty("mail.smtp.timeout",timeout+"");
        p.setProperty("mail.smtp.auth","true");
        sender.setJavaMailProperties(p);

        
        /*
         *Session类定义了一个基本的邮件对话。
         *是否Debug   （如果不为了这个可以用默认session，直接设用户密码即可）
         */
//      final String user = "503935949@qq.com";//用户名
//      final String pwd = "gyaxohwodrozbjdg";//密码fhsoilytoyoxcbae
//        final String user = username;//用户名
//        final String pwd = password;
//         Session session = Session.getInstance(p, new Authenticator() {
//             @Override
//             protected PasswordAuthentication getPasswordAuthentication() {
//                 //登录用户名密码
//                 return new PasswordAuthentication(user,pwd);
//             }
//         });
//         sender.setSession(session);
//         //是否Debug   （如果不为了这个可以用默认session，直接设用户密码即可）
//         session.setDebug(true);
         
        return sender;
    }
    
    //发送测试的邮件
    public static void sendMailForTest(String host,int port,String username,String password,String from,
            String to){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject("这是测试邮件，请勿回复！");
        mail.setSentDate(new Date());// 邮件发送时间
        mail.setText("这是一封测试邮件。如果您已收到此邮件，说明您的邮件服务器已设置成功。请勿回复，请勿回复，请勿回复，重要的事说三遍！");
        JavaMailSenderImpl sender = createMailSender(host,port,username,password,25000);
        sender.send(mail);
    }
    
    /** 发送文本邮件
     * @param to
     * @param subject
     * @param text
     */
    public static void sendTextMail(String to,String subject,String text){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(mailFrom);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setSentDate(new Date());// 邮件发送时间
        mail.setText(text);
        mailSender.send(mail);
    }
    
    
    /**
     * 发送HTML 模板邮件
     * @param to
     * @param subject
     * @param html
     * @throws MessagingException
     */
    public static void sendHtmlMail(String from,String to,String subject,String html) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        
        if(StringUtils.isEmpty(from)) {
        	messageHelper.setFrom(mailFrom);
        }else {
        	messageHelper.setFrom(from);
        }
        
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送带附件的邮件（不建议直接用） 后面对其封装
     * @param to
     * @param subject
     * @param html
     * @param contentId
     * @param resource
     * @throws MessagingException
     */
    public static void sendFileMail(String to,String subject,String html,String contentId,Resource resource) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        //FileSystemResource img = new FileSystemResource(new File("c:/350.jpg"));
        messageHelper.addInline(contentId, resource);
        // 发送
        mailSender.send(mimeMessage);
    }
    
    /**
     * 发送带附件的邮件（不建议直接用） 后面对其封装
     * @param to
     * @param subject
     * @param html
     * @param contentId
     * @param resource
     * @throws MessagingException
     */
    public static void sendFileMail(String to,String subject,String html,String contentId,String path) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        FileSystemResource resource = new FileSystemResource(new File(path));
        messageHelper.addInline(contentId, resource);
        messageHelper.addAttachment("apple-icon.png", resource);
        messageHelper.addAttachment("apple-icon.png", resource);
        // 发送
        mailSender.send(mimeMessage);
    }
    
    /**
     * 发送带附件的邮件（不建议直接用） 后面对其封装
     * @param to
     * @param subject
     * @param html
     * @param contentId
     * @param resource
     * @throws MessagingException
     */
    public static void sendFileMail(String[] to,String subject,String html,HttpServletRequest request) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        
        //附件处理
        Map<String, InputStream> files = FileUtil.upLoadFileStream(request);
        FileSystemResource resource = null;
      
        String sysTemp = System.getProperty("java.io.tmpdir");
        //跨平台分隔符--File.separator
        String path = sysTemp+File.separator;
        for (Iterator<Map.Entry<String, InputStream>> it =files.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, InputStream> entry = it.next();
            String fileName = entry.getKey();
            InputStream fileStream = entry.getValue();
            resource = new FileSystemResource(FileUtil.inputstreamtofile(fileStream, new File(path+FileUtil.createNewName(fileName))));
            messageHelper.addAttachment(fileName, resource);
        } 
//      messageHelper.addInline(contentId, resource);
        // 发送
        mailSender.send(mimeMessage);
    }
    
    

    
    
}

