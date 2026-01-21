import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
Time Complexity : O(N), N nodes in the tree, go through all of them
Space Complexity : O(N), keep node's parent and level in the map
Did this code successfully run on Leetcode : yes
Any problem you faced while coding this : no
Approach :

We can do level order traversal and identify the level the node is on. With the level, we can also keep the parent value
in the hashmap. Once we are done processing all the nodes, we can fetch the parent and level for nodes x and y. Return false,
if the parents are same. Return false if the levels are different as the nodes are not cousin. Else return true.
*/
public class BinaryTreeCousins {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        HashMap<Integer, Integer[]> nodeInfo = new HashMap<>();
        queue.add(root);
        int level = 0;

        // if root is checked, return false, because root has no cousins
        if(x == root.val || y == root.val){
            return false;
        }

        while(!queue.isEmpty()){
            int currentLevelSize = queue.size();

            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode current = queue.remove();
                // create the parent and level info and add to the map
                Integer[] levelAndParent = {current.val, level};
                if(current.left != null){
                    queue.add(current.left);
                    nodeInfo.put(current.left.val, levelAndParent);
                }

                if(current.right != null){
                    queue.add(current.right);
                    nodeInfo.put(current.right.val, levelAndParent);
                }
            }
            level += 1;
        }

        Integer[] xInfo = nodeInfo.get(x);
        Integer[] yInfo = nodeInfo.get(y);

        // check if parents are same, if yes, x and y are siblings.
        if(xInfo[0].equals(yInfo[0])) return false;
        // check if the levels are same or not
        if(!xInfo[1].equals(yInfo[1])) return false;
        return true;
    }

}
