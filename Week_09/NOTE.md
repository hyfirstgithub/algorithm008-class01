学习笔记

# 高级动态规划

    动态规划英文定义
     "Simplifying a complicatd problem by break it down into simple sub-problems"
     
     Dp 顺推模板
        dp = [][]
        for i = 0 .. M{
            for j = 0 .. N {
                dp[i][j] = _Function(dp['i]['j]...)
            }
        }
        return dp[M][N];
        
        
     
     理解关键点：
        动态规划 和 递归 或者分治没有根本上的区别 (关键是看有无最优子结构)
        共同点：找到重复子问题
        
        差异性：最优子结构、中途可以淘汰次优解。
        
        
    
    
    常见的DP题目和状态方程举例
    
  - 爬楼梯问题
   
   递推公式
   f(n) = f(n-1) + f(n-2), f(1) = 1, f(0) = 0
        
  - 不同路径
  
  递推公式
  f(x,y) = f(x-1, y) + f(x, y -1);
  
  - 打家劫舍
  
  dp[i] = max(dp[i - 2 ] + nums[i], dp[i - 1])
  
  
  
  课后作业：
  
  1. https://leetcode-cn.com/problems/min-cost-climbing-stairs/
  2. https://leetcode-cn.com/problems/longest-increasing-subsequence/
  3. https://leetcode-cn.com/problems/decode-ways/
  4. https://leetcode-cn.com/problems/longest-valid-parentheses/
  5. https://leetcode-cn.com/problems/maximal-rectangle/
  6. https://leetcode-cn.com/problems/distinct-subsequences/
  7. https://leetcode-cn.com/problems/race-car/