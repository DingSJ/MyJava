package com.china.letcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3.
 * */
public class NoReString_3 {
    public static void main(String[] args) {
        String str = "abcabcbb";

        lengthOfLongestSubstring(str);
    }

    public static void lengthOfLongestSubstring(String s) {
        byte[] byteStr = s.getBytes();
        Set<Byte> sets = new HashSet<>();
        int max = 1;
        for (int i = 1; i < byteStr.length; i++) {
            int j = i;
            while (j >= 0) {
                if (sets.contains(byteStr[j])) {
                    break;
                }
                sets.add(byteStr[j]);
                j--;
            }
            max = Math.max(max, sets.size());
            System.out.println("sets: " + sets.size());
            System.out.println(max);
            sets.clear();
        }
    }
}
