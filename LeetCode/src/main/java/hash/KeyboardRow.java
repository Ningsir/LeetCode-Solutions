package hash;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class KeyboardRow {
    private String[] kEYStrings = {"qwertyuiopQWERTYUIOP", "asdfghjklASDFGHJKL", "zxcvbnmZXCVBNM"};
    public String[] findWords2(String[] words){
        //按照abcd的顺序表示其相应的行位置
        int[] charRow = {2, 1, 1, 2, 3, 2, 2, 2, 3, 2, 2, 2, 1, 1, 3, 3, 3, 3, 2, 3, 3, 1, 3, 1, 3, 1};
        List<String> list = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            int status = 1;
            String s = words[i].toLowerCase();//转为小写
            int row = charRow[s.charAt(0) - 'a'];//第一个字母所在行
            for(int j = 0; j < s.length(); j++){
                //判断是否与第一个字母在同一行
                if(row != charRow[s.charAt(j)]){
                    status = 0;
                    break;
                }
            }
            if(status != 0) list.add(s);
        }
        return list.toArray(new String[0]);
    }
    public String[] findWords(String[] words){
        if(words.length == 0){
            return new String[0];
        }
        HashSet[] set = new HashSet[3];
        for(int i = 0; i < set.length; i++){
            set[i] = new HashSet<Character>();
            for(int j = 0; j < kEYStrings[i].length(); j++){
                set[i].add(kEYStrings[i].charAt(j));
            }
        }
        List<String> list = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            String s = words[i];
            HashSet<Character> temp;//表示键盘上一行字母的hashset
            //判断字符创第一个字母在哪个set中
            if(set[0].contains(s.charAt(0))){
                temp = set[0];
            }
            else if(set[1].contains(s.charAt(0))){
                temp = set[1];
            }
            else{
                temp = set[2];
            }

            int status = 1;
            //遍历字符串
            for(int j = 0; j < s.length(); j++){
                //判断每个字符是否在temp中
                if(!temp.contains(s.charAt(j))){
                    status = 0;
                    break;
                }
            }
            if(status != 0){
                list.add(s);
            }
        }
        if(list.size() == 0) return new String[0];
        return list.toArray(new String[0]);
    }

    public static void main(String[] args){
        String[] words = {"eequwe", "heef", "fdaf"};
        KeyboardRow k = new KeyboardRow();
        String[] res = k.findWords(words);
        for(String s : res){
            System.out.println(s);
        }
    }
}