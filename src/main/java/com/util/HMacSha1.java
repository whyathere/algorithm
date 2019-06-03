package com.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HMacSha1 {
    public HMacSha1() {
    }

    public static String HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes("UTF-8");
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("UTF-8");
        return bytesToHex(mac.doFinal(text));
    }

    public static String bytesToHex(byte[] bytes) {
        char[] HEX_CHAR = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        byte[] var4 = bytes;
        int var5 = bytes.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte b = var4[var6];
            buf[index++] = HEX_CHAR[b >>> 4 & 15];
            buf[index++] = HEX_CHAR[b & 15];
        }

        return new String(buf);
    }
}
