package hash;

import java.util.HashMap;

/**
 * @author 宁鑫
 * @date 2019/9/8
 * @time 19:05
 **/
public class IsomorphicStrings {
    public  boolean isIsomorphic(String s, String t){
       return isomorphic(s, t) && isomorphic(t, s);
    }

    public boolean isomorphic(String s, String t){
        if(s.length() != t.length()) return false;
        HashMap<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i)) && !map.get(s.charAt(i)).equals(t.charAt(i)))
                return false;
            else{
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    public static void main(String[] args){
        IsomorphicStrings i = new IsomorphicStrings();
        String s = "ab";
        String t = "aa";
        System.out.println(i.isIsomorphic(s, t));
    }
}
