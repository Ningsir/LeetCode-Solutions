# 目录
*  [1.Single Number(找到数组中只出现一次的整型数字)](#1Single-Number)
* [2.Valid Sudoku(有效数独)](#2Valid-Sudoku)
* [3.Isomorphic String(同构字符串)](#3Isomorphic-String)
* [4.BullsAndCows](#4BullsAndCows)
* [5.Longest-Substring-Without-Repeating-Characters](#5Longest-Substring-Without-Repeating-Characters)
* [6.Copy-List-With-Random-Pointer](#6Copy-List-With-Random-Pointer)
* [7.单词的压缩编码](#7short-encoding-of-words)
# 哈希表
### 1.Single-Number
题目描述
> Given a non-empty array of integers, every element appears twice except for one. Find that single one. 
  Note:  
  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
  
  
  由于题目对时间复杂度和空间复杂度有严格要求，采用暴力法不妥。联想到
  异或运算有如下规律：
 * a ^ 0 = a;
 * a ^ a = 0;
 * 满足交换律
 
 [代码](./src/main/java/hash/SingleNumber.java)
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

### 2.Valid-Sudoku
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

[代码](./src/main/java/hash/ValidSudoku.java)
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

### 3.Isomorphic-String
题目描述
> Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

比如 `s = "foo"` 与 `t = "tee"`是同构的， 而 `t = "ab"` 与 `s = "aa"`不是同构的。即相同位置的字母是互相等价的，能够互换。可以利用 `HashMap` 键值对应的性质来解决此题，即一个键只能对应一个值，如果对应了其他值，则说明两个字符串不是同构的。但需要注意第一次使用 `s`中的字符作为 `key`, 第二次使用 `t` 中的字符作为 `key`。

[代码](./src/main/java/hash/IsomorphicStrings.java)
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

### 4.BullsAndCows
题目描述
>      * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
>       *
>       * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 
>       *
>       * Please note that both secret number and friend's guess may contain duplicate digits.
>       * bulls：位置和数字完全相同
>       * cows：位置不同，但存在该数字
>       * getHint：得到bulls 和 cows 的个数
>       * Example 1:
>       *
>       * Input: secret = "1807", guess = "7810"
>       *
>       * Output: "1A3B"
>       *
>       * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
>       * Example 2:
>       *
>       * Input: secret = "1123", guess = "0111"
>       *
>       * Output: "1A1B"
>       *
>       * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
      
得到 A 的个数比较简单，只需遍历两个字符串并比较相同位置上的字符是否相同即可。
为了获取 B 的个数，可以统计猜错位置每个数字的个数，用一个长度为10的int类型数组表示，下标表示每个数字，对应每个数组值
表示数字的个数。

[代码](./src/main/java/hash/BullsAndCows.java)
```java
public String getHint(String secret, String guess){
        if(secret == null) return null;
        int bull = 0, cow = 0;
        //统计猜错位置上每个数字的个数
        int[] countA = new int[10];
        int[] countB = new int[10];
        for(int i = 0; i < secret.length(); i ++){
            if(secret.charAt(i) == guess.charAt(i)){
                bull++;
            }else{
                countA[secret.charAt(i) - '0']++;
                countB[guess.charAt(i) - '0']++;
            }
        }
        for(int i = 0; i < 10; i++){
            cow += Math.min(countA[i], countB[i]);
        }
        return bull + "A" + cow + "B";

    }
```

### 5.Longest-Substring-Without-Repeating-Characters
[参考链接](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/)
#### 题目描述
> 求解没有重复字母的最长连续子字符串
#### 方法一：暴力法
#### 方法二：滑动窗口法
使用 i、j 表示下标，每次寻找最大 j 满足：s[i]~s[j] 没有重复字母，查询是否有重复字母时可以使用 `HashSet`，
因为`contains()` 操作时间复杂的为`O(1)`.

[代码](./src/main/java/hash/LongestSubString.java)
```java
public int lengthOfLongestSubstring2(String s){
        HashSet<Character> set = new HashSet<>();
        int n = s.length();
        int i = 0;
        int j = 0;
        int res = 0;
        while(i < n && j < n){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                res = Math.max(res, j - i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

```

### 6.Copy-List-With-Random-Pointer
[参考链接](https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/fu-zhi-dai-sui-ji-zhi-zhen-de-lian-biao-by-leetcod/)
复制带有随机指针的链表，节点如下：

```java
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}
```

### 7.short-encoding-of-words
```
给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。

例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。

对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。

那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
```
将words添加进hashset，然后判断每个单词的substring是否在hashset，如果在，则将其删除，因为该单词是某一单词的后缀。
```cpp
int minimumLengthEncoding(vector<string>& words) {
        set<string> tmp(words.begin(), words.end());
        for(auto word : words){
            for(int i = 1; i < word.size(); i++){
                tmp.erase(word.substr(i));
            }
        }
        int res = 0;
        for(auto word : tmp){
            res += word.size() + 1;
        }
        return res;
}
```