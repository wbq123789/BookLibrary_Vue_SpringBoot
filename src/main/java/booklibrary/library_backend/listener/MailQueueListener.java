/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.listener;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
* @Description: 邮件接收监听器
* @Author: 王贝强
* @Date: 2023/12/26
*/
@Component
public class MailQueueListener {

    @Resource
    JavaMailSender sender;

    @Value("${spring.mail.username}")
    String username;
    /**
    * @Description: 从RabbitMq中获取邮件信息并发送邮件
    * @Param: [data]
    * @return: void
    * @Author: 王贝强
    * @Date: 2023/12/26
    */
    @RabbitHandler
    @RabbitListener(queues = "mail")
    public void sendMailMessage(Map<String,Object> data) {
        String email = data.get("email").toString();
        Integer code = (Integer) data.get("code");
        String type=data.get("type").toString();
        SimpleMailMessage message=switch(type){
            case "register"->createMessage("欢迎注册图书管理系统",
                    "您的邮件注册码为"+code+"，有效时间5分钟！",email);
            case "reset"->createMessage("图书管理系统——密码重置",
                    "您的正在通过邮箱重置账号密码，验证码为："+code+"，有效时间5分钟，如非本人操作，请无视",email);
            default -> null;
        };
        if(message == null) return;
        //使用JavaMailSender发送邮件
        sender.send(message);
    }
    /**
    * @Description: 邮件信息生成
    * @Param: [title, content, email]
    * @return: org.springframework.mail.SimpleMailMessage
    * @Author: 王贝强
    * @Date: 2023/12/26
    */
    private SimpleMailMessage createMessage(String title, String content, String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(content);
        message.setTo(email);
        message.setFrom(username);
        return message;
    }
}
