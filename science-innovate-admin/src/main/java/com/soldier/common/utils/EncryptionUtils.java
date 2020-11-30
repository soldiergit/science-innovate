package com.soldier.common.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;

/**
 * @Author soldier
 * @Date 20-8-14 上午9:08
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:配置文件加密
 */
public class EncryptionUtils {

    /**
     * Jasypt加密，这里加密redis密码
     * @throws Exception
     */
    @Test
    public void EncryptCode() throws Exception {

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        // 加密的算法，这个算法是默认的
        config.setAlgorithm("PBEWithMD5AndDES");
        // 加密的密钥 这个与yml文件保持一致
        config.setPassword("soldier");
        standardPBEStringEncryptor.setConfig(config);
        // 被加密串
        String plainText = "Panda15677408298";
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

    /**
     * Jasypt解密，这里解密redis密码
     * @throws Exception
     */
    @Test
    public void DeCode() throws Exception {

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        // 加密的算法，这个算法是默认的
        config.setAlgorithm("PBEWithMD5AndDES");
        // 加密的密钥 这个与yml文件保持一致
        config.setPassword("soldier");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "eeI4nzJvYpZ9YXbYHAA6S78zc1sx7SdV";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }

    /**
     * druid自带非对称加密
     * @throws Exception
     */
    @Test
    public void druidEncrypt() throws Exception {
        //密码明文
        String password = "123456";
        System.out.println("明文密码: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];

        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);

        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

        System.out.println("password:" + password);

        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密后:" + decryptPassword);
    }

}
