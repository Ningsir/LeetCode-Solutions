package bfs;

import java.util.*;

/**
 * @author 宁鑫
 * @date 2020/1/20
 * @time 15:40
 **/
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int blength = beginWord.length();
        int elength = endWord.length();
        if(!wordList.contains(beginWord)) wordList.add(beginWord);
        if(!wordList.contains(endWord)) wordList.add(endWord);
        int n = wordList.size();
        if(elength == 0 || blength != elength || n < 1) return 0;
        int[][] adjacency = new int[n][n];
        int begin = -1, end = -1;
        for(int i = 0; i < n; i++){
            begin = beginWord.equals(wordList.get(i)) ? i : begin;
            end = endWord.equals(wordList.get(i)) ? i : end;
            for(int j = i + 1; j < n; j++){
                if(diffOneChar(wordList.get(i), wordList.get(j))){
                    adjacency[i][j] = 1;
                    adjacency[j][i] = 1;
                }
            }
        }
        for(int[] i : adjacency){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println(begin + " " + end);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(begin);
        boolean[] isVisited = new boolean[n];
        int res = 1;
        while(!queue.isEmpty()){
            int queue_length = queue.size();
            for(int i = 0; i < queue_length; i++){
                int tmp = queue.remove();
                isVisited[tmp] = true;
                if(tmp == end){
                    return res + 1;
                }
                for(int j = 0; j < n; j++){
                    if(adjacency[tmp][j] == 1 && !isVisited[j]){
                        queue.add(j);
                    }
                }

            }
            res++;
        }
        return 0;
    }

    public boolean diffOneChar(String a, String b){
        int count = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i))  count++;
        }
        if(count != 1) return false;
        return true;
    }

    public static void main(String[] args){
        WordLadder wordLadder = new WordLadder();
        String begin = "a";
        String end = "c";
        String[] wordList = {"a", "b", "c"};
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(wordList));
        System.out.println(wordLadder.ladderLength(begin, end, list));
    }
}
