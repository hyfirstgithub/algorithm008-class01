//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树


package leetcode.editor.cn;
//Java：实现 Trie (前缀树)
public class PImplementTriePrefixTree{
    public static void main(String[] args) {
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Trie {

        private TrieNode node;

        /** Initialize your data structure here. */
        public Trie() {
            node = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word == null) return;
            TrieNode current = node;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!current.containsKet(ch)) {
                    current.put(ch, new TrieNode());
                }
                current = current.get(ch);
            }
            current.setEnd();
        }

        private TrieNode searchPrefix(String word) {
            if (word == null) return null;
            TrieNode current = node;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.containsKet(ch)) {
                    current = current.get(ch);
                } else {
                    return null;
                }
            }
            return current;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode tmp = searchPrefix(word);
            return tmp != null && tmp.isEnd();
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode tmp = searchPrefix(prefix);
            return tmp != null;
        }

        class TrieNode{
            private TrieNode[] child;
            private boolean isEnd;
            private final int R = 26;

            public TrieNode() {
                child = new TrieNode[R];
            }

            public boolean containsKet(char ch) {
                return child[ch - 'a'] != null;
            }

            public void put(char ch, TrieNode node){
                child[ch - 'a'] = node;
            }

            public TrieNode get(char ch) {
                return child[ch - 'a'];
            }

            public boolean isEnd(){
                return isEnd;
            }

            public void setEnd(){
                isEnd = true;
            }
        }
    }


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}