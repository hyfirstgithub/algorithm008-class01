# 如何优雅地计算斐波那契数列
 · 方法一  暴力解法，递归
 
```java
public  int fibnacci(int n){
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fibnacci(n-2) + fibnacci(n-1);
}
```
这种递归解法进行了大量重复的计算，时间复杂度是2的n次方，效率特别差，因此有了下面的迭代法


· 方法二 迭代法
```java
public  int fibnacci(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    int a1=0,a2=1;
    for(int i = 2; i <= n; i++) {
        int tmp = a2;   
        a2 = a1 + a2;
        a1 = tmp;
    }   
    return a2;
}
```
迭代法使用了两个哨兵分别记录了n-1，和n-2的值，因此减少了大量的重复计算，时间复杂为O(n)

· 方法三 递推公式
  数学家们通过数学推导，直接得出了计算公式，公式如下(想要证明公式，需要大量的前置知识)：
 ```java
public int method3(int n) {
            //递推公式
    double result = (Math.pow((1 + Math.sqrt(5)) / 2, n) - Math.pow((1 - Math.sqrt(5)) / 2, n)) / Math.sqrt(5);
    return (int) Math.round(result);
}
```

方法三的递推公式看似一步到位，以为时间复杂度为O(1)，但其实并不是如此。真正的时间复杂在于Math.power()函数，
其底层实现的时间复杂度为O(logn),但由于涉及到浮点数的运算，真正的时间复杂比O(logn)要高一些的


· 方法四  矩阵的乘法运用
基本思想如下： 
假设第n-1 和 n个斐波那契数 为 a ，b，那么第 n+1 个数可通过下面的矩阵乘法求得

\| a  &nbsp; b \|   * \| 0 &nbsp; 1\|  =  &nbsp; \| b  &nbsp; a+b \|                          
\| 0  &nbsp; 0 \|   &nbsp;  \| 1 &nbsp; 1\|   =  &nbsp; \| 0  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  0  \|

上面矩阵算式等号右边的 a+b 其实就是我们斐波那契数的计算逻辑，因此 第n+1数可写成下面的样子  
\| b  &ensp; a+b \| * \| 0 &ensp; 1\|    ====>   \| a  &ensp; b \|   * \| 0 &ensp; 1\| * \| 0 &ensp; 1\|  
\| 0  &ensp;&ensp;&ensp;  0  \|  &ensp; \| 1 &ensp; 1\|   &ensp;&ensp;&ensp;&ensp;&ensp;&ensp; \| 0 &ensp;  0 \|   &ensp;  \| 1 &ensp; 1\|  &ensp; \| 1 &ensp; 1\|

一般化我们可以得到矩阵的推导公式   
\| 0  &ensp; 1 \| * \| 0 &ensp; 1\| 的n-1次方  
\| 0   &ensp; 0  \|  &ensp; \| 1 &ensp; 1\|

代码如下：
```java
public int fibnacci(int n) {
   if( n<=0) return 0;
   int[][]  a1= {{0,1},{0,0}};
   int[][] a2 = {{0,1},{1,1}};
   return matrix22_mul(a1, matrix22_pow(a2,n-1))[0][1];
}

//求任意矩阵的n次方公式
private int[][] matrix22_pow(int[][] x, int n) {
    int[][] r = {{1, 0}, {0, 1}};
    int[][] v = x;
    while (n > 0) {
        if( n %2 == 1) {
            r = matrix22_mul(r, v);
            n -= 1;
        }
        v = matrix22_mul(v,v);
        n = n/2;
    }
    return r;
}

//矩阵的乘法公式 
private int[][] matrix22_mul(int[][] x, int[][] y) {
    int[][] result = {{x[0][0] * y[0][0] + x[0][1] * y[1][0], x[0][0] * y[0][1] + x[0][1] * y[1][1]},
                    {x[1][0] * y[0][0] + x[1][1] * y[1][0], x[1][0] * y[0][1] + x[1][1] * y[1][1]}};
    return result;
}
```

上面算法中，比较难理解的是matrix22_pow() 求矩阵的n次方的算法，普通的求n次方的算法，
需要做n次相乘的运算。但上面的代码实现只需要做 logn次的乘法运算就可以得到n次方的结果。
其实现的要点是使用了一个哨兵v记录平方之后的积和移位运算。v=v*v，平方积之后对积再平方，这样计算两次就等于做了x的4次方的运算。
2的k次方=n，即k=logn。 原来做n次相乘，现在只需要k次即可。

# 递归

1. 递归代码模板
```java
public void recution(int level, Object params1, params2,...) {
    // recursion terminator
    if ( level > MAX_LEVEL){
        process_result;
        return;
    }
    
    //process logic in current level
    process(level, data...);
    
    // drill down
    self.recursion(level+1,p1,...);
    
    // reverse the current level status if needed

}
```

# 分治

1. 分治代码模板
```java
void divide_conquer(problem, param1, param2, ...){
    // recursion teminator
    if( problem == null){
        print_result;
        return;
    }
    
    // prepare data
    data = prepare_data(problem);
    subProblems = split_problem(problem, data)
    
    // conquer subproblems
    subresult1 = self.divide_conquer(subProblems[0], p1,....);
    subresult1 = self.divide_conquer(subProblems[1], p1,....);
    subresult1 = self.divide_conquer(subProblems[2], p1,....);
    ...
    
    // process and generate the final result
    result = process_result(subresult1, subresult2, subresult3, ....);
    
    // revert the current level states 
}
```


