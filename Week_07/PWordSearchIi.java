//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Java：单词搜索 II
public class PWordSearchIi{
    public static void main(String[] args) {
        Solution solution = new PWordSearchIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<String> result = new ArrayList<>();
        private Set<String> set = new HashSet<>();
        private int[] nAdd = {1,0,-1,0};
        private int[] mAdd = {0,1,0,-1};

        public List<String> findWords(char[][] board, String[] words) {
            if (words == null) return new ArrayList<>();
            Trie trie = new Trie();
            for(String s: words) {
                trie.insert(s);
            }

            TrieNode root = trie.getRoot();

            int n = board.length;
            int m = board[0].length;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    dodfs(root, i, j, n, m, board);
                }
            }

            return result;
        }

        private void dodfs(TrieNode root, int i, int j, int n, int m,char[][] board) {
            if ( i < 0 || i >= n) return;
            if (j < 0 || j >= m) return;
            if (board[i][j] == '@') return;
            TrieNode node = root.get(board[i][j]);
            if (node == null) return;

            if (node.isEnd()) {
                String word = node.getEndWord();
                if(!set.contains(word)) {
                    result.add(word);
                    set.add(word);
                }
            }
            char tmp = board[i][j];
            board[i][j] = '@';
            for(int h = 0; h < 4; h++){
                dodfs(node, i + nAdd[h], j + mAdd[h], n, m, board);
            }
            board[i][j] = tmp;

        }

        class Trie{
            private TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void insert(String word){
                if (word == null) return;
                TrieNode node = root;
                for(int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    if (!node.containsKey(ch)) {
                        node.put(ch, new TrieNode());
                    }
                    node = node.get(ch);
                }
                node.setEnd();
                node.setEndWord(word);
            }

            public TrieNode getRoot() {
                return root;
            }
        }


        class TrieNode{
            private TrieNode[] root;
            private final int R = 26;
            private boolean end;
            private String endWord;

            public TrieNode() {
                root = new TrieNode[R];
            }

            public TrieNode get(char ch) {
                return root[ch - 'a'];
            }

            public boolean containsKey(char ch) {
                return root[ch - 'a'] != null;
            }

            public void put(char ch, TrieNode node) {
                root[ch - 'a'] = node;
            }

            public boolean isEnd() {
                return end;
            }

            public void setEnd() {
                end = true;
            }

            public void setEndWord(String word) {
                endWord  = word;
            }

            public String getEndWord() {
                return endWord;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}