package com.china.letcode;

import org.junit.Test;

public class FindNum_4 {

    @Test
    public void test() {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};

        double res = fun(nums1, nums2);
        System.out.println(res);
    }

    private double fun(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            arr[i] = nums1[i];
        }

        for (int i = 0; i < nums2.length; i++) {
            int curr = nums2[i];
            int preIndex = nums1.length - 1 + i;
            while (preIndex >= 0 && curr < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = curr;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("\n");

        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length % 2 == 0) {
            return ((double) (arr[arr.length / 2 - 1] + arr[arr.length / 2])) / 2.0D;
        } else {
            return arr[arr.length / 2];
        }
    }
}