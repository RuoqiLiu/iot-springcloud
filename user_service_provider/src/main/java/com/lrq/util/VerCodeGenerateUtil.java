package com.lrq.util;

import java.security.SecureRandom;
import java.util.Random;

public class VerCodeGenerateUtil {
    private static final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();
    private static final String KEYSYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * 生成6位随机数字字母
     * @return 返回6位激活码
     */
    public static String generateVerCode() {
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    //生成10位随机userkey
    public static String generateUserKey(){
        char[] nonceChars = new char[10];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = KEYSYMBOLS.charAt(RANDOM.nextInt(KEYSYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    public static void main(String[] args) {
        System.out.println( );
    }

}
