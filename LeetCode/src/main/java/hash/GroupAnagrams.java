package hash;

import java.util.*;

/**
 * @author 宁鑫
 * @date 2019/7/29
 * @time 18:16
 **/
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> ans = new HashMap<>();
        for(String s : strs){
            char[] tmp = s.toCharArray();
            Arrays.sort(tmp);
            if(!ans.containsKey(String.valueOf(tmp))){
                ans.put(String.valueOf(tmp), new ArrayList<>());
            }
            ans.get(String.valueOf(tmp)).add(s);
        }
        Collection<List<String>> strings = ans.values();
        return new ArrayList<>(strings);
    }

    public static void main(String[] args){
        String[] strs = {"ate", "eat", "tan", "atn", "hello"};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println(groupAnagrams(strs).size());
        for(int i = 0; i < lists.size(); i++){
            for(String s : lists.get(i)){
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
