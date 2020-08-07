package com.china.letcode;

import org.junit.Test;

public class ArrSort {
    int[] arr = new int[]{1,0,5,3,4,7,9,8,2};

    @Test
    public void bubbleSort(){
        // 升序
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
    public void selectSort(){
        // 升序
        for (int i = 0; i < arr.length; i++) {
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
    public void insertSort(){
        // 升序
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
}
