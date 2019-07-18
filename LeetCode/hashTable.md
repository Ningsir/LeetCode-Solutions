# 哈希表
### 1. Single Number
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