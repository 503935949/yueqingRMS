package com.yueqingRMS.business.knowledgebase.text.mail;


import java.util.Calendar;  
import java.util.Properties;  
  
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.Message.RecipientType;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class SendMail {  
	 public static void main(String[] args){
	        try {
	            String host = "smtp.qq.com";//这是QQ邮箱的smtp服务器地址
	            String port = "465"; //端口号25
	            /*
	            *Properties是一个属性对象，用来创建Session对象
	            */
	            Properties props = new Properties();
	            props.setProperty("mail.smtp.host", host);
	            props.setProperty("mail.smtp.port", port);
	            props.setProperty("mail.smtp.auth", "true");
	            props.setProperty("mail.smtp.ssl.enable", "true");//"true"
	            props.setProperty("mail.smtp.connectiontimeout", "5000");
	            final String user = "503935949@qq.com";//用户名
	            final String pwd = "gyaxohwodrozbjdg";//密码fhsoilytoyoxcbae
	            /*
	            *Session类定义了一个基本的邮件对话。
	            */
	            Session session = Session.getInstance(props, new Authenticator() {
	                @Override
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    //登录用户名密码
	                    return new PasswordAuthentication(user,pwd);
	                }
	            });
	            session.setDebug(true);
	            
	            
	            /*
		            *Message对象用来储存实际发送的电子邮件信息
		            */
		            MimeMessage message = new MimeMessage(session);
		            message.setSubject("邮件标题");
		            //消息发送者接收者设置(发件地址，昵称)，收件人看到的昵称是这里设定的
		            message.setFrom(new InternetAddress(user,"二师兄"));
		            message.addRecipients(Message.RecipientType.TO,new InternetAddress[]{
		                //消息接收者(收件地址，昵称)
		                //不过这个昵称貌似没有看到效果
		                    new InternetAddress("316505707@qq.com","哈喽  我试试好不好用"),
		                    new InternetAddress("524777535@qq.com","哈喽  我试试好不好用")
		            });
		            message.saveChanges();

		            //设置邮件内容及编码格式
		            //后一个参数可以不指定编码，如"text/plain"，但是将不能显示中文字符
//		            message.setContent("点击此处<a href='//www.baidu.com' >dian</a>", "text/html;charset=UTF-8");//text/plain
		            message.setContent("<html lang='zh-CN'><head ><meta charset='utf-8'>"

		            + "</head><body>内容：这是我发的一封Java邮件"

		            + "<a href='http://www.baidu.com'>【百度一下】</a></body></html>",

		            "text/html;charset=utf-8");
		            
		            
	            /*
	            *Transport类用来发送邮件。
	            *传入参数smtp，transport将自动按照smtp协议发送邮件。
	            */
	            Transport transport = session.getTransport("smtp");//"smtps"
	            transport.connect(host,user,pwd);
	           
	            //发送
	            //transport.send(message);
	            Transport.send(message);
	            transport.close();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }  
}  