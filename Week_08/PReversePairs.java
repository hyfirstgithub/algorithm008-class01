//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法


package leetcode.editor.cn;
//Java：翻转对
public class PReversePairs{
    public static void main(String[] args) {
        Solution solution = new PReversePairs().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reversePairs(int[] nums) {
        return mergeSor(nums, 0, nums.length - 1);
    }

    private int mergeSor(int[] nums, int left , int right) {
        if (left >= right) return 0;
        int mid = left + (right - left)/2;

        int count = mergeSor(nums, left, mid) + mergeSor(nums, mid + 1, right);
        int[] tmp = new int[right - left + 1];
        int i = left , t = left, c = 0;
        for(int j = mid + 1; j <= right; j++, c++) {
            while (i <= mid && nums[i] <= 2* (long)nums[j]) i++;
            while (t <= mid && nums[t] < nums[j]) tmp[c++] = nums[t++];
            tmp[c] = nums[j];
            count += mid - i + 1;

        }

        while (t <= mid)tmp[c++] = nums[t++];
        System.arraycopy(tmp, 0, nums, left, right - left + 1);
        return count;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}