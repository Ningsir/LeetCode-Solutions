package hash;

import sun.security.util.ConstraintsParameters;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 宁鑫
 * @date 2019/9/11
 * @time 21:16
 **/
public class CopyList {
    public Node copyRandomList(Node head) {
        Map<Integer, Node> map1 = new HashMap<>();
        Map<Integer, Node> map2 = new HashMap<>();
        Node p = head;
        Node res, copy = new Node();
        res = copy;
        while(p != null){
            map1.put(p.hashCode(), p);
            Node random;
            Node temp;
            if(map2.containsKey(p.hashCode())){
                temp = map2.get(p.hashCode());
            }else{
                temp = new Node();
                temp.val = p.val;
            }
            map2.put(p.hashCode(), temp);

            if(p.random == null){
                random = null;
            }
            else if(map1.containsKey(p.random.hashCode())){
                random = map2.get(p.random.hashCode());
            }
            else{
                map1.put(p.random.hashCode(), p.random);
                random = new Node();
                random.val = p.random.val;
                map2.put(p.random.hashCode(), random);
            }
            temp.random = random;
            copy.next = temp;
            copy = copy.next;
            p = p.next;
        }
        return res.next;
    }

    public static void main(String[] args){

    }
}
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}
