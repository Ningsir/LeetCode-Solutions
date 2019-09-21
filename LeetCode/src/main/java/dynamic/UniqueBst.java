package dynamic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 宁鑫
 * @date 2019/9/21
 * @time 17:15
 **/
public class UniqueBst {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new LinkedList<>();
        return generateTrees(1, n);
    }

    /**
     * 生成从first到last的BST
     * @param first
     * @param last
     * @return
     */
    private List<TreeNode> generateTrees(int first, int last){
        List<TreeNode> res = new ArrayList<>();
        if(first > last) {
            //注意：不能return new ArrayList<>();
            res.add(null);
            return res;
        }
        for(int i = first; i <= last; i++){
            List<TreeNode> left = generateTrees(first, i - 1);
            List<TreeNode> right = generateTrees(i + 1, last);
            //生成以i为根节点的BST
            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode t = new TreeNode(i);
                    t.left = l;
                    t.right = r;
                    res.add(t);
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        UniqueBst uniqueBst = new UniqueBst();
        List<TreeNode> list =uniqueBst.generateTrees(3);
        System.out.println(list.size());
    }

    @Test
    void test() {

    }
}

//* Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
