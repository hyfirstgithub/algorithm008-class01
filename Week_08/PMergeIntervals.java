//给出一个区间的集合，请合并所有重叠的区间。 
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Java：合并区间
public class PMergeIntervals{
    public static void main(String[] args) {
        Solution solution = new PMergeIntervals().new Solution();
        // TO TEST
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] result =solution.merge(intervals);
        System.out.println(Arrays.toString(result));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals.length ==  1)return  intervals;
        List<int[]> result = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] cuurent = intervals[0];
        for(int i = 1; i < intervals.length; i++) {
            int[] tmp = intervals[i];
            if (cuurent[1] >= tmp[0]){
                cuurent[1] = Math.max(cuurent[1], tmp[1]);
            } else {
                result.add(cuurent);
                cuurent = tmp;
            }
        }
        result.add(cuurent);
        int[][] arr = new int[result.size()][2];
        for(int i = 0 ; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}