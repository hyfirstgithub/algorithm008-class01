//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法


package leetcode.editor.cn;

import java.util.*;

//Java：单词接龙 II
public class PWordLadderIi {
    public static void main(String[] args) {
        Solution solution = new PWordLadderIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            if (beginWord == null || endWord == null || beginWord.length() != endWord.length() || wordList == null || wordList.size() < 1) {
                return new ArrayList<>();
            }
            Map<String, Integer> distance = new HashMap<>();
            Map<String, List<String>> neibers = new HashMap<>();
            doBfs(beginWord, endWord, new HashSet<>(wordList), distance, neibers);
            List<String> parents = new ArrayList<>();
            parents.add(beginWord);
            doDfs(beginWord, endWord, distance, parents, neibers);
            return result;
        }

        private List<List<String>> result = new ArrayList<>();

        private void doDfs(String beginString, String endString, Map<String, Integer> distance, List<String> parents, Map<String, List<String>> neibers) {
            if (beginString.equals(endString)) {
                result.add(new ArrayList<>(parents));
                return;
            }

            List<String> nerber = neibers.getOrDefault(beginString, new ArrayList<>());
            for (
                    String s : nerber) {
                if (distance.get(beginString) + 1 != distance.get(s)) continue;
                parents.add(s);
                doDfs(s, endString, distance, parents, neibers);
                parents.remove(parents.size() - 1);
            }
        }

        private void doBfs(String beginString, String endString, Set<String> wordList, Map<String, Integer> distance, Map<String, List<String>> neibers) {
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginString);
            distance.put(beginString, 0);
            int depth = 0;
            while (!queue.isEmpty()) {
                depth++;
                int size = queue.size();
                while (size-- > 0) {
                    String currentPos = queue.poll();
                    List<String> neber = findNeibores(currentPos, wordList);
                    if (neber.size() > 0) neibers.put(currentPos, neber);
                    for (String s : neber) {
                        if (!distance.containsKey(s)) {
                            distance.put(s, depth);
                            if (endString.equals(currentPos)) return;
                            queue.offer(s);
                        }
                    }
                }
            }
        }

        private List<String> findNeibores(String origin, Set<String> wordSet) {
            char[] chars = origin.toCharArray();
            List<String> result = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                char tmp = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String tmpString = new String(chars);
                    if (wordSet.contains(tmpString)) {
                        result.add(tmpString);
                    }
                }
                chars[i] = tmp;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}