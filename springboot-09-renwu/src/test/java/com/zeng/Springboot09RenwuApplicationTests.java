package com.zeng;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09RenwuApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {

        //发送邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("曾嘉彬好帅啊");
        mailMessage.setText("黎玉梅是你老婆吗，当然");

        mailMessage.setTo("1573439264@qq.com");
        mailMessage.setFrom("1573439264@qq.com");

        mailSender.send(mailMessage);
    }

    @Test
    void contextLoads2() throws MessagingException {

        //发送邮件
        //复杂邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject("曾嘉彬好帅啊");
        helper.setText("<p style='color:red'>黎玉梅是你老婆吗，当然</p>",true);
        //附件
        helper.addAttachment("1.jpg",new File("C:\\Users\\123\\Desktop\\1.jpg"));

        helper.setTo("1573439264@qq.com");
        helper.setFrom("1573439264@qq.com");

        mailSender.send(mimeMessage);
    }

    /**
     *
     * @param html
     * @param subject
     * @param text
     * @throws MessagingException
     * @Author:zengjaabin
     */
    public void SendMail(Boolean html , String subject , String text) throws MessagingException {
        //发送邮件
        //复杂邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,html);

        helper.setSubject(subject);
        helper.setText(text,true);
        //附件
        helper.addAttachment("1.jpg",new File("C:\\Users\\123\\Desktop\\1.jpg"));

        helper.setTo("1573439264@qq.com");
        helper.setFrom("1573439264@qq.com");

        mailSender.send(mimeMessage);
    }
}
