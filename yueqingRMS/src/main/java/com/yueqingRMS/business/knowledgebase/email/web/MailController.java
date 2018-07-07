package com.yueqingRMS.business.knowledgebase.email.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqingRMS.business.knowledgebase.email.domain.MailDomain;
import com.yueqingRMS.business.knowledgebase.email.service.IMailService;
import com.yueqingRMS.platform.base.web.BaseController;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：MailController   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年8月7日 下午4:58:00   
* 修改人：   
* 修改时间：2017年8月7日 下午4:58:00   
* 修改备注：   
* @version    
*    
*/
@Controller
@RequestMapping("/business/knowledgebase/mail")
public class MailController extends BaseController{
	
	@Autowired
	private IMailService  mailServiceImpl;
	
	
	/**
	 * @return DEMO_LIST 界面
	 */
	@RequestMapping("/findMail_IndexPage")
	public String findMail_IndexPage(){
		return "business/knowledgebase/mail/MailIndex";	
	}
	
	/**
	 * sendMail_bySpring
	 * @param mailDomain
	 * @return
	 */
	@RequestMapping(value = "/sendMail_bySpring")
    public String sendMail_bySpring(MailDomain mailDomain){
		return mailServiceImpl.sendMail_bySpring( mailDomain);
	}
	
	/**
	 * 自己封装工具类方式
	 * @param mailDomain
	 * @return
	 */
	@RequestMapping(value = "/sendMail_byJavaMail")
	@ResponseBody
    public String sendMail_byJavaMail(MailDomain mailDomain){
		return mailServiceImpl.sendMail_byJavaMail( mailDomain);
	}
	
	/**
	 * 自己封装工具类方式(wenjian)
	 * @param mailDomain
	 * @return
	 */
	@RequestMapping(value = "/sendMail_byJavaMailWithFile")
	@ResponseBody
    public String sendMail_byJavaMailWithFile(MailDomain mailDomain,HttpServletRequest request){
		return mailServiceImpl.sendMail_byJavaMailWithFile( mailDomain,request);
	}
	
	
	
	
	
	
	
	
	
	
	//SendMailByTable.jsp
//	 @Autowired
//	    private ResourceLoader resourceLoader;

//	    @RequestMapping("/WEB-INF-file")
//	    @ResponseBody
//	    public String testProperties() throws IOException {
//	        String content = IOUtils.toString(resourceLoader.getResource("/WEB-INF/target_file.txt").getInputStream());
//	        return "the content of resources:" + content;
//	    }
	
//	@RequestMapping(value = "/params_modify", produces="text/html;charset=utf-8",method=RequestMethod.POST)
//    @ResponseBody
//    public String params_modify(@RequestBody String data){
//        try {
//            JSONObject jo = JSONObject.parseObject(data);
//            PropertiesConfigUtil.setProperties(jo.entrySet());
////            return JSON.toJSONString(new Result(true,"参数设置成功！"));
//            return JSON.toJSONString("参数设置成功！");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return JSON.toJSONString("出现IO异常:可能配置文件找不到");
////            return JSON.toJSONString(new Result(false,"出现IO异常:可能配置文件找不到"));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            return JSON.toJSONString("出现URISyntax异常:可能配置文件不对");
////            return JSON.toJSONString(new Result(false,"出现URISyntax异常:可能配置文件不对"));
//        }
//    }
//    @RequestMapping(value = "/params_sendTestMail", produces="text/html;charset=utf-8",method=RequestMethod.POST)
//    @ResponseBody
//    public String params_sendTestMail(String mail_host,int mail_port,String mail_username,String mail_password,
//                    String mail_from,String mail_to){
//        try{
//            MailUtil.sendMailForTest(mail_host,mail_port,mail_username,mail_password,mail_from,mail_to);
////            return JSON.toJSONString(new Result(true,"测试邮件发送成功,请注意查收！"));
//            return JSON.toJSONString("测试邮件发送成功,请注意查收！");
//        }catch (MailAuthenticationException e) {
//            e.printStackTrace();
//            return JSON.toJSONString("邮件认证异常：authentication failure(认证失败)");
////            return JSON.toJSONString(new Result(false,"邮件认证异常：authentication failure(认证失败)"));
//        }catch(MailSendException e){
//            e.printStackTrace();
//            return JSON.toJSONString("邮件发送异常：failure when sending the message(发送消息失败)");
////            return JSON.toJSONString(new Result(false,"邮件发送异常：failure when sending the message(发送消息失败)"));
//        }catch(MailParseException  e){
//            e.printStackTrace();
//            return JSON.toJSONString("邮件消息解析异常：failure when parsing the message(消息解析失败)");
////            return JSON.toJSONString(new Result(false,"邮件消息解析异常：failure when parsing the message(消息解析失败)"));
//        }
//
//    }
	
	
}
