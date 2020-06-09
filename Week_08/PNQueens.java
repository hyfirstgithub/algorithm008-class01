//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法


package com.leetCode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：N皇后, 51
public class PNQueens {
    public static void main(String[] args) {
        Solution solution = new PNQueens().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int size;
        private List<List<String>> result = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            size = (1 << n) - 1;
            char[] templates = new char[n];
            for(int i = 0; i < n; i++) {
                templates[i] = '.';
            }
            dodfs(0, 0 , 0, templates, new ArrayList<>());
            return result;
        }

        private void dodfs(int row, int la, int hi, char[] template, List<String> tmp){
            if (row == size) {
                result.add(new ArrayList<>(tmp));
                return;
            }

            int pos = size & (~ (row | la | hi));
            while (pos != 0) {
                int tmpPos = pos & (-pos);
                pos -= tmpPos;
                int p = 0;
                int cout = tmpPos;
                while (cout != 1) {
                   cout = cout >> 1;
                    p++;
                }
                template[p] = 'Q';
                tmp.add(new String(template));
                template[p] = '.';
                dodfs(row | tmpPos, (la | tmpPos) << 1, (hi | tmpPos )>> 1,template, tmp );
                tmp.remove(tmp.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}