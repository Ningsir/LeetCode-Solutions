package hash;

/**
 * @author 宁鑫
 * @date 2019/7/18
 * @time 21:27
 **/
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i : nums){
            res ^= i;
        }
        return res;
    }
}