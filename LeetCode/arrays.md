# Array
## 目录
 * 1. [SubarraySumsDivByK(连续子数组和被K整除)](#SubarraySums)
 * 2. [盛最多水的容器](#ContainerWithMostWater)

### SubarraySums
题目描述
> Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

[代码](./src/main/java/arrays/SubarraySums.java)
* 方法一：暴力解法

使用两层for循环进行遍历即可解决，但时间成本太大
```java

//暴力解法
    public int subarraysDivByK(int[] A, int K) {
        return subarray(A, K, 0);
    }

    private int subarray(int[] a, int K, int n){
        //数组元素个数为0
        if(a.length - n == 0) return 0;
        int sum = 0;
        int count = 0;
        for(int i = n; i < a.length; i++){
            sum += a[i];
            if(sum % K == 0) {
                count++;
            }
        }
        return count + subarray(a, K, n + 1);
    }
```

* 方法二：利用前缀和解题

前缀和：`P[i] = A[0] + A[1] + ...... + A[i]`.
将前缀和对 K 进行求余操作，余数相同的任意两个前缀和之间的子数组之和能够整除K。

比如：
`A = {4, 5, 0, -2, -3, 1}`; `K = 5`; `P = {4, 9, 9, 7, 4, 5}`;
`P[1] % 5 = P[0] % 5= 4` 能够整除 5，则`P[0]` 与 `P[1]` 之间的子数组能够整除 5(子数组为`{5}`)。

一般性：
`P[i]` 、 `P[j]` 对 K 取余且余数相等，则子数组`A[i + 1]......A[j]`之和能够整除K

```java
public int subarraysDivByK(int[] A, int K){
        int n = A.length;
        int[] p = new int[n];
        //计算前缀和
        for(int i = 0; i < n; i++){
            if(i == 0) p[i] = A[i];
            else{
                p[i] = p[i - 1] + A[i];
            }
        }
        //前缀和对K求余，count用来存储每个余数的个数
        int[] count = new int[K];
        for(int i : p){
            count[(i  % K + K) % K]++; //避免出现负数
        }
        int res = 0;
        //余数相同的任意两个前缀和之差一定能够整除K（它们之间的子数组之和也就能够整除K）
        for(int i : count){
            res += i * (i - 1) / 2;
        }
        //注意余数为0的子数组直接满足条件
        return res + count[0];

    }
```

### ContainerWithMostWater
```
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
```
#### 思路：双指针
使用首尾两个指针i、j对数组进行遍历，因为容器的大小：`min(ai, aj) * (j - i)`,其长度是由短的一条边决定的，所以为了使容器的大小更大，需要替换短的边。

```c++
class Solution {
public:
    int maxArea(vector<int>& height) {
        int res = 0;
        int i = 0, j = height.size() - 1;
        while(i < j){
            res = max(res, min(height[i], height[j]) * (j - i));
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return res;
    }
};
```