//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;

//Java：有效的字母异位词
public class P242ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new P242ValidAnagram().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isAnagram(String s, String t) {
            return method3(s,t);
        }

        private boolean method3(String s, String t) {
            // 因为可以假设字母都是小写， 所以可以使用长度为26的数组，进行hash
            //s的字符 命中一个加1, t的字符命中一个-1
            if (s.length() != t.length()) {
                return false;
            }
            int[] counts = new int[26];
            int sum = s.length();
            for (int i = 0; i < s.length(); i++) {
                if(counts[s.charAt(i) - 'a'] ++ < 0){
                    sum--;
                };
                if(counts[t.charAt(i) - 'a'] -- > 0){
                    sum--;
                };
            }

            return sum == 0;
        }

        private boolean method2(String s, String t) {
            // 因为可以假设字母都是小写， 所以可以使用长度为26的数组，进行hash
            // 命中一个加1
            if (s.length() != t.length()) {
                return false;
            }
            int[] counts = new int[26];
            for (int i = 0; i < s.length(); i++) {
                counts[s.charAt(i) - 'a'] ++;
                counts[t.charAt(i) - 'a'] --;
            }
            for(int i=0; i< counts.length;i++){
                if( counts[i] != 0) {
                    return false;
                }
            }
            return true;
        }

        private boolean method1(String s, String t) {
            //1. 先对两个char数组进行排序，然后再比较两个数组是否相等
            if (s.length() != t.length()) {
                return false;
            }
            char[] source = s.toCharArray();
            char[] target = t.toCharArray();
            Arrays.sort(source);
            Arrays.sort(target);
            return Arrays.equals(source, target);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}