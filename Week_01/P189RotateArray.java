//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组


package leetcode.editor.cn;

import java.util.Arrays;

//Java：旋转数组
public class P189RotateArray {
    public static void main(String[] args) {
        Solution solution = new P189RotateArray().new Solution();
        // TO TEST
        int[] nums = {1,2,3,4,5,6,7};
        solution.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[] nums, int k) {
            method3(nums, k);
        }

        private void method1(int[] nums, int k) {
            //空间复杂度为O(n)，不满足题意
            int[] tmp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int pos = (i + k) % nums.length;
                tmp[pos] = nums[i];
            }
            for (int j = 0; j < nums.length; j++) {
                nums[j] = tmp[j];
            }
        }


        private void method2(int[] nums, int k) {
            //暴力解法，转k圈
            int previous, current;
            for (int i = 0; i < k; i++) {
                previous = nums[nums.length - 1];
                for (int j = 0; j < nums.length; j++) {
                    current = nums[j];
                    nums[j] = previous;
                    previous = current;
                }
            }
        }

        private void method3(int[] nums, int k) {
            int skipNum = k % nums.length;
            int count = 0;
            int previous, current;
            for (int start = 0; count < nums.length; start++) {
                previous = nums[start];
                int n = start;
                do {
                    n = (n + skipNum) % nums.length;
                    current = nums[n];
                    nums[n] = previous;
                    previous = current;
                    count++;
                }while (start != n);
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}