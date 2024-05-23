package org.uaf.cd_web.components;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.SplittableRandom;

@Component
public class RandomOTP {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateOTP(int lengthOTP) {
        SplittableRandom splittableRandom = new SplittableRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lengthOTP; i++) {
            sb.append(splittableRandom.nextInt(1,10));
        }
        return sb.toString();
    }
    public static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}