package com.example.booking.config.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {
    String from;
    String to;
    String subject;
    String content;
}
