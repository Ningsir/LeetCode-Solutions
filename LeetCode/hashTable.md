# 哈希表
### 1. Single Number
题目描述
> Given a non-empty array of integers, every element appears twice except for one. Find that single one. 
  Note:  
  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
  
  
  由于题目对时间复杂度和空间复杂度有严格要求，采用暴力法不妥。联想到
  异或运算有如下规律：
 * a ^ 0 = a;
 * a ^ a = 0;
 * a ^ b = 1;(a != b)
 * 满足交换律
```java
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i : nums){
            res ^= i;
        }
        return res;
    }
}
    
```

### 2. Valid Sudoku
题目描述
> Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
  Each row must contain the digits 1-9 without repetition.
  Each column must contain the digits 1-9 without repetition.
  Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
  A partially filled sudoku which is valid.
  The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

此题用来简单判断数独是否有效，只需要判断每行、每列及每个子数独是否存在重复数字。
所以只需要分别将上述出现的情况用list存储，判断是否出现重复数字即可。其中子数独
的下标可以如此表示`(row / 3) * 3 + column / 3`。
```java
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
}

```