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
\| a   b \|   * \| 0  1\|  =  \| b   a+b \|
\| 0   0 \|     \| 1  1\|     \| 0    0  \|

上面矩阵算式等号右边的 a+b 其实就是我们斐波那契数的计算逻辑，因此 第n+1数可写成下面的样子
\| b   a+b \| * \| 0  1\|    ====>   \| a   b \|   * \| 0  1\| * \| 0  1\|
\| 0    0  \|   \| 1  1\|            \| 0   0 \|     \| 1  1\|   \| 1  1\|

一般化我们可以得到矩阵的推导公式
\| b   a+b \| * \| 0  1\| 的n次方
\| 0    0  \|   \| 1  1\|



