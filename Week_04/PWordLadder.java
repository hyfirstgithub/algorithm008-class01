//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
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
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索


package leetcode.editor.cn;

import java.util.*;

//Java：单词接龙
public class PWordLadder {
    public static void main(String[] args) {
        Solution solution = new PWordLadder().new Solution();
        String[] list = {"si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye"};
//        System.out.println(list.length);
//        System.out.println(solution.ladderLength("qa", "sq", Arrays.asList(list)));
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        System.out.println(solution.ladderLength("hit", "cog", wordList));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (beginWord == null || endWord == null || beginWord.length() != endWord.length() || wordList == null || wordList.size() < 1)
                return 0;
            Set<String> set = new HashSet<>(wordList);
            if( !set.contains(endWord)) return 0;
//            method1(beginWord, endWord, wordList, 1, new HashSet<>());
//            return minStep != Integer.MAX_VALUE ? minStep : 0;
//            return method2BFS(beginWord, endWord, wordList);
            return method3TwoSideBFSChange(beginWord, endWord, wordList);
        }

        private int method3TwoSideBFSChange(String beginWord, String endWord, List<String> wordList) {
            wordList.add(beginWord);
            Queue<String> queue1 = new LinkedList<>();
            Set<String> queue1Visited = new HashSet<>();
            Queue<String> queue2 = new LinkedList<>();
            Set<String> queue2Visited = new HashSet<>();
            Set<String> allSet = new HashSet<>(wordList);
            queue1.add(beginWord);
            queue1Visited.add(beginWord);
            queue2.add(endWord);
            queue2Visited.add(endWord);
            boolean[] beginVisited = new boolean[wordList.size()];
            int result = 0;
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                if (queue2.size() < queue1.size()) {
                    Queue<String> tmp = queue1;
                    queue1 = queue2;
                    queue2 = tmp;

                    Set<String> tmpVisited = queue1Visited;
                    queue1Visited = queue2Visited;
                    queue2Visited = tmpVisited;
                }

                int size = queue1.size();
                result++;
                while (size-- > 0) {
                    String word = queue1.poll();
                    char[] currentChar = word.toCharArray();
                    for (int i=0;i < word.length(); i++) {
                        char tmp = currentChar[i];
                        for(char a = 'a'; a<= 'z'; a++) {
                            currentChar[i] = a;
                            String tmpString = new String(currentChar);
                            if( queue1Visited.contains(tmpString)) continue;
                            if( queue2Visited.contains(tmpString)) return result + 1;
                            if ( allSet.contains(tmpString)) {
                                queue1.add(tmpString);
                                queue1Visited.add(tmpString);
                            }
                        }
                        currentChar[i] = tmp;
                    }
                }
            }
            return 0;
        }



        private int method3TwoSideBFS(String beginWord, String endWord, List<String> wordList) {
            wordList.add(beginWord);
            Queue<String> queue1 = new LinkedList<>();
            Set<String> queue1Visited = new HashSet<>();
            Queue<String> queue2 = new LinkedList<>();
            Set<String> queue2Visited = new HashSet<>();

            queue1.add(beginWord);
            queue1Visited.add(beginWord);
            queue2.add(endWord);
            queue2Visited.add(endWord);
            boolean[] beginVisited = new boolean[wordList.size()];
            int result = 0;
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                if (queue2.size() < queue1.size()) {
                    Queue<String> tmp = queue1;
                    queue1 = queue2;
                    queue2 = tmp;

                    Set<String> tmpVisited = queue1Visited;
                    queue1Visited = queue2Visited;
                    queue2Visited = tmpVisited;
                }

                int size = queue1.size();
                result++;
                while (size-- > 0) {
                    String word = queue1.poll();
                    for (int i = 0; i < wordList.size(); i++) {
                        if (queue1Visited.contains(wordList.get(i))) continue;
                        if (judge(word, wordList.get(i))) {
                            if (queue2Visited.contains(wordList.get(i))) {
                                return result + 1;
                            }
                            queue1.add(wordList.get(i));
                            queue1Visited.add(wordList.get(i));
                            beginVisited[i] = true;
                        }
                    }
                }
            }
            return 0;
        }

        private int method2BFS(String beginWOrd, String endWrd, List<String> wordList) {
            boolean[] visited = new boolean[wordList.size()];
            Deque<List<String>> queue = new LinkedList<>();
            List<String> level1 = new ArrayList<>();
            for (int i = 0; i < wordList.size(); i++) {
                if (judge(beginWOrd, wordList.get(i))) {
                    level1.add(wordList.get(i));
                    visited[i] = true;
                }
            }
            if (level1.size() == 0) return 0;
            queue.addFirst(level1);
            int result = 1;
            boolean hasResult = false;
            while (!queue.isEmpty()) {
                result++;
                List<String> lastList = queue.removeLast();
                List<String> nextVisited = new ArrayList<>();
                for (String s : lastList) {
                    if (s.equals(endWrd)) {
                        hasResult = true;
                        break;
                    }
                    for (int i = 0; i < wordList.size(); i++) {
                        if (visited[i]) continue;
                        if (judge(s, wordList.get(i))) {
                            nextVisited.add(wordList.get(i));
                            visited[i] = true;
                        }
                    }
                }
                if (hasResult) break;
                if (nextVisited.size() > 0) queue.addFirst(nextVisited);
            }

            return hasResult ? result : 0;
        }

        private boolean judge(String word1, String word2) {
            int dif = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) dif++;
            }
            if (dif == 1) return true;
            else return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}