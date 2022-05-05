package com.example.booking.controller.api;

import com.example.booking.config.dto.CustomResponse;
import com.example.booking.controller.req.MailInfoReq;
import com.example.booking.service.DemoSendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class DemoSendMailController {
    @Autowired
    DemoSendMailService demoSendMailService;

    @Autowired
    CustomResponse response;
    @PostMapping("send")
    public ResponseEntity<?> testSendMail(@RequestBody MailInfoReq req){
        try{
            demoSendMailService.testSendMail(req.getName(), req.getPhoneNumber(), req.getEmailTo());
            return response.successResp("Success", HttpStatus.OK);
        }catch (Exception ex){
            return response.infoResp("Something went wrong when sending mail!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
