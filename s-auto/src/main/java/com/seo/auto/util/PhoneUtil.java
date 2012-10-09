package com.seo.auto.util;

import java.util.Random;

public class PhoneUtil {
    public static String generatePhone() {
        Random random = new Random();
//        +7 89 312 33 15
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append("+7 ");
        sbuffer.append(random.nextInt(80) + 10);
        sbuffer.append(" ");
        sbuffer.append(random.nextInt(800) + 100);
        sbuffer.append(" ");
        sbuffer.append(random.nextInt(80) + 10);
        sbuffer.append(" ");
        sbuffer.append(random.nextInt(80) + 10);

        return sbuffer.toString();
    }
}
