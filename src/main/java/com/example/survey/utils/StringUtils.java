package com.example.survey.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {

    public static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        return byteToHexString(md.digest());
    }

    /**
     * 바이트를 헥스값으로 변환한다.
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();

        for (byte b:  bytes) {
            builder.append(String.format("%02x",  b));
        }
        return builder.toString();
    }
}