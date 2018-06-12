package Chapter4;

/**
 * Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
 * this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
 * node never differ by more than one.
 */
class Question4 {

    public boolean checkBalancedS1(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        int heightDiff = Math.abs(leftHeight - rightHeight);
        if (heightDiff > 1) {
            return false;
        }
        return checkBalancedS1(root.left) && checkBalancedS1(root.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public boolean checkBalancedS2(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    private int checkHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        Question4 question4 = new Question4();
        System.out.println(question4.checkBalancedS1(TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7})));
        System.out.println(question4.checkBalancedS1(TreeNode.createRandomBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7})));
        System.out.println(question4.checkBalancedS2(TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7})));
        System.out.println(question4.checkBalancedS2(TreeNode.createRandomBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7})));
    }

}
