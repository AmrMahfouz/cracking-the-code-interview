package Chapter4;

/**
 * Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
 * algorithm to determine if T2 is a subtree of Tl.
 * A tree T2 is a subtree of T1 if there exists a node n in Tl such that the subtree of n is identical to T2 .
 * That is, if you cut off the tree at node n, the two trees would be identical.
 */
class Question10 {

    public boolean containsTreeS1(TreeNode t1, TreeNode t2) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getPreOrderTraversal(t1, string1);
        getPreOrderTraversal(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    private void getPreOrderTraversal(TreeNode t, StringBuilder string) {
        if (t == null) {
            string.append("X");
            return;
        }

        string.append(t.value + " ");
        getPreOrderTraversal(t.left, string);
        getPreOrderTraversal(t.right, string);
    }

    public boolean containsTreeS2(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        return subTree(t1, t2);
    }

    private boolean subTree(TreeNode r1, TreeNode r2) {
        if (r1 == null) {
            return false;
        } else if (r1.value == r2.value && matchTree(r1, r2)) {
            return true;
        }
        return subTree(r1.left, r2) || subTree(r1.right, r2);
    }

    private boolean matchTree(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        } else if (r1 == null || r2 == null) {
            return false;
        } else if (r1.value != r2.value) {
            return false;
        } else {
            return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
        }
    }

    public static void main(String[] args) {
        Question10 question10 = new Question10();
        TreeNode t1 = TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        TreeNode t2 = TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3});
        System.out.println(question10.containsTreeS1(t1, t2));
        System.out.println(question10.containsTreeS2(t1, t2));
        t2 = TreeNode.createBalancedBinaryTree(new int[]{8, 9, 10});
        System.out.println(question10.containsTreeS1(t1, t2));
        System.out.println(question10.containsTreeS2(t1, t2));
    }

}
