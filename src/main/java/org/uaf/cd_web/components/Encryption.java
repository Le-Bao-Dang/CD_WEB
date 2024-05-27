package org.uaf.cd_web.components;

import java.security.MessageDigest;
import java.util.SplittableRandom;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Encryption {
    public static String toSHA1(String str) {
        String salt = "asfavktrgovndalef";
        String result = null;
        str = str + salt;
        try {
            byte[] dataByte = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String randomSalt() {
        SplittableRandom splittableRandom = new SplittableRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append((char)splittableRandom.nextInt(97,123));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomSalt() + randomSalt());
    }
}
