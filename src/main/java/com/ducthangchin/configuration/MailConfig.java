package com.ducthangchin.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

//    mail.enable=false
//    mail.smtp.port=587
//    mail.smtp.host=smtp.mailtrap.io
//    mail.smtp.user=2169b0afd890cb
//    mail.smtp.password=a50a889cdf842d

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private Integer port;

    @Value("${mail.smtp.user}")
    private String user;

    @Value("${mail.smtp.pass}")
    private String password;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(user);
        mailSender.setPassword(password);


        return mailSender;
    }

}
