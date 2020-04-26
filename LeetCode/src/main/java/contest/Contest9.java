package contest;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author 宁鑫
 * @date 2019/9/21
 * @time 22:52
 **/
public class Contest9 {
    public static int smallestCommonElement(int[][] mat) {
        if(mat.length <= 1) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < mat[0].length; i++){
            map.put(mat[0][i], 1);
        }
        for(int i = 1; i < mat.length; i++){
            for(int j = 0; j < mat[i].length; j++){
                if(map.containsKey(mat[i][j])){
                    map.put(mat[i][j], map.get(mat[i][j]) + 1);
                }
            }
        }
        int ans = 100000;
        for(Integer key : map.keySet()){
            if(map.get(key) == mat.length){
                ans = ans < key ? ans : key;
            }
        }
        return ans == 100000 ? -1 : ans;
    }

    public static int minKnightMoves(int x, int y) {
        if(x == 0 && y == 0) return 0;
        x = Math.abs(x);
        y = Math.abs(y);
        int min = minKnightMoves(x - 1, y + 2);
        int temp = minKnightMoves(x + 1, y + 2);
        min = Math.min(min, temp);
        temp = minKnightMoves(x + 2, y + 1);
        min = Math.min(min, temp);
        temp = minKnightMoves(x + 2, y - 1);
        min = Math.min(min, temp);
        return min + 1;
    }

    public static void main(String[] args){
        System.out.println(Contest9.minKnightMoves(5, 5));
    }
}
