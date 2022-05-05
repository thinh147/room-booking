package com.example.booking.service.imp;

import com.example.booking.config.mail.MailDTO;
import com.example.booking.config.mail.MailSenderDemo;
import com.example.booking.service.DemoSendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class DemoSendMailServiceImp implements DemoSendMailService {

    @Autowired
    MailSenderDemo sender;

    @Override
    @Transactional
    public void testSendMail(String name, String phoneNumber, String emailTo) throws AddressException {
        MailDTO dto = new MailDTO();
        dto.setFrom(new InternetAddress("gogitek.wibu.love.anal@gmail.com").getAddress());
        String subject = String.format("Some one has Name %s and phone number: %s booking room", name, phoneNumber);
        dto.setTo(emailTo);
        dto.setSubject(subject);
        dto.setContent("Fill detail booking information into here");

        sender.send(dto);
    }
}
