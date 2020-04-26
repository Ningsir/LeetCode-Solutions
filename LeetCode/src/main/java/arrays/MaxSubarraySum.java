package arrays;

import java.util.HashSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author 宁鑫
 * @date 2019/9/13
 * @time 11:11
 **/
public class MaxSubarraySum {
    public int maxSum(int[] arr){
        int res = 0;
        int temp = 0;
        for(int i : arr){
            temp += i;
            if(temp > res) res = temp;
            if(temp  < 0) temp = 0;
        }
        return res;
    }
    public int maximumSum(int[] arr) {
        int res = maxSum(arr);
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < 0){
                int temp = arr[i];
                arr[i] = 0;
                int max = maxSum(arr);
                if(max > res) res = max;
                arr[i] = temp;
            }
        }
        return res;
    }
    public static void main(String[] args){
        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();
        random.nextInt();
        int[] a = {-2, -2, -2, -2};
        MaxSubarraySum m = new MaxSubarraySum();
        System.out.println(m.maximumSum(a));
    }
}
