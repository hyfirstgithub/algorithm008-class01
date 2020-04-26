//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：N叉树的层序遍历
public class P429NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P429NAryTreeLevelOrderTraversal().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
// Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            return curision(root);
        }

        private List<List<Integer>> curision(Node root) {
            List<List<Integer>> result = new ArrayList<>();
            if(root == null) return result;
            doCurision(Arrays.asList(Arrays.asList(root)), result);
            return result;
        }

        private void doCurision(List<List<Node>> root, List<List<Integer>> result) {
            if (root == null || root.size() == 0) return;
            List<List<Node>> nextRoot = new ArrayList<>();
            List<Integer> thisList = new ArrayList<>();
            for (List<Node> tmpList : root) {
                for (Node node : tmpList) {
                    thisList.add(node.val);
                    if (node.children != null) {
                        nextRoot.add(node.children);
                    }
                }
            }
            if( thisList.size() > 0) {
                result.add(thisList);
            }
            doCurision(nextRoot, result);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}