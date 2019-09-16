package com.vainglory.utils;

import java.util.Base64;
public class Base64Utils {
    //加密
    static String encode(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }
    //解密
    static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }
}
