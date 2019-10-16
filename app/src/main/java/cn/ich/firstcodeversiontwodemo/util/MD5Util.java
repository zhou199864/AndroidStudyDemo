package cn.ich.firstcodeversiontwodemo.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    /**
     * 利用MD5进行加密
     * @param str 要加密得字符串
     * @return  加密后的字符串
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md = MessageDigest.getInstance("MD5");
        //加密后的字符串
        String encryptionStr = Base64.encodeToString(md.digest(str.getBytes("UTF-8")),Base64.DEFAULT);
        return encryptionStr;
    }

    /**
     * 验证密码是否正确
     * @param inputPassword 用户输入的密码
     * @param sqlPassword   sql中的密码
     */
    public static boolean checkPassword(String inputPassword,String sqlPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(inputPassword).equals(sqlPassword))
            return true;
        else
            return false;
    }
}
