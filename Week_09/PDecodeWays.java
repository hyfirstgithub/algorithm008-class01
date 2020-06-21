//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划


package leetcode.editor.cn;

//Java：解码方法
public class PDecodeWays {
    public static void main(String[] args) {
        Solution solution = new PDecodeWays().new Solution();
        solution.numDecodings("00");
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDecodings(String s) {
//        return digui(s,0);
            return dp2(s);
        }

        private int dp3(String s) {
            if ( s == null )return 0;
            if (s.charAt(0) == '0') return 0;
            if (s.length() == 1) return 1;
            int[] dp = new int[s.length() + 1];
            dp[0] = dp[1] = 1;
            for(int i = 1; i < s.length(); i++) {
                int current = s.charAt(i) - '0';
                int last = s.charAt(i - 1) - '0';
                if ( current == 0) {
                    if (last == 1 || last == 2) {
                        dp[i + 1] = dp[i - 1];
                    } else {
                        return 0;
                    }
                } else {
                    if (last == 1) {
                        dp[i + 1] = dp[i] + dp[i - 1];
                    } else if (last == 2) {
                        if (current <= 6) {
                            dp[i + 1] = dp[i] + dp[i - 1];
                        } else {
                            dp[i + 1] = dp[i];
                        }
                    } else{
                        dp[i + 1] = dp[i];
                    }
                }
            }

            return dp[s.length()];
        }


        private int digui(String s, int start) {
            if (s.length() == start) {
                return 1;
            }
            if (s.charAt(start) == '0') return 0;
            int ans = digui(s, start + 1);
            int help = 0;
            if (start < s.length() - 1) {
                if ((s.charAt(start) - '0') * 10 + (s.charAt(start + 1) - '0') <= 26) {
                    help = digui(s, start + 2);
                }
            }

            return ans + help;
        }

        private int dp1(String s) {
            int[] dp = new int[s.length() + 1];
            dp[s.length()] = 1;
            if (s.charAt(s.length() - 1) == '0') {
                dp[s.length() - 1] = 0;
            } else {
                dp[s.length() - 1] = 1;
            }

            for (int i = s.length() - 2; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    dp[i] = 0;
                    continue;
                }
                int a1 = (s.charAt(i) - '0') * 10;
                int a2 = (s.charAt(i + 1) - '0');
                if (a1 + a2 <= 26) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                } else {
                    dp[i] = dp[i + 1];
                }
            }

            return dp[0];
        }

        //省空间
        private int dp2(String s) {
            int last = 1;
            int before = 0;
            if (s.charAt(s.length() - 1) == '0') {
                before = 0;
            } else {
                before = 1;
            }

            for (int i = s.length() - 2; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    last = before;
                    before = 0;
                    continue;
                }
                int a1 = (s.charAt(i) - '0') * 10;
                int a2 = (s.charAt(i + 1) - '0');
                if ( a1+a2 <= 26 ) {
                    int tmp = before;
                    before = before + last;
                    last = tmp;
                } else {
                    last = before;
                }
            }

            return before;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}