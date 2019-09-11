package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author 宁鑫
 * @date 2019/9/11
 * @time 19:08
 **/
public class LongestSubString {

    /**
     * 求解最长无重复字母的子字符串的长度
     * 方法一：暴力解法，求解以每个字母作为子字符的开始的最长子字符串
     * 方法二：滑动窗口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        int countA = 0;
        int countB = 0;
        for(int i = 0; i < s.length(); i++){
            HashSet<Character> set = new HashSet<>();
            for(int j = i; j < s.length(); j++){
                if(set.contains(s.charAt(j))){
                    break;
                }else{
                    set.add(s.charAt(j));
                    countB++;
                }
            }
            if(countA < countB){
                countA = countB;
            }
            countB = 0;
        }
        return countA;
    }

    /**
     * 滑动窗口解法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s){
        HashSet<Character> set = new HashSet<>();
        int n = s.length();
        int i = 0;
        int j = 0;
        int res = 0;
        while(i < n && j < n){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                res = Math.max(res, j - i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

    //优化：使用hashmap，没有使用remove操作
    public int lengthOfLongestSubstring3(String s){
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int i = 0;
        int res = 0;
        for(int j = 0; j < n; j++){
            if(!map.containsKey(s.charAt(j))){
                map.put(s.charAt(j), j);
            }else{
                //因为没有remove操作，则i 之前的字符可能与 i 之后的字符重复，避免i减小
                i = Math.max(map.get(s.charAt(j)) + 1, i);
                map.put(s.charAt(j), j);
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    public static void main(String[] args){
        LongestSubString l = new LongestSubString();
        String s = "twwaext";
        System.out.println(l.lengthOfLongestSubstring3(s));
    }
}
