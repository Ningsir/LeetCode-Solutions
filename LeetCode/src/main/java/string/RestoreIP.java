package string;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author 宁鑫
 * @date 2019/9/14
 * @time 14:54
 **/
public class RestoreIP {
    public static List<String> restoreIpAddresses(String s) {
        if(s.length() < 4 || s.length() > 12) return new ArrayList<>();
        List<StringBuilder> res = new ArrayList<>();
        StringBuilder ss = new StringBuilder();//存储ip
        int n = s.length();
        //使用i + 1、j + 1、k + 1 来表前面三个数字的长度
        for(int i = 0; i < 3 && (n - (i + 1) >= 3); i++){
            ss.append(s.substring(0, i + 1)).append(".");
            for(int j = 0; j < 3 && n - (i + j + 2) >= 2; j++){
                ss.append(s.substring(i + 1, i + j + 2)).append(".");
                for(int k = 0; k < 3  && n - (i + j + k + 3) >= 1; k++){
                    ss.append((s.substring(i + j + 2, i + j + k + 3))).append(".");
                    ss.append(s.substring(i + j + k + 3, n));
                    if(validIP(ss.toString())){
                        res.add(new StringBuilder(ss));
                    }
                    ss.delete(i + j + 4, n + 3);
                }
                ss.delete(i + 2, i + j + 4);
            }
            ss.delete(0, i + 2);
        }
        List<String> list = new ArrayList<>();
        for(StringBuilder sb : res){
            list.add(sb.toString());
        }
        return list;
    }

    public static boolean validIP(String s){
        String[] temp = s.split("\\.");
        if(temp.length != 4) return false;
        for(String ss : temp){
            if(Integer.parseInt(ss) > 255 || (ss.length() != 1 && ss.charAt(0) == '0')) return false;
        }
        return true;
    }

    @Test
    void test(){
        String s = "010010";
        ArrayList<String> list = new ArrayList<>();
        list.add("0.10.0.10");
        list.add("0.100.1.0");
        assertEquals(list, RestoreIP.restoreIpAddresses(s));
        assertEquals(new ArrayList<>(), RestoreIP.restoreIpAddresses(""));
    }
}
