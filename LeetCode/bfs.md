## BFS
### 目录
* [1.二叉树的锯齿形层次遍历](#1zigzag-level-order-traversal)
* [2. 01矩阵](#201-matrix)
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

### 2.01-matrix
题目[01矩阵](https://leetcode-cn.com/problems/01-matrix/)
```
给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。

两个相邻元素间的距离为 1 。
```
#### 方法一：广度优先搜索算法(超时)
对每个1进行bfs，当遍历到0时停止遍历，遍历的层数即是最小距离。
```java
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix.length == 0)  return new int[0][0];
        int[][] res = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0)   res[i][j] = 0;
                else res[i][j] = dfs(i, j, matrix);
            }
        }
        return res;

    }

    //使用pair来保存遍历点
    private int dfs(int i, int j, int[][] martix){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i, j));
        int row = martix.length;
        int column = martix[0].length;
        boolean[][] isVisited = new boolean[martix.length][martix[0].length];
        int res = 0;
        while(!queue.isEmpty()){
            int n = queue.size();
            //对某一层中的所有元素进行遍历
            for(int k = 0; k < n; k++){
                Pair pair = queue.remove();
                if(pair.i < 0 || pair.i >= row || pair.j < 0 || pair.j >= column || 
                    isVisited[pair.i][pair.j])  continue;
                isVisited[pair.i][pair.j] = true;
                if(martix[pair.i][pair.j] == 0) return res;
                queue.add(new Pair(pair.i + 1, pair.j));
                queue.add(new Pair(pair.i - 1, pair.j));
                queue.add(new Pair(pair.i, pair.j + 1));
                queue.add(new Pair(pair.i, pair.j - 1));
            }
            res++;
        }
        return 0;
    }
}
class Pair{
    public int i;
    public int j;
    public Pair(int i, int j){
        this.i = i;
        this.j = j;
    }
}

```

#### 方法二：改进的bfs(将所有的0加入队列中)
此方法只进行了一次bfs，减少了时间复杂度。
* 1. 将所有的0在矩阵中的位置加入到队列中
* 2. bfs更新1的距离，如果有更新，则将该点加入到队列中

```java
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix.length == 0)  return new int[0][0];
        int[][] res = new int[matrix.length][matrix[0].length];
        Queue<Pair> queue = new LinkedList<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    queue.add(new Pair(i, j));
                }else{
                    res[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dis = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!queue.isEmpty()){
            Pair pair = queue.remove();
            for(int k = 0; k < 4; k++){
                int row = pair.i + dis[k][0];
                int col = pair.j + dis[k][1];
                if(row >=0 && row < matrix.length && col >= 0 && col < matrix[0].length){
                    if(matrix[row][col] == 1 && res[row][col] > res[pair.i][pair.j] + 1){
                        res[row][col] = res[pair.i][pair.j] + 1;
                        queue.add(new Pair(row, col));
                    }
                }
            }
        }
        return res;
    }
}
class Pair{
    public int i;
    public int j;
    public Pair(int i, int j){
        this.i = i;
        this.j = j;
    }
}
```
#### 方法三：动态规划
