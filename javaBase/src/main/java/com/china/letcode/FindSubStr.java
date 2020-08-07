package com.china.letcode;


import org.junit.Test;

/**
 * 寻找子串
 * */
public class FindSubStr {

    @Test
    public void test(){
        String str1 = "abcdABEsdfdsgfg";
        String str2 = "ABEA";

        int index = startIndex(str1,str2);
        System.out.println(index);
    }

    public int startIndex(String target, String sub) {
        int index = -1;
        if (sub.length() == 0 || sub.length() > target.length()) {
            return -1;
        }
        byte[] byteTarget = target.getBytes();
        byte[] byteSub = sub.getBytes();
        for (int i = byteSub.length - 1; i < byteTarget.length; i++) {
            int t = i;
            int s = byteSub.length - 1;
            while (s >= 0 && byteTarget[t] == byteSub[s]) {
                if (s == 0) {
                    return t;
                }
                s--;
                t--;
            }
        }
        return index;
    }
}
