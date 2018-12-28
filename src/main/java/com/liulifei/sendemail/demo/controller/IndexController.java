package com.liulifei.sendemail.demo.controller;

import com.liulifei.sendemail.demo.help.SendEmailHelp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class IndexController {

    @PostMapping("sendEmail")
    public ResponseEntity sendEmail(String content){
        System.out.println(content);
        SendEmailHelp.send("测试发送邮件",content,true);
        return new ResponseEntity("发送成功",HttpStatus.OK);
    }
}
