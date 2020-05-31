学习笔记   动态规划

# 复习前面的知识

  递归代码模板
  
  ```java
public void recur(int level, int param) { 
 // terminator 
 if (level > MAX_LEVEL) { 
 // process result 
     return; 
 } 
 // process current logic 
 process(level, param); 
 // drill down 
 recur( level: level + 1, newParam); 
 // restore current status 
 
}
```

分治代码模板

```java
def divide_conquer(problem, param1, param2, ...): 
# recursion terminator 
if problem is None: 
 print_result 
 return 
# prepare data 
 data = prepare_data(problem) 
 subproblems = split_problem(problem, data) 
# conquer subproblems 
 subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
 subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
 subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
… 
# process and generate the final result 
 result = process_result(subresult1, subresult2, subresult3, …) 
 
 # revert the current level states
```


# 动态规划关键点
 · 共性：找到重复子问题 
 
 · 差异性：最优子结构、中途可以淘汰次优解


1. 最优子结构 opt[n] = best_of(opt[n-1], opt[n-2], …)

2. 储存中间状态：opt[i]

3. 递推公式（美其名曰：状态转移方程或者 DP 方程） 
Fib: opt[i] = opt[n-1] + opt[n-2] 
二维路径：opt[i,j] = opt[i+1][j] + opt[i][j+1] (且判断a[i,j]是否空地）