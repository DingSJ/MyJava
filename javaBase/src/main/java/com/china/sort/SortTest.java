package com.china.sort;

import org.junit.Before;
import org.junit.Test;

public class SortTest {

    public int[] arr;

    @Before
    public void init() {
        arr = new int[]{1, 3, 4, 7, 4, 5, 9, 2, 0, 8};
    }

    @Test
    public void testBubbleSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
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
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }

            if (index != i) {
                int tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    @Test
    public void testInsertSort() {
        for (int i = 1; i < arr.length; i++) {
            int currValue = arr[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && currValue > arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = currValue;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}
