package leetcode.editor.cn;

public class StarisUp {
    // 爬楼梯，每次可以走1，2，3步，相临两次的步数不能相同，求最多有多少种走法

    // dp(n,1) = dp(n-1,2) + dp(n-1,3)
    // dp(n,2）= dp(n-2,1) + dp(n-2,3)
    // dp(n,3) = dp(n-3,1) + dp(n-3,2)
    // f(n) = dp(n,1)+ dp(n,2) + dp(n,3)
    public int maxSteps(int n) {
        int[][] memo = new int[n+1][4];
        memo[1][1] = 1;
        memo[2][2] = 1;
        memo[3][1] = 1;
        memo[3][2] = 1;
        memo[3][3] = 1;

        for(int i=4;i <=n ; i++) {
            memo[i][1] = memo[i-1][2] + memo[i-1][3];
            memo[i][2] = memo[i-2][1] + memo[i-2][3];
            memo[i][3] = memo[i-3][1] + memo[i-3][2];
        }

        return memo[n][1] + memo[n][2] + memo[n][3];
    }


}
