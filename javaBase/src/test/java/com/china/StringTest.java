package com.china;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class StringTest {
    public static void main(String[] args) {

        String beanName = "&123543543654";
        System.out.println(beanName.substring("&".length()));
    }

    @Test
    public void testMaxStrLength(){
        String str = "abcabcdbb";
        int maxLen = fun(str);
        System.out.println(maxLen);
    }

    private int fun(String str) {
        if (str == null || str.length() == 0){
            return 0;
        }

        int max = 1;
        byte[] strByte = str.getBytes();
        Set<Byte> sets = new HashSet<>();
        for (int i = 1; i < strByte.length; i++) {
            int index = i;
            while (index >= 0 && !sets.contains(strByte[index])) {
                sets.add(strByte[index]);
                index--;
            }
            max = Math.max(max, sets.size());
            sets.clear();
        }

        return max;
    }

    @Test
    public void testSubString(){
        String str = "abcdefghijk";

        System.out.println(str.substring(0,1));
        System.out.println(str.substring(2,5));
    }

    @Test
    public void testCycString(){
//        String str = "cbbd";
        String str = "babad";
        String s = longestPalindrome(str);
        System.out.println(s);
    }

    private String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        if(s.length() == 1){
            return s;
        }
        int max = 1;
        int index = -1;
        byte[] strByte = s.getBytes();
        for(int i = 1;  i < strByte.length; i++) {
            int curr = i, step = 0;
            while((curr - step) >= 0 && (curr + step) < strByte.length){
                if (strByte[curr] == strByte[curr - 1]){
                    if (strByte[curr - step] != strByte[curr] && strByte[curr - step] != strByte[curr + step]) {
                        step++;
                        continue;
                    }
                }else{
                    if (strByte[curr - step] != strByte[curr + step]) {
                        step++;
                        continue;
                    }
                }
                step++;
            }
            if (max < step) {
                max = step;
                index = curr - step + 1;
            }
        }
        System.out.println("index = " + index + ", max = " + max);
        return s.substring(index, index + max);
    }

    @Test
    public void testStrSpilt(){
        String str1 = "asfdsgfd;lrel;kvmvdsva";
        for (int i = 0; i < str1.length(); i++) {
            System.out.println(str1.charAt(i));
        }
    }

    @Test
    public void testStrSpilt2(){
        String str1 = "123435234565444";
        for (int i = 0; i < str1.length(); i++) {
            System.out.println(str1.charAt(i));
        }
    }


    @Test
    public void testStrSpilt3(){
        String str1 = "123435234565444";
        char[] charArr = str1.toCharArray();
        for (int i = 0; i < charArr.length - 1; i++) {
            if (charArr[i] + charArr[i + 1] >= 10)
            System.out.println(charArr[i] + " : " +  charArr[i] + charArr[i + 1]);
        }

        for (int i = 0; i < charArr.length - 1; i++) {
            System.out.println(charArr[i] + charArr[i + 1] + "");
        }
    }


    @Test
    public void testStrByte(){
        String str1 = "123435234565444";
        String str2 = "123435234565444";
        int[] arr1 = strToIntArr(str1);
        int[] arr2 = strToIntArr(str2);
        int res = add(arr1, arr2);
    }

    private int add(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i] + arr2[i]);
        }
        return 0;
    }

    private int[] strToIntArr(String str) {
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = Integer.valueOf(str.charAt(i) + "");
        }
        return arr;
    }


}
