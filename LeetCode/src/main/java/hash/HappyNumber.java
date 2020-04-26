package hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宁鑫
 * @date 2019/7/19
 * @time 20:36
 **/
public class HappyNumber {
    public boolean isHappy(int n) {
        if(n == 0) {
            return false;
        }
        int res = n;
        List<Integer> list = new ArrayList<Integer>();
        list.add(n);
        while(res != 1){
            n = res;
            res = 0;
            while(n != 0){
                res += (n % 10) * (n % 10);
                n /= 10;
            }
            if(list.contains(res)){
                return false;
            }else{
                list.add(res);
            }
        }
        return true;
    }

    public static  void main(String[] args){
        HappyNumber happyNumber = new HappyNumber();
        System.out.println(happyNumber.isHappy(2));
    }

}
