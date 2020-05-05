//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：从前序与中序遍历序列构造二叉树
public class PConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args) {
        Solution solution = new PConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
// * Definition for a binary tree node.
//  public class TreeNode {
//      int val;
//      TreeNode left;
//      TreeNode right;
//      TreeNode(int x) { val = x; }
//  }

class Solution {
        private int[] truePreOrder;
        private int[] trueInOrder;
        private Map<Integer,Integer> inorderMap = new HashMap<>();
        private int rootPos;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        rootPos = 0;
        truePreOrder = preorder;
        trueInOrder = inorder;
        int inPos = 0;
        for(Integer i: inorder){
            inorderMap.put(i, inPos++);
        }
        return doBuildTree(0, inorder.length);
    }

    private TreeNode doBuildTree(int in_left, int in_right){
        if( in_left == in_right) return null;
        TreeNode root = new TreeNode(truePreOrder[rootPos]);
        int inorderPos = inorderMap.get(truePreOrder[rootPos]);
        rootPos++;
        root.left = doBuildTree(in_left, inorderPos);
        root.right = doBuildTree(inorderPos+1, in_right);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}