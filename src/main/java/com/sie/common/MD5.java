package com.sie.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @ClassName MD5
 * @Description TODO 加密处理
 * @Author 徐啸儒
 * @Data 2021/7/26 8:36
 * @Version 1.0
 **/
public class MD5 {

    public static String makeMD5(String src){
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
                    '6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串
            //System.out.println(sb);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}