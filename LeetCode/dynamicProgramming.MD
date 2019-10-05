# Dynamic Programming

## 目录
* [1.Unique BST](#1unique-bst)
* [2.Word Break](#2word-break)
* [3.Perfect Square（完全平方数）](#3perfect-square)

## 简介
> 动态规划（英语：Dynamic programming，简称 DP）是一种在数学、管理科学、计算机科学、经济学和生物信息学中使用的，通过把原问题分解为相对简单的子问题的方式求解复杂问题的方法。 
  动态规划常常适用于有重叠子问题和最优子结构性质的问题，动态规划方法所耗时间往往远少于朴素解法。
  动态规划背后的基本思想非常简单。大致上，若要解一个给定问题，我们需要解其不同部分（即子问题），再根据子问题的解以得出原问题的解。
  通常许多子问题非常相似，为此动态规划法试图仅仅解决每个子问题一次，具有天然剪枝的功能，从而减少计算量：一旦某个给定子问题的解已经算出，则将其记忆化存储，以便下次需要同一个子问题解之时直接查表。这种做法在重复子问题的数目关于输入的规模呈指数增长时特别有用。

### 1.Unique-BST
题目描述：
> Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
(树的总数为：卡特兰数)

[代码](src/main/java/dynamic/UniqueBst.java)

```java
public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new LinkedList<>();
        return generateTrees(1, n);
    }

    /**
     * 生成从first到last的BST
     * @param first
     * @param last
     * @return
     */
    private List<TreeNode> generateTrees(int first, int last){
        List<TreeNode> res = new ArrayList<>();
        if(first > last) {
            //注意：不能return new ArrayList<>();
            res.add(null);
            return res;
        }
        for(int i = first; i <= last; i++){
            List<TreeNode> left = generateTrees(first, i - 1);
            List<TreeNode> right = generateTrees(i + 1, last);
            //生成以i为根节点的BST
            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode t = new TreeNode(i);
                    t.left = l;
                    t.right = r;
                    res.add(t);
                }
            }
        }
        return res;
    }
```

### 2.word-break
#### 2.1 题目描述
> Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.(单词拆分：判断是否能够将字符串拆分成wordDict中的字符串表示)

#### 2.2 思路
遍历字符串，可以将字符串分割成`s[0:i]` 和 `s[i:n]`, 从而将原问题拆分两个子问题，可以将wordDict数组转换为set以方便搜索，

#### 2.3 代码
```python
class Solution:
    def wordBreak(self, s: str, wordDict) -> bool:
        return self.word(s, 0, set(wordDict), [-1] * len(s))
        pass

    # memo[i]表示s从位置i开始的子字符串是否能够由wordDict组成
    def word(self, s, start, word_set, memo):
        if start >= len(s):
            return True
        if memo[start] != -1:
            return memo[start]
        n = len(s)
        i = start
        for i in range(start, n):
            if word_set.__contains__(s[start:i + 1]) and self.word(s, i + 1, word_set, memo):
                memo[i] = True
                return True
        memo[start] = False
        return False
```

### 3.perfect-square
#### 题目描述
> Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.（计算一个数最少可以由多少个完全平方数相加组成）

#### 思路
采用动态规划的方法，递推公式：`dp[i] = min(dp[j] + dp[i - j], dp[i])`
* 方法一：j < i // 2
* 方法二：j < sqrt(i), 然后式子中的`j`用`j ** 2`代替（题目本身就要求要用完全平方数组成），此方法在方法一上有了一定优化

```python
def numSquares(self, n):
        ans = [0] * n
        i = 1
        while i <= n:
            if self.isSquare(i):
                ans[i - 1] = 1
            else:
                curmin = i
                j = 1
                count = 1
                while j < i:
                    curmin = min(1 + ans[i - j - 1], curmin)
                    count += 1
                    j = count ** 2
                ans[i - 1] = curmin
            i += 1
        return ans[n - 1]
        pass

    def isSquare(self, n):
        return math.floor(math.sqrt(n)) ** 2 == n
```