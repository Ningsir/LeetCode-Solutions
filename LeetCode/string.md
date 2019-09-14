# String
### 目录
* [ZagZig-Conversion(Z字形变形)](#1ZagZig-Conversion)

### 1.ZagZig-Conversion
题目描述

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)  

>  P &nbsp;&nbsp; A  &nbsp;&nbsp; H  &nbsp; N  
>  A P  L S I &nbsp;I G  
>  Y &nbsp;&nbsp; I &nbsp;&nbsp;&nbsp; R

And then read line by line: "PAHNAPLSIIGYIR"

[代码](./src/main/java/string/ZigZag.java)
```java
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
```

### 2.Restore-IP-Address
题目描述

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

> Example:
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

IP 地址规则：
* 四个数字构成
* 每个数字范围0-255
* 每个数字开头不能是0(除非为0)

[代码](./src/main/java/string/RestoreIP.java)
```java
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

```
