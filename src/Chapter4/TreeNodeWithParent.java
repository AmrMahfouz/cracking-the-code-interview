package Chapter4;

class TreeNodeWithParent {
    TreeNodeWithParent left;
    TreeNodeWithParent right;
    TreeNodeWithParent parent;
    int value;

    public TreeNodeWithParent(int value) {
        this.value = value;
    }

    public static TreeNodeWithParent createBalancedBinaryTree(int[] values) {
        return createBinaryTreeHelper(values, 0, values.length - 1);
    }

    private static TreeNodeWithParent createBinaryTreeHelper(int[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNodeWithParent(values[start]);
        }
        int middle = (start + end) / 2;
        TreeNodeWithParent root = new TreeNodeWithParent(values[middle]);
        TreeNodeWithParent left = createBinaryTreeHelper(values, start, middle - 1);
        TreeNodeWithParent right = createBinaryTreeHelper(values, middle + 1, end);
        root.left = left;
        root.right = right;
        if (left != null) {
            left.parent = root;
        }
        if (right != null) {
            right.parent = root;
        }
        return root;
    }
}
