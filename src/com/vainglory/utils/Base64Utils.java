package com.vainglory.utils;

import java.util.Base64;

/*
 * wgy 2019/9/10 16:41
 * 佛祖保佑，永无BUG!
 */
public class Base64Utils {
    //加密
    public static String encode(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }
    //解密
    public static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }
}
