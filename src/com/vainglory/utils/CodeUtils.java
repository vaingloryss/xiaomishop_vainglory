package com.vainglory.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CodeUtils {
    public static String getCode(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s1=sdf.format(date);
        String s2=Integer.toHexString(new Random().nextInt(900)+100);
        return s1+s2;
    }
}
