# Array
## 目录
 * 1. [SubarraySumsDivByK(连续子数组和被K整除)](#SubarraySums)

### SubarraySums
题目描述
> Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
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
        return res;

    }
```