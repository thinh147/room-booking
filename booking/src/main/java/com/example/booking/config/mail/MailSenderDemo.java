package com.example.booking.config.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderDemo {

    private JavaMailSender sender;

    public MailSenderDemo(JavaMailSender sender) {
        this.sender = sender;
    }

    public void send(MailDTO dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getTo());
        message.setFrom(dto.getFrom());
        message.setSubject(dto.getSubject());
        message.setText(dto.getContent());

        sender.send(message);
    }
}
