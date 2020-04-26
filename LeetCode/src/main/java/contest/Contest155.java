package contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author 宁鑫
 * @date 2019/9/22
 * @time 10:35
 **/
public class Contest155 {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int min = arr[n - 1];
        for(int i = 0; i < n - 1; i++){
            min = Math.min(min, arr[i + 1] - arr[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n - 1; i++){
             if(arr[i + 1] - arr[i] == min){
                 List<Integer> list = new ArrayList<>();
                 list.add(arr[i]);
                 list.add(arr[i + 1]);
                 ans.add(list);
             }
        }
        return ans;
    }

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < obstacles.length; i++){
            set.add(obstacles[i][0] + Integer.toString(obstacles[i][1]));
        }
        int x1 = 0, y1 = 0;
        while(true){
        for(int i = 0; i < command.length(); i++){
            if(command.charAt(i) == 'U'){
                y1++;
            }else{
                x1++;
            }
            if(x == x1 && y == y1) return true;
            if(set.contains(x1 + Integer.toString(y1))){
                return false;
            }
        }
        }
       // return false;
    }
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
         char[] c = s.toCharArray();
         for(int i = 0; i < pairs.size(); i++){
             char temp = c[pairs.get(i).get(0)];
             c[pairs.get(i).get(0)] = c[pairs.get(i).get(1)];
             c[pairs.get(i).get(1)] = temp;
         }
         return new String(c);
    }
    public static void main(String[] args){
        String s = "URR";
        int[][] o = {{2, 2}};
        int x = 2, y = 3;
        Contest155 c = new Contest155();
        System.out.println(c.robot(s, o, x, y));
    }
}
