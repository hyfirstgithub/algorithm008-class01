//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？ 
//
// 
//
// 示例 1: 
//
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 2: 
//
// 输入: m = 7, n = 3
//输出: 28 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 10 ^ 9 
// 
// Related Topics 数组 动态规划


package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//Java：不同路径
public class PUniquePaths{
    public static void main(String[] args) {
        Solution solution = new PUniquePaths().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] mark = new int[m+1][n+1];
        Deque<Pair> queue = new LinkedList<>();
        Pair p = new Pair();
        p.curM = 1;
        p.curN = 1;
        mark[p.curM][p.curN]++;
        queue.addFirst(p);
        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0){
                Pair tmpP = queue.removeLast();
                if ( tmpP.curM == m && tmpP.curN == n) {
                    continue;
                }
                if(tmpP.curM < m) {
                    Pair p1 = new Pair();
                    p1.curM = tmpP.curM + 1;
                    p1.curN = tmpP.curN;
                    if(mark[p1.curM][p1.curN] == 0){
                        queue.addFirst(p1);
                    }
                    mark[p1.curM][p1.curN] += mark[tmpP.curM][tmpP.curN];
                }
                if(tmpP.curN < n) {
                    Pair p2 = new Pair();
                    p2.curM = tmpP.curM;
                    p2.curN = tmpP.curN + 1;
                    if(mark[p2.curM][p2.curN] == 0) {
                        queue.addFirst(p2);
                    }
                    mark[p2.curM][p2.curN] += mark[tmpP.curM][tmpP.curN];
                }
            }
        }

        return mark[m][n];
    }

        class Pair{
            int curM;
            int curN;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}