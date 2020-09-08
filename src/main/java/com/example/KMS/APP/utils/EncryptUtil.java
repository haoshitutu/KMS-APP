package com.example.KMS.APP.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Description 加密解密工具类
 * @Author tutu
 * @Date 2020/8/19
 * @Version 1.0
 **/
public class EncryptUtil {

    private static final String SHA1 = "SHA-1";
    private static final Random random = new Random();

    /**
     * 生成指定长度的随机盐值
     * @param length 盐值的长度
     */
    public static byte[] generateSalt(int length){
        Validate.isTrue(length > 0, "length argument must be a positive integer (1 or larger)", length);
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * Hex 加密
     * @param source 需要加密的数据
     * @return hex加密之后的密文
     */
    public static String encodeHex(byte[] source){
        return new String(Hex.encodeHex(source));
    }

    /**
     * Hex 解密
     * @param source 需要解密的数据
     * @return hex解密之后的数据
     */
    public static byte[] decodeHex(String source) throws DecoderException {
        return Hex.decodeHex(source);
    }

    /**
     * sha-1 加密
     * @param source 源数据
     * @param salt 盐值
     * @param ic 计算次数
     * @return 加密结果
     */
    public static byte[] sha1(byte[] source, byte[] salt, int ic) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(SHA1);
        if (salt != null) {
            digest.update(salt);//加入盐，更具有不可破解性
        }
        byte[] result = digest.digest(source);

        for(int i = 1; i < ic; i++){
            digest.reset();
            result = digest.digest(result);
        }
        return result;
    }
}
