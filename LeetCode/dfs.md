## DFS
### 目录
* [1.二叉树原地展开为链表](#1flatten-binary-tree-to-a-linked-list)
* [2.步进数](#2stepping-number)
* [3.填充二叉树每个节点的next指针](#3populate-next-pointer)

### 深度优先算法
对于二叉树的深度优先算法，其实就是先序遍历
```python
def preorderTraversal(self, root: TreeNode) -> List[int]:
        if root is None:
            return []
        stack = [root]
        ans = []
        while len(stack) != 0:
            node = stack.pop()
            ans.append(node.val)
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
        return ans
```
### 1.flatten-binary-tree-to-a-linked-list
#### 题目描述
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:


    1
   / \
  2   5
 / \   \
3   4   6

The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

#### 思路
原地展开，没有返回值，可以采用后序遍历
```python
class Solution:
    def flatten(self, root: TreeNode) -> None:
        """
        Do not return anything, modify root in-place instead.
        原地展开为链表，采用后序遍历
        """
        if root is None:
            return
        self.flatten(root.left)
        self.flatten(root.right)
        tmp = root.right
        root.right = root.left
        root.left = None
        while root.right is not None:
            root = root.right
        root.right = tmp
        
```

### 2.stepping-number
#### 题目描述
> 如果一个整数上的每一位数字与其相邻位上的数字的绝对差都是 1，那么这个数就是一个「步进数」。
例如，321 是一个步进数，而 421 不是。
给你两个整数，low 和 high，请你找出在 [low, high] 范围内的所有步进数，并返回 排序后 的结果。

#### 分析
比如说2是一个步进数，那么下一个以2开头的两位数应该是21 和 23（即 `21 = 2 * 10 + 2 - 1`, `23 = 2 * 10 + 2 + 1`）, 可以从中发现另外3~8也满足该规律，注意一下1 和 9两个边界情况即可。可以采用深度优先搜索解决问题，依次搜索以某个步进数为开头的下一个步进数。
```python
class Solution:
     
    hashset = set()

    def countSteppingNumbers(self, low: int, high: int):
        self.hashset.add(0)
        for i in range(1, 10):
            self.hashset.add(i)
            self.bfs(i, high)
        ans = []
        for i in sorted(list(self.hashset)):
            if i > high:
                break
            if low <= i <= high:
                ans.append(i)
        return ans
        pass
    # 搜索以low为高位的下一步进数，直到大于high为止
    def bfs(self, low, high):
        if low >= high:
            return
        tail = low % 10 # 尾数

        #下一个步进数的尾数为 tail-1 或者tail+1
        if tail == 0:
            self.hashset.add(low * 10 + tail + 1)
            self.bfs(low * 10 + tail + 1, high)
        elif tail == 9:
            self.hashset.add(low * 10 + tail - 1)
            self.bfs(low * 10 + tail - 1, high)
        else:
            self.hashset.add(low * 10 + tail - 1)
            self.hashset.add(low * 10 + tail + 1)
            self.bfs(low * 10 + tail - 1, high)
            self.bfs(low * 10 + tail + 1, high)
        
```

### 3.populate-next-pointer
#### 题目描述
> You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```
>Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.
(填充一棵完美二叉树（完全填充满的二叉树）的next指针，即用next指针从左至右连接树中每层的节点)

#### 分析
只需要将左右子树相邻的节点用next指针连接起来，又因为是完美二叉树，只需对左子树一直向右儿子遍历，对右子树一直向左儿子遍历即可，然后对左右子树使用递归即可，比较简单

#### 问题拓展
将原问题中的二叉树改成一棵普通二叉树。比较容易想到的一个思路是使用层序遍历，然后将每一层的节点通过next指针连接即可。

也可以继续使用递归，但递归方法连接左右子树的相邻节点变得更加复杂。可以通过父节点的next指针来查找右儿子的下一个next对象，但需要注意的是，这样需要先递归右子树。
```python
    def connect(self, root):
        if not root:
            return root
        # 填充左儿子的next
        if root.left:
            if root.right:
                root.left.next = root.right
            else:
                root.left.next = self.nextNode(root.next)
        # 填充右儿子的next
        if root.right:
            root.right.next = self.nextNode(root.next)
        # 必须先递归右子树，否则左子树上的节点调用nextNode()方法，而右子树next为空
        self.connect1(root.right)
        self.connect1(root.left)

        pass

    # 寻找next节点
    def nextNode(self, root: Node):
        while root:
            if root.left:
                return root.left
            elif root.right:
                return root.right
            root = root.next
        return None
```
