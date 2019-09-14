package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Z字形变换
 * @author 宁鑫
 * @date 2019/9/14
 * @time 10:13
 **/
public class ZigZag {
    public static String convert(String s, int numRows){
        if(numRows == 1) return s;
        ArrayList<Character>[] lists = new ArrayList[numRows];
        for(int i = 0; i < numRows; i++){
            lists[i] = new ArrayList<Character>();
        }
        for(int i = 0; i < s.length(); i++){
            int index = i % (2 * numRows - 2);
            if(index > numRows - 1) index = 2 * numRows - index - 2;
            lists[index].add(s.charAt(i));
        }
        String res = "";
        for(int i = 0; i < numRows; i++){
            for(Character c : lists[i]){
                res += c;
            }
        }
        return res;
    }

    /**
     * 注意：变长字符串使用StringBuilder
     * 使用一次遍历即可，只需判断行数是加还是减即可
     * @param s
     * @param numRows
     * @return
     */
    public static String convert2(String s, int numRows){
        if(numRows == 1){
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i < Math.min(s.length(), numRows); i++){
            list.add(new StringBuilder());
        }
        int curRow = 0;
        //判断行数是向下相加，还是向上相减
        boolean goingDown = false;
        for(char c : s.toCharArray()){
            list.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder ss : list) res.append(ss);
        return res.toString();
    }
    public static void main(String[] args){
        String s = "PAYPALISHIRING";
        System.out.println(ZigZag.convert2(s, 4));
    }
}
