//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。 
//
// 示例: 
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法


package com.leetCode.leetcode.editor.cn;
//Java：N皇后 II, 52
public class PNQueensIi{
    public static void main(String[] args) {
        Solution solution = new PNQueensIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        int result ;
        int count;

    public int totalNQueens(int n) {
        count = 0;
        result = (1 << n ) - 1;
        dodfs(0,0,0);
        return  count;
    }

    private void dodfs(int row, int la, int hi) {
        if (row == result){
            count++;
            return;
        }

        int pos = result &( ~(row | la | hi) );
        while (pos != 0) {
            int tmp  = (pos & (-pos));
            pos -= tmp;
            dodfs(row | tmp, (la | tmp) << 1, (hi | tmp) >> 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}