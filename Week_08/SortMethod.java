package com.leetCode.leetcode.editor.cn;

import java.util.Arrays;

public class SortMethod {

    public static void main(String[] args) {
        SortMethod method = new SortMethod();
        int[] nums = {2,1,3,5,4,10,6};
//        method.bubbleSort(nums);
//        method.selectionSort(nums);
        method.insertionSort(nums);
        System.out.println(Arrays.toString(nums));

    }

    /**
     *  冒泡排序
     * @param nums int数组
     */
    public void bubbleSort(int[] nums) {
        if (nums.length == 1) return;
        for(int i = nums.length - 1; i >= 0 ; i--) {
            boolean flag = false;
            for(int j =0 ; j < i; j  ++) {
                if (nums[j] > nums[j+1]){
                    int tmp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = tmp;
                    flag = true;
                }
            }
            if (flag)break;
        }
    }

    /**
     * 选择排序
     * @param nums
     */
    public void selectionSort(int[] nums) {
        if (nums.length == 1) return;

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            for(int j = i + 1; j < nums.length; j++) {
                if (current > nums[j]) {
                    int tmp = current;
                    current = nums[j];
                    nums[j] = tmp;
                }
            }
            nums[i] = current;
        }
    }

    /**
     *  插入排序
     */
    public void insertionSort(int[] nums) {
        if (nums.length == 1) return;
        for(int i = 1 ; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i -1;
            for(; j >= 0; j--) {
                if (nums[j] > tmp){
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j+1] = tmp;
        }
    }


}
