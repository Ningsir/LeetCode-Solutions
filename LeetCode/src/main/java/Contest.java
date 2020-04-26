import java.util.*;

/**
 * @author 宁鑫
 * @date 2020/4/19
 * @time 10:35
 **/
public class Contest {

    public String reformat(String s) {
        StringBuilder a = new StringBuilder();
        StringBuilder num = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                a.append(String.valueOf(s.charAt(i)));
            }else{
                num.append(String.valueOf(s.charAt(i)));
            }
        }
        if(Math.abs(a.length() - num.length()) > 1){
            return "";
        }
        StringBuilder res = new StringBuilder();
        int min = Math.min(a.length(), num.length());
        for(int i = 0; i < min; i++){
            res.append(a.charAt(i));
            res.append(num.charAt(i));
        }
        if(a.length() < num.length()){
            res.insert(0, num.charAt(num.length() - 1));
        }
        if(a.length() > num.length()){
            res.append(a.charAt(a.length() - 1));
        }
        return new String(res);
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String>  foodSet = new HashSet<>();
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> tableSet = new HashSet<>();
        for(List<String> list : orders){
            tableSet.add(list.get(1));
            foodSet.add(list.get(2));
            if(!map.containsKey(list.get(1))){
                map.put(list.get(1), new HashSet<>());
            }
            map.get(list.get(1)).add(list.get(2));
        }
        List<String> tableList = new ArrayList<>(tableSet);
        tableList.sort((o1, o2) -> Integer.parseInt(o1) - Integer.parseInt(o2));
        Map<String, Integer> tableCount = new HashMap<>(100);
        for(int i = 0; i < tableList.size(); i++){
            tableCount.put(tableList.get(i), i);
        }
        LinkedList<String> foodList = new LinkedList<>(foodSet);
        Collections.sort(foodList);
        Map<String, Integer> foodCount = new HashMap<>(100);
        for(int i = 0; i < foodList.size(); i++){
            foodCount.put(foodList.get(i), i);
        }
        int[][] count = new int[tableSet.size()][foodSet.size()];
        for(List<String> list : orders){
            count[tableCount.get(list.get(1))][foodCount.get(list.get(2))] += 1;
        }
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        tmp.add("Table");
        tmp.addAll(foodList);
        res.add(tmp);
        for(int i = 0; i < tableList.size(); i++){
            List<String> t = new ArrayList<>();
            t.add(String.valueOf(tableList.get(i)));
            for(int j : count[i]){
                t.add(String.valueOf(j));
            }
            res.add(t);
        }
        return res;
    }

    public static void main(String[] args){
        Contest contest = new Contest();
        System.out.println(contest.reformat("12345fsabfda"));
        System.out.println(contest.reformat("12345fsabfd"));
        System.out.println(contest.reformat("12345fsabf"));
        System.out.println(contest.reformat("12345"));
        String[][] s = {{"David","3","Ceviche"},{"Corina","10","Beef Burrito"},{"David","3","Fried Chicken"},{"Carla","5","Water"},{"Carla","5","Ceviche"},{"Rous","3","Ceviche"}};
        List<List<String>> lists = new ArrayList<>();
        for(String[] ss : s){
            List<String> tmp = new ArrayList<>(Arrays.asList(ss));
            lists.add(tmp);
        }
        contest.displayTable(lists);
//        Set<String> l = new HashSet<>();
//        l.add("3");
//        l.add("1");
//        LinkedList<String> ll = new LinkedList<>(l);
//        Collections.sort(ll);
//        for(String s : ll){
//            System.out.println(s);
//        }
    }
}
