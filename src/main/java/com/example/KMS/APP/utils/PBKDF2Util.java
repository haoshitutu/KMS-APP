package com.example.KMS.APP.utils;

import org.apache.commons.codec.DecoderException;

import java.security.NoSuchAlgorithmException;

/**
 * @Description PBKDF2 加密，解密工具类
 * 多次hash来对密码进行加密
 * @Author tutu
 * @Date 2020/8/19
 * @Version 1.0
 **/
public class PBKDF2Util {

    /**
     * 加密操作
     * @param plain 明文
     * @return String 密文
     */
    public static String encode(String plain) throws NoSuchAlgorithmException {
        //1.生成随机盐salt
        byte[] salt = EncryptUtil.generateSalt(32);
        //2.随机盐HEX加密
        String encodeHex = EncryptUtil.encodeHex(salt);
        //3.明文和随机盐一起SHA1运算1024次
        byte[] sha1 = EncryptUtil.sha1(plain.getBytes(), salt, 1024);
        //4.对运算结果进行HEX加密
        String ciperText = EncryptUtil.encodeHex(sha1);
        //4.加密后的随机盐和加密后的明文生成密文
        String finalText = encodeHex + ciperText;
        return finalText;
    }

    /**
     * PBKDF2解密匹配
     * @param plain 明文
     * @param ciper 密文
     * @return flag 对比结果
     */
    public static boolean decode(String plain,String ciper) throws DecoderException, NoSuchAlgorithmException {
        boolean flag=false;
        //1.截取随机盐
        String saltHex = ciper.substring(0, 32*2);
        //2.随机盐解密
        byte[] salt = EncryptUtil.decodeHex(saltHex);
        //3.明文和随机盐一起SHA1运算1024次
        byte[] sha1 = EncryptUtil.sha1(plain.getBytes(), salt, 1024);
        //4.对运算结果进行HEX加密
        String ciperText = EncryptUtil.encodeHex(sha1);
        //5.生成密文
        String finalCiper=saltHex+ciperText;
        if(finalCiper.equals(ciper)) {
            flag=true;
        }
        return flag;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, DecoderException {
        System.out.println(PBKDF2Util.encode("5821haoshi"));
        System.out.println(PBKDF2Util.decode("5821haoshi", "9e1855a91767b8bb692f4017aff3738cfba7423dda39700be2b2031d54de3aae5301bf1d9ea70227b32aab0ab9a978e3d3442b2e"));


    }
}
