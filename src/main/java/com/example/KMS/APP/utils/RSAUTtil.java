package com.example.KMS.APP.utils;

import com.example.KMS.APP.basic.dto.RSAKeyPair;
import com.example.KMS.APP.basic.exception.BusinessException;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

/**
 * @Description RSA加解密工具，用于前后端数据交互
 * RSA加密对明文的长度有所限制，规定需加密的明文最大长度=密钥长度-11（单位是字节，即byte），
 * 所以在加密和解密的过程中需要分块进行。而密钥默认是1024位，即1024位/8位-11=128-11=117字节。
 * 所以默认加密前的明文最大长度117字节，解密密文最大长度为128字。
 * 那么为啥两者相差11字节呢？是因为RSA加密使用到了填充模式（padding），即内容不足117字节时会自动填满，用到填充模式自然会占用一定的字节，而且这部分字节也是参与加密的
 * 密钥长度的设置可以进行修改，随着密钥变长，安全性上升的同时性能也会有所下降
 * @Author tutu
 * @Date 2020/8/20
 * @Version 1.0
 **/
public class RSAUTtil {

    private static final int MAX_ENCRYPT_BLOCK = 117; //RSA最大加密明文大小

    private static final int MAX_DECRYPT_BLOCK = 128; //RSA最大解密密文大小

    /**
     * 获取密钥对
     * @return 密钥对
     */
    public static KeyPair getKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException nsa) {
            throw new BusinessException("获取RSA密钥对失败:" + nsa);
        }
    }

    /**
     * 获取公钥
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] decodeKey = Base64.decodeBase64(publicKey.getBytes());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodeKey);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException nsa) {
            throw new BusinessException("获取RSA密钥处理厂失败:" + nsa);
        } catch (InvalidKeySpecException iks) {
            throw new BusinessException("获取公钥失败:" + iks);
        }
    }

    /**
     * 获取私钥
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] decodeKey = Base64.decodeBase64(privateKey.getBytes());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeKey);
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException nsa) {
            throw new BusinessException("获取RSA密钥处理厂失败:" + nsa);
        } catch (InvalidKeySpecException iks) {
            throw new BusinessException("获取私钥失败:" + iks);
        }
    }

    /**
     * RSA加密
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                 cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
             out.write(cache, 0, cache.length);
             i++;
             offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }

    /**
     * RSA解密
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    /**
     * 获取账号的密钥对
     * @param account 人员账号
     * @param refreshKey 刷新Key标识
     * @return
     */
    public static RSAKeyPair getAccountKey(String account, boolean refreshKey) {
        if (StringUtils.isNotBlank(account)) {
            RSAKeyPair rsaKeyPair = new RSAKeyPair();
            String filePath = RSAUTtil.class.getResource("/rsa/keypair.properties").getPath();

            //检查该账号是否有密钥对
            Properties keyReadProperties = new Properties();
            try {
                keyReadProperties.load(new FileInputStream(filePath));

                String accountKey = (String) keyReadProperties.get(account);
                if (StringUtils.isNotBlank(accountKey)) {
                    //有,取出来
                    JSONObject Object = JSONObject.fromObject(accountKey);
                    rsaKeyPair = (RSAKeyPair) JSONObject.toBean(Object, RSAKeyPair.class);
                }

                if (StringUtils.isBlank(accountKey) || refreshKey) {
                    //没有 或者 刷新，重新生成
                    KeyPair keyPair = getKeyPair();
                    String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
                    String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));

                    rsaKeyPair.setPublicKey(publicKey);
                    rsaKeyPair.setPrivateKey(privateKey);
                    JSONObject object = JSONObject.fromObject(rsaKeyPair);

                    Properties keyWriteProperties = new Properties();
                    keyWriteProperties.setProperty(account, object.toString());
                    keyWriteProperties.store(new FileOutputStream(filePath,true), "Update:" + accountKey);
                }
            } catch (FileNotFoundException fnf) {
                throw new BusinessException("配置文件未找到：" + filePath);
            } catch (IOException io) {
                throw new BusinessException("文件流操作异常：" + io);
            }
            return rsaKeyPair;
        } else {
            throw new BusinessException("获取账号密钥对错误，参数账号为空！");
        }
    }

    public static void main(String[] args) {
        try {
//            // 生成密钥对
//            KeyPair keyPair = getKeyPair();
//            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
//            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
//            System.out.println("私钥:" + privateKey);
//            System.out.println("公钥:" + publicKey);
//            // RSA加密
//            String data = "待加密的文字内容";
//            String encryptData = encrypt(data, getPublicKey(publicKey));
//            System.out.println("加密后内容:" + encryptData);
//            // RSA解密
//            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
//            System.out.println("解密后内容:" + decryptData);
//
//            // RSA签名
//            String sign = sign(data, getPrivateKey(privateKey));
//            // RSA验签
//            boolean result = verify(data, getPublicKey(publicKey), sign);
//            System.out.print("验签结果:" + result);

            getAccountKey("yuanyl001",false);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
