package com.china;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {

        Map map = Collections.synchronizedMap(new HashMap());

        map.put(null, null);
        map.put(null, new Object());
        map.put(new Object(), null);

        System.out.println(map.get(null));
        System.out.println(map.size());


        System.out.println(null == null);
        System.out.println("" == "");
        System.out.println("".equals(""));
        System.out.println("" == null);

        Integer i1 = 2;
        Integer i2 = 2;
        Integer i3 = new Integer(3);
        int i4 = 2;

        System.out.println("NUM: ");
        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println(i1 == i4);


        System.out.println("String:  ");
//        String str1 = new String("ASDFFSDFSDFSD");
        String str2 = "ASDFFSDFSDFSD";
        String str3 = "ASDFFSDFSDFSD";
//        System.out.println(str1 == str2);
        System.out.println(str3 == str2);

        float a = 123.001f;

        System.out.println("a:" + (a == a));

        System.out.println("tableSizeFor: " + tableSizeFor(3));
        System.out.println("| : " + (1 | 2));


        for (int binCount = 0; ; ++binCount) {
            System.out.println(binCount);
        }
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 10) ? 10 : n + 1;
    }
}
