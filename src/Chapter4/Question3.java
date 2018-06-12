package Chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
 * at each depth (e.g., if you have a tree with depth D, you 'll have D linked lists).
 */
class Question3 {

    public List<List<TreeNode>> listOfDepthsS1(TreeNode root) {

        List<List<TreeNode>> levels = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<TreeNode> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            levels.add(level);
        }

        return levels;

    }

    public List<List<TreeNode>> listOfDepthsS2(TreeNode root) {
        List<List<TreeNode>> levels = new ArrayList<>();
        listOfDepthsS2Helper(root, levels, 0);
        return levels;
    }

    private void listOfDepthsS2Helper(TreeNode root, List<List<TreeNode>> levels, int levelCount) {
        if (root == null) {
            return;
        }
        List<TreeNode> level = null;
        if (levels.size() == levelCount) {
            level = new ArrayList<>();
            levels.add(level);
        } else {
            level = levels.get(levelCount);
        }
        level.add(root);
        listOfDepthsS2Helper(root.left, levels, levelCount + 1);
        listOfDepthsS2Helper(root.right, levels, levelCount + 1);
    }

    public static void main(String[] args) {
        Question3 question3 = new Question3();
//        List<List<TreeNode>> levels = question3.listOfDepthsS1(TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        List<List<TreeNode>> levels = question3.listOfDepthsS2(TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));

        for (List<TreeNode> level : levels) {
            for (TreeNode node : level) {
                System.out.print(node.value + ", ");
            }
            System.out.println();
        }
    }

}
