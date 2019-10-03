## DFS
### 目录
* [1.二叉树原地展开为链表](#1flatten-binary-tree-to-a-linked-list)

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