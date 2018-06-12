package Chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * Validate 8ST: Implement a function to check if a binary tree is a binary search tree.
 */
class Question5 {

    public boolean validBSTS1(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        inOrderTraversal(root, inOrder);
        for (int i = 1; i < inOrder.size() - 1; i++) {
            if (inOrder.get(i - 1) > inOrder.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void inOrderTraversal(TreeNode root, List<Integer> inOrder) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, inOrder);
        inOrder.add(root.value);
        inOrderTraversal(root.right, inOrder);
    }

    private boolean validBSTS2(TreeNode root) {
        return checkBSTS2(root, new WrapInt());
    }

    private class WrapInt {
        int value;
    }

    private boolean checkBSTS2(TreeNode root, WrapInt lastPrinted) {
        if (root == null) {
            return true;
        }

        if (!checkBSTS2(root.left, lastPrinted)) {
            return false;
        }

        if (lastPrinted != null && lastPrinted.value > root.value) {
            return false;
        }
        lastPrinted.value = root.value;

        if (!checkBSTS2(root.right, lastPrinted)) {
            return false;
        }

        return true;
    }

    public boolean validBSTS3(TreeNode root) {
        return checkBSTS3(root, null, null);
    }

    private boolean checkBSTS3(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.value <= min) || (max != null && root.value > max)) {
            return false;
        }
        if (!checkBSTS3(root.left, min, root.value) || !checkBSTS3(root.right, root.value, max)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Question5 question5 = new Question5();
        System.out.println(question5.validBSTS1(TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));
        System.out.println(question5.validBSTS1(TreeNode.createBalancedBinaryTree(new int[]{10, 2, 3, 4, 5, 6, 7, 8})));
        System.out.println(question5.validBSTS2(TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));
        System.out.println(question5.validBSTS2(TreeNode.createBalancedBinaryTree(new int[]{10, 2, 3, 4, 5, 6, 7, 8})));
        System.out.println(question5.validBSTS3(TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));
        System.out.println(question5.validBSTS3(TreeNode.createBalancedBinaryTree(new int[]{10, 2, 3, 4, 5, 6, 7, 8})));
    }

}
