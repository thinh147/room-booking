package com.example.booking.controller.req;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReq {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
