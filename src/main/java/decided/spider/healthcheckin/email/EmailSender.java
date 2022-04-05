package decided.spider.healthcheckin.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    public static void send(String email) throws IOException {
        EmailConfig config = new EmailConfig();
        String server = config.getServer();
        String port = config.getPort();
        String ssl = config.getSsl();
        String mail = config.getEmail();
        String password = config.getPassword();
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host",server);//发送邮箱服务器
        properties.setProperty("mail.smtp.port",port);//发送端口
        properties.setProperty("mail.smtp.auth","true");//是否开启权限控制
        properties.setProperty("mail.debug","false");//打印信息到控制台
        properties.setProperty("mail.transport","smtp");//发送的协议是简单的邮件传输协议
        properties.setProperty("mail.smtp.ssl.enable",ssl);
        //建立链接
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail,password);
            }
        });
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
            message.setSubject("打卡异常");
            message.setContent("<h1>今日两次打卡异常，导致失败，请自行打卡</h1>","text/html;charset=utf-8");

            Transport transport = session.getTransport();
            transport.connect(mail,password);
            Transport.send(message);
            transport.close();
        }catch (Exception e){
            System.out.println();
        }
    }


}
