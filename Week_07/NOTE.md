学习笔记

# Trie 树
    基本概念， 字典树即Trie树，又称单词查找树或键树，是一种树形结构。
    
    典型应用于统计和排序大量的字符串（但又不限于字符串），所以经常被搜索引擎用于
    文本词频统计。
    
    优点： 最大限度地减少无谓的字符串比较，查询效率比哈希表高
    
    基本性质：
    1. 节点本身不存完整单词
    2. 从根节点到某一节点， 路径上经过的字符连接起来，为该节点对应的字符串
    3. 每个节点的所有子节点路径代表的字符都不相同
    
    核心思想
    Trie树的核心思想是空间换时间
    利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。
    
    Trie 树的实现
```java
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

```
 
实战题：单词搜索2
```java
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
```

时间复杂度 ： O(m*m*k + N*k) k为字符串的平均长度， N为字符串数量， m为矩阵的长度
    
# 并查集 Disjoint Set

 使用场景 
 
   - 组团、配对问题
   -  Group or not?
   
 基本操作
   -  makeSet(s): 建立一个新的并查集，其中包含S个单元素集合
   - unionSet(x, y): 把元素X和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并
   - find(x) : 找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，值要将他们各自的代表比较一下就可以了。

java 代码实现    
```java
class UnionFind{
    private int count = 0;
    private int[] parent;
    
    public UnionFind(){
        count = n;
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public  int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        
        return  p;
    }   
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }   


}
```
    
# 剪枝

括号生成， DP解法
```java
class Solution {


    public List<String> generateParenthesis(int n) {
        List<List<String>> result = new ArrayList<>();
        result.add(Arrays.asList(""));
        result.add(Arrays.asList("()"));
        for(int i = 2; i <= n; i++) {
            List<String> tmp = new ArrayList<>();
            for(int left=0, right= i -1; left < i && right >=0; left++, right--) {
                List<String> leftString = result.get(left);
                List<String> rightString = result.get(right);
                for(String s1: leftString) {
                    for(String s2: rightString)  {
                        tmp.add("(" + s1 + ")" + s2);
                    }
                }
            }
            result.add(tmp);
        }

        return result.get(n);
    }
    
    
}
```

# 启发式搜索 A*

代码模板

```
def AstarSearch(graph, start, end):
    pq = collections.priority_queue() # 优先级 -> 估价函数
    pq.append([start])
    visited.add(start)

    while pq:
        node = pq.pop() # can we add more intelligence here?
        visited.add(node)
        
        process(node)
        nodes = generate_related_nodes(node)
        unvisited = [node for node in nodes if node not in visited]
        pq.push(visited)
```
