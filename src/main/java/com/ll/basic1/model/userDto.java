package com.ll.basic1.model;

import lombok.Data;

@Data
public class userDto {

    String resultCode;
    String msg;

    public userDto(String resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
    }
}
