package com.example.KMS.APP.basic.dto;

import lombok.Data;

/**
 * @Description RSA密钥对
 * @Author tutu
 * @Date 2020/8/20
 * @Version 1.0
 **/
@Data
public class RSAKeyPair {

    private String publicKey;

    private String privateKey;
}
