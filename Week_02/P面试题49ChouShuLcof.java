//我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 数学


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//Java：丑数
public class P面试题49ChouShuLcof {
    public static void main(String[] args) {
        Solution solution = new P面试题49ChouShuLcof().new Solution();
        // TO TEST
        System.out.println(solution.nthUglyNumber(10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nthUglyNumber(int n) {
            int a = 0, b = 0, c = 0;
            int[] result = new int[n];
            result[0] = 1;
            for (int i = 1; i < n; i++) {
                int aNum = result[a] * 2;
                int bNum = result[b] * 3;
                int cNum = result[c] * 5;
                int tmp = Math.min(Math.min(aNum, bNum), cNum);
                result[i] = tmp;
                if (tmp == aNum) a++;
                if (tmp == bNum) b++;
                if (tmp == cNum) c++;
            }
            return result[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}