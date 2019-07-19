package hash;

import java.util.ArrayList;

/**
 * @author 宁鑫
 * @date 2019/7/19
 * @time 22:12
 **/
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        ArrayList<Integer>[] rows = new ArrayList[9];
        ArrayList<Integer>[] columns = new ArrayList[9];
        ArrayList<Integer>[] subboxs = new ArrayList[9];
        //初始化
        for (int i = 0; i < 9; i++) {
            rows[i] = new ArrayList<Integer>();
            columns[i] = new ArrayList<Integer>();
            subboxs[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                int cell = board[i][j];
                if (cell == '.') {
                    continue;
                } else {
                    //计算sub-box的下标
                    int sub = (i / 3) * 3 + j / 3;
                    if (rows[i].contains(cell) || columns[j].contains(cell) || subboxs[sub].contains(cell)) {
                        return false;
                    } else {
                        rows[i].add(cell);
                        columns[j].add(cell);
                        subboxs[sub].add(cell);
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] s = {{"5", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"}};
        char[][] boards = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boards[i][j] = s[i][j].charAt(0);
            }
        }
        ValidSudoku validSudoku = new ValidSudoku();
        System.out.println(validSudoku.isValidSudoku(boards));
    }
}
