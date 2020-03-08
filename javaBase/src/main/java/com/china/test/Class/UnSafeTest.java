package com.china.test.Class;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeTest {
    public static void main(String[] args) {
        Field unsafe = getUnsafe();
        System.out.println(unsafe);
    }

    private static Field getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);

            return theUnsafe;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
