package com.example.booking.service;

import javax.mail.internet.AddressException;

public interface DemoSendMailService {
    void testSendMail(String name, String phoneNumber, String emailTo) throws AddressException;
}
