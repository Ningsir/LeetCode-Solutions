# String
### 目录
* [ZagZig-Conversion(Z字形变形)](#1.ZagZig-Conversion)

### 1.ZagZig-Conversion
题目描述

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)  

>  P &nbsp;&nbsp; A  &nbsp;&nbsp; H  &nbsp; N  
>  A P  L S I &nbsp;I G  
>  Y &nbsp;&nbsp; I &nbsp;&nbsp;&nbsp; R

And then read line by line: "PAHNAPLSIIGYIR"

[代码](/src/main/java/string/ZigZag.java)
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