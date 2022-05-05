package com.example.booking.controller.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailInfoReq {
    String name;
    String phoneNumber;
    String emailTo;
}
