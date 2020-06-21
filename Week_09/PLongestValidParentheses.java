//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划


package leetcode.editor.cn;
//Java：最长有效括号
public class PLongestValidParentheses{
    public static void main(String[] args) {
        Solution solution = new PLongestValidParentheses().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestValidParentheses(String s) {
        if (s.length() < 2) return 0;
        int max = 0;
        int[] dp = new int[s.length()];
        if (s.charAt(0) == '(' && s.charAt(1) == ')'){
            dp[1] = 2;
            max = 2;
        }
        for(int i = 2; i < s.length(); i++ ) {
            if (s.charAt(i) == ')') {
                if ( s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else  {
                    if ( i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                        if ( i - dp[i - 1] - 1 == 0) {
                            dp[i] = dp[i - 1] + 2;
                        } else {
                            dp[i] = dp[i - dp[i - 1] - 2] + 2 + dp[i - 1];
                        }
                    }
                }
            }

            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}