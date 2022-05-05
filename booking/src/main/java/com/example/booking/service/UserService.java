package com.example.booking.service;

import com.example.booking.controller.req.ChangePasswordReq;
import com.example.booking.controller.req.UserInfoReq;
import com.example.booking.controller.req.UserRegisterReq;
import com.example.booking.controller.res.UserInfoRes;
import com.example.booking.entity.User;


public interface UserService {
    void registerUser(UserRegisterReq req);

    User getCurrentUser();
}
