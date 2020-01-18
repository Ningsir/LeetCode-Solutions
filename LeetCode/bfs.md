## BFS
### 目录
* [1.二叉树的锯齿形层次遍历](#1zigzag-level-order-traversal)

### 1.zigzag-level-order-traversal
题目[二叉树的锯齿形层次遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)
```
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
```
利用队列对二叉树进行bfs遍历，为了区分每一层，可以使用队列每次只存储一层的节点，然后对这个队列进行遍历
```
while(!queue.isEmpty()){
    int n = queue.size();
    for(int i = 0; i < n; i++>){
        //对某一层中的所有节点进行遍历
    }
}
```
区分了层之后，可以使用`list.addFirst()` 和 `list.addLast()` 来实现锯齿形遍历；
