//编写一个程序，通过已填充的空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// Note: 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法


package leetcode.editor.cn;

import java.util.*;

//Java：解数独
public class PSudokuSolver{
    public static void main(String[] args) {
        Solution solution = new PSudokuSolver().new Solution();
        String[][] rows =  {{"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},{".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},{"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},{".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},{".",".",".",".","8",".",".","7","9"}};
        char[][] board = new char[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                board[i][j] = rows[i][j].charAt(0);
            }
        }

        solution.solveSudoku(board);
        for(int i  = 0; i < 9; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        boolean[][] div = new boolean[9][9];
        boolean[][] row = new boolean[9][9];
        boolean[][] hei = new boolean[9][9];

        private boolean result = false;
    public void solveSudoku(char[][] board) {

       List<int[]> list = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            for(int j = 0 ; j < 9; j++) {
                if (board[i][j] == '.'){
                    int[] pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                    list.add(pos);
                    continue;
                }
                int num = board[i][j] - '1';
                row[i][num] = true;
                hei[j][num] = true;
                int count = (i/3) * 3 + j/3;
                div[count][num] = true;
            }
        }



        doDfs(board, list);

    }

    private void doDfs(char[][] board, List<int[]> deque) {
        if(result) return;
        if (deque.isEmpty()) {
            result = true;
            return;
        }
        int[] pos = deque.remove(deque.size() - 1);
        int box = (pos[0]/3) * 3 + pos[1]/3;
        for(char i = '1'; i <= '9' ; i++) {
            int num = i - '1';
            if (row[pos[0]][num] || hei[pos[1]][num] || div[box][num] ) continue;
            row[pos[0]][num] = true;
            hei[pos[1]][num] = true;
            div[box][num] = true;
            board[pos[0]][pos[1]] = i;

            doDfs(board, deque);

            if (result) break;

            row[pos[0]][num] = false;
            hei[pos[1]][num] = false;
            div[box][num] = false;

        }
        deque.add(pos);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}