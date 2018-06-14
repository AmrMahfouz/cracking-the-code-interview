package Chapter4;

import java.util.Stack;

/**
 * Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
 * binary search tree. You may assume that each node has a link to its parent.
 */
class Question6 {

    private static class TreeNodeWithParent {
        TreeNodeWithParent left;
        TreeNodeWithParent right;
        TreeNodeWithParent parent;
        int value;

        public TreeNodeWithParent(int value) {
            this.value = value;
        }
    }

    public TreeNodeWithParent inorderSuccessorS1(TreeNodeWithParent node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return leftMostChild(node.right);
        } else {
            TreeNodeWithParent current = node;
            TreeNodeWithParent parent = current.parent;
            while(parent != null && parent.left != current) {
                current = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    private TreeNodeWithParent leftMostChild(TreeNodeWithParent node) {
        if (node == null) {
            return null;
        }
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    public TreeNodeWithParent inorderSuccessorS2(TreeNodeWithParent node) {
        TreeNodeWithParent root = getTreeRoot(node);
        Stack<TreeNodeWithParent> stack = new Stack<>();
        inorderSuccessorS2Helper(root, node, stack);
        while (!stack.isEmpty()) {
            TreeNodeWithParent prev = stack.pop();
            if (stack.peek() == node) {
                return prev;
            }
        }
        return null;
    }

    private void inorderSuccessorS2Helper(TreeNodeWithParent root, TreeNodeWithParent node, Stack<TreeNodeWithParent> stack) {
        if (root == null) {
            return;
        }
        inorderSuccessorS2Helper(root.left, node, stack);
        stack.push(root);
        inorderSuccessorS2Helper(root.right, node, stack);
    }

    private TreeNodeWithParent getTreeRoot(TreeNodeWithParent node) {
        TreeNodeWithParent root = node;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNodeWithParent root = new TreeNodeWithParent(1);
        root.left = new TreeNodeWithParent(2);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(3);
        root.right.parent = root;
        root.left.left = new TreeNodeWithParent(4);
        root.left.left.parent = root.left;
        root.left.right = new TreeNodeWithParent(5);
        root.left.right.parent = root.left;
        root.left.right.left = new TreeNodeWithParent(6);
        root.left.right.left.parent = root.left.right.left;
        root.right.left = new TreeNodeWithParent(7);
        root.right.left.parent = root.right;
        root.right.right = new TreeNodeWithParent(8);
        root.right.right.parent = root.right;
        root.right.right.left = new TreeNodeWithParent(9);
        root.right.right.left.parent = root.right.right;
        root.right.right.left.left = new TreeNodeWithParent(10);
        root.right.right.left.left.parent = root.right.right.left;
        root.right.right.left.right = new TreeNodeWithParent(11);
        root.right.right.left.right.parent = root.right.right.left;
//        TreeNodeWithParent nextInOrderNode = new Question6().inorderSuccessorS1(root.right.right.left.left);
        TreeNodeWithParent nextInOrderNode = new Question6().inorderSuccessorS2(root.right.right.left.left);
        if (nextInOrderNode != null) {
            System.out.println(nextInOrderNode.value);
        } else {
            System.out.println(nextInOrderNode);
        }
    }

}
