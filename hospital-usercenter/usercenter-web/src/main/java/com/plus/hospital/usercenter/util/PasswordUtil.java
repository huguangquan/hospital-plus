package com.plus.hospital.usercenter.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 密码工具类
 *
 * @author huguangquan
 * 2023/6/6
 **/
public class PasswordUtil {
    private static final String salt_strings = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * AES加解密的秘钥key，长度必须是16位或其倍数
     */
    private static final String password_sign_key = "hospital-plus-ch";

    private static final AES aes = new AES(Mode.ECB, Padding.ZeroPadding,
            new SecretKeySpec(password_sign_key.getBytes(), "AES"));

    /**
     * 生成密码加密盐值，6位英文+数字
     *
     * @return
     */
    public static String generateString(int length) {
        return RandomUtil.randomString(salt_strings, length);
    }

    /**
     * 生成加密后的密码
     *
     * @param password
     * @param salt
     * @return
     */
    public static String generateEncodePassword(String password, String salt) {
        return DigestUtil.md5Hex(password + salt);
    }

    /**
     * 验证密码是否正确
     *
     * @param password
     * @param salt
     * @param encodePassword
     * @return
     */
    public static boolean validatePassword(String password, String salt, String encodePassword) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(salt) || StringUtils.isEmpty(encodePassword)) {
            return false;
        }
        String tempEncodePassword = generateEncodePassword(password, salt);
        if (Objects.equals(tempEncodePassword, encodePassword)) {
            return true;
        }
        return false;
    }

    /**
     * 签名前后端交互的密码，例如：注册、登录、修改密码场景下的密码传输
     *
     * @param password
     * @return
     */
    public static String signPassword(String password) {
        return aes.encryptBase64(password, StandardCharsets.UTF_8);
    }

    /**
     * 签名密码解签
     *
     * @param signPassword
     * @return
     */
    public static String unSignPassword(String signPassword) {
        try {
            return aes.decryptStr(signPassword);
        } catch (Exception exception) {
            return null;
        }
    }

    public static void main(String[] args) {
//        String salt = generateString(6);
//        System.out.println("salt=" + salt);
//        String pass = generateEncodePassword("12345678", salt);
//        System.out.println(pass);

//        String salt= "fZsFrZ";
//        String encodePass = "844433947a79a9d2a75ae1b6b4683ffd";
        String pass = "jya3NMfD";
//        boolean validateRst = validatePassword(pass, salt, encodePass);
//        System.out.println(validateRst);

        String signPass = signPassword(pass);
        System.out.println(signPass);
        String temp = unSignPassword(signPass);
        System.out.println(temp);
    }
}
