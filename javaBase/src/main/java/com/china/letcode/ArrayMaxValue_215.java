package com.china.letcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，
 * 你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * */
public class ArrayMaxValue_215 {

    /**
     * 方法一：
     * 1. 先排序
     * 2. 获取数据
     *  */
    @Test
    public void test_1(){
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int res = findKthLargest(nums, 3);
        System.out.println(res);
    }

    public int findKthLargest(int[] nums, int k) {
        sort(nums);
        return nums[k-1];
    }

    public int[] sort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int currValue = arr[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && currValue > arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = currValue;
        }

        return arr;
    }


    /**
     * 方法二
     * 冒泡排序
     * */
    @Test
    public void test_2(){
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int res = findMasValue(nums, 4);
        System.out.println(res);
    }

    /**
     * [3,2,3,1,2,4,5,5,6]
     * 4
     * */

    private int findMasValue(int[] nums, int k) {

        for (int i = 0; i < k; i++) {
            int curr = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[curr] < nums[j]) {
                    curr = j;
                }
            }
            if (curr != i) {
                int tmp = nums[i];
                nums[i] = nums[curr];
                nums[curr] = tmp;
            }
        }

        return nums[k-1];
    }
}
