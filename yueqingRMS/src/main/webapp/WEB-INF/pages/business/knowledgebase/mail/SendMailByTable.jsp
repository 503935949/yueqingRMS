<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="/common/base/include/include.jsp"%>
    <title>SendMailByTable</title>
    
  </head>
  
  <body>
  <form id="formParams" action="post">
                <table class="table table-bordered table-striped">
                    <thead><tr><th>参数名</th><th>参数值</th><th>说明</th></tr></thead>
                    <tbody>
                        <tr><td colspan="3" align="center"><span style="font-weight: bolder;">邮件服务</span></td></tr>
                        <tr>
                            <td>邮件服务器主机(SMTP)</td>
                            <td><input type="text" name="mail_host" value="smtp.163.com" style="width:100%;"/></td>
                            <td>邮件服务器主机host，目前只支持SMTP协议(可以是163或者qq)</td>
                        </tr>
                        <tr>
                            <td>邮件服务器端口</td>
                            <td><input type="number" name="mail_port" value="25" style="width:100%;"/></td>
                            <td>邮件服务器端口</td>
                        </tr>
                        <tr>
                            <td>邮件服务登录账号</td>
                            <td><input type="email" name="mail_username" value="username@163.com" style="width:100%;"/></td>
                            <td>登录邮件服务器的账号，例如username@163.com</td>
                        </tr>
                        <tr>
                            <td>邮件服务登录密码</td>
                            <td><input type="password" name="mail_password" value="234" style="width:100%;"/></td>
                            <td>登录邮件服务器的密码，该密码通常是通过短信动态授权第三方登录的密码</td>
                        </tr>
                        <tr>
                            <td>连接服务器超时(毫秒)</td>
                            <td><input type="number" name="mail_smtp_timeout" value="25000" style="width:100%;"/></td>
                            <td>使用账号密码登录邮件服务器连接超时(毫秒)</td>
                        </tr>
                        <tr>
                            <td>邮件的发送账号</td>
                            <td><input type="email" name="mail_from" value="username@163.com" style="width:100%;"/></td>
                            <td>邮件的发送账号，用于系统发送邮件的账号，例如username@163.com</td>
                        </tr>
                        <tr>
                            <td>发送测试邮件账号，看配置是否正确</td>
                            <td><input type="email" id="mailTo" placeholder="example@163.com" style="width:100%;"/></td>
                            <td><button type="button" class="btn btn-primary" onclick="sendTestMail()">发送测试邮件</button></td>
                        </tr>
                        <tr><td colspan="3" align="center">
                            <button type="button" class="btn btn-primary" onclick="saveParams()">保存</button>
                            <button type="button" class="btn btn-primary" onclick="$('#formParams')[0].reset()">重置</button>
                        </td></tr>
                    </tbody>
                </table>
                </form>

	
  </body>
  <script>
var regMail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
/* 功能 */
function saveParams(){
    if(confirm("更改参数设置很有可能会导致系统功能异常(如果出现问题，请联系管理员)，确定要保存更改吗？"))
    {
        var from = $('#formParams input[name=mail_from]').val();
        var username = $('#formParams input[name=mail_username]').val();
        if(!regMail.test(from) || !regMail.test(username)){
            alert('邮箱格式不正确，请输入有效的邮件账号！');
            return ;
        }
        var data = $('#formParams').serializeArray();
        var obj=new Object(); 
        //将array转换成JSONObject
        $.each(data,function(index,param){  
            if(!(param.name in obj)){  
                obj[param.name]=param.value;  
            }  
        });  
        $.ajax({
            type: "POST",
            url: "params_modify.do",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(obj),
            dataType: "json",
            success: function (message) {
                alert(message.info);
            },
            error: function (message) {
                alert(message);
            }
        });
    }
}
function sendTestMail(){
    var to = $('#mailTo').val();
    var from = $('#formParams input[name=mail_from]').val();
    var username = $('#formParams input[name=mail_username]').val();
    var password = $('#formParams input[name=mail_password]').val();
    var host  = $('#formParams input[name=mail_host]').val();
    var port  = $('#formParams input[name=mail_port]').val();
    if(!regMail.test(to) || !regMail.test(from) || !regMail.test(username)){
        alert('邮箱格式不正确，请输入有效的邮件账号！');
        return ;
    }
    var p = {mail_host:host,mail_port:port,mail_username:username,mail_password:password,mail_from:from,mail_to:to};
    $.post("params_sendTestMail.do",p,function(data){
        data = eval('('+data+')');
        alert(data.info);
    });
}
</script>
</html>