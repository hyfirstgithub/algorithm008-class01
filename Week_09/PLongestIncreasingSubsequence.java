//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划


package leetcode.editor.cn;
//Java：最长上升子序列
public class PLongestIncreasingSubsequence{
    public static void main(String[] args) {
        Solution solution = new PLongestIncreasingSubsequence().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length < 1) return 0;
        int[] dp = new int[nums.length];
        int max = 1;
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++ ) {
            dp[i] = 1;
            int j = i - 1;
            for(; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (j < dp[i]) break;
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}