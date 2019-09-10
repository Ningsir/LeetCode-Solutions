package arrays;

/**
 * 连续子数组之和被K整除
 * @author 宁鑫
 * @date 2019/9/10
 * @time 19:47
 **/
public class SubarraySums {
    /**
     * 利用前缀和解题
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK2(int[] A, int K){
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
        return res + count[0];

    }

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

    public static void main(String[] args){
        int[] a= {4, 5, 0, -2, -3, 1};
        SubarraySums s = new SubarraySums();
        System.out.println(s.subarraysDivByK2(a, 5));

    }
}
