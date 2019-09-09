[Toc]

# 哈希表
### 1. Single Number(找到数组中只出现一次的整型数字)
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

### 2. Valid Sudoku(有效数独)
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

### 3. Isomorphic String(同构字符串)
题目描述
> Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

比如 `s = "foo"` 与 `t = "tee"`是同构的， 而 `t = "ab"` 与 `s = "aa"`不是同构的。即相同位置的字母是互相等价的，能够互换。可以利用 `HashMap` 键值对应的性质来解决此题，即一个键只能对应一个值，如果对应了其他值，则说明两个字符串不是同构的。但需要注意第一次使用 `s`中的字符作为 `key`, 第二次使用 `t` 中的字符作为 `key`。
```java
public  boolean isIsomorphic(String s, String t){
       return isomorphic(s, t) && isomorphic(t, s);
    }

    public boolean isomorphic(String s, String t){
        if(s.length() != t.length()) return false;
        HashMap<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i)) && !map.get(s.charAt(i)).equals(t.charAt(i)))
                return false;
            else{
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }
```
