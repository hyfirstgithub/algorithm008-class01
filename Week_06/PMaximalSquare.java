//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划


package leetcode.editor.cn;
//Java：最大正方形
public class PMaximalSquare{
    public static void main(String[] args) {
        Solution solution = new PMaximalSquare().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {
        //1. 子问题： dp(i-1,j) , dp(i,j-1),dp(i-1,j-1)
        //2. 状态数组 dp[i][j]
        //3. dp方程: dp(i,j) = min(dp(i-1,j) , dp(i,j-1), dp(i-1,j-1) ) + 1;
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n+1][m+1];
        int max= 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j] == '1') {
                    dp[i+1][j+1] = Math.min(Math.min(dp[i+1][j], dp[i][j+1]),dp[i][j]) + 1;
                    max = Math.max(max, dp[i+1][j+1]);
                } else {
                    dp[i+1][j+1] = 0;
                }
            }
        }
        return max * max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}