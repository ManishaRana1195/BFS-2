/*
Time Complexity : O(N), all the nodes in the tree needs to be visited
Space Complexity : ~O(N), worst case, the largest number of nodes in the queue will be the last level nodes.
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
Approach :

We need to do level order traversal and find the right most node. While traversing we add all the children at a level
in the queue. After adding the children from right to left and before processing that level, we can peek in the queue. It will
have the rightmost node first and add it to the result list.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int currentSize = queue.size();
            int index = 0;
            // Before the level is processed take the first child as it is the rightmost child.
            result.add(queue.peek().val);
            while(index < currentSize){
                TreeNode currentRoot = queue.poll();
                // Add Right children before the left
                addChildren(currentRoot, queue);
                index++;
            }
        }

        return result;
    }

    public void addChildren(TreeNode root, Queue<TreeNode> queue){
        if(root == null) return;
        if(root.right != null) queue.add(root.right);
        if(root.left != null) queue.add(root.left);
    }
}
