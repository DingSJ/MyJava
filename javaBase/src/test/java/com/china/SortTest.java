package com.china;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SortTest {

    public int[] arr;

    @Before
    public void init() {
        arr = new int[]{1, 3, 4, 7, 4, 5, 9, 2, 0, 8};
    }

    @Test
    public void testBubbleSort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    @Test
    public void testSelectSort() {

        for (int i = 0; i < arr.length - 1; i++) {
            int curr = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[curr] > arr[j]) {
                    curr = j;
                }
            }
            if (curr != i) {
                int tmp = arr[i];
                arr[i] = arr[curr];
                arr[curr] = tmp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    @Test
    public void testInsertSort() {

        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && curr < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = curr;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    @Test
    public void testStr(){
        String str = "AdsfdsgkerjklgA";
        int len = maxLen(str);
        System.out.println(len);
    }

    private int maxLen(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int max = 1;
        byte[] arr = str.getBytes();
        Set<Byte> byteSet = new HashSet<>();
        for (int i = 1; i < arr.length; i++) {
            int curr = i;
            while (curr >= 0 && !byteSet.contains(arr[curr])) {
                byteSet.add(arr[curr]);
                curr--;
            }
            max = max > byteSet.size() ? max : byteSet.size();
            byteSet.clear();
        }

        return max;
    }

}
