package com.interview.algorithm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author qcl
 * @Description
 * @Date 10:19 AM 6/13/2023
 */
public class MD5Example {
    public static void main(String[] args) {
        String input = "Hello, World!";
        String md5Hash = getMD5Hash(input);
        System.out.println("MD5 Hash: " + md5Hash);
    }

    public static String getMD5Hash(String input) {
        try {
            // 创建MD5摘要算法实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算哈希值
            byte[] hashBytes = md.digest(input.getBytes());
            // 转换为十六进制表示
            BigInteger hashNumber = new BigInteger(1, hashBytes);
            String hashString = hashNumber.toString(16);
            // 如果哈希值的长度不足32位，前面补0
            while (hashString.length() < 32) {
                hashString = "0" + hashString;
            }
            return hashString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
