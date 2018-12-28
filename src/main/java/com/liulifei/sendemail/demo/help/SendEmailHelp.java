package com.liulifei.sendemail.demo.help;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.ContextLoader;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class SendEmailHelp {

    private final static Boolean deBug=true;

    private static void init(){

    }
    public static void send(String title,String content,boolean isHtml){
        init();

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.qq.com");
        sender.setPort(465);
        sender.setDefaultEncoding("UTF-8");
        //邮箱账号
        sender.setUsername("@qq.com");
        //SMTP 的授权码
        sender.setPassword("");

        Properties properties= new Properties();
        properties.put("mail.smtp.auth","true");
        properties.setProperty("mail.transport.protocol", "smtp");//必须选择协议
        properties.put("mail.smtp.timeout","25000");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.host","smtp.qq.com");
        properties.put("mail.smtp.socketFactory.fallback","false");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(deBug);
        sender.setSession(session);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            //发送人邮件地址，发件人和邮件账号必须一样，必须填写，不然QQ邮箱报错。
            helper.setFrom("@qq.com");
            //收件人邮箱地址，发送给谁？
            helper.setTo("@qq.com");
            //邮件主题
            helper.setSubject(title);
            //邮件的文本内容
            helper.setText(content,isHtml);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

    }
}
