package Chapter4;

/**
 * Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an
 * algorithm to create a binary search tree with minimal height.
 */
class Question2 {

    public TreeNode minimalTree(int[] values) {
        return minimalTreeHelper(values, 0, values.length - 1);
    }

    private TreeNode minimalTreeHelper(int[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(values[start]);
        }
        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(values[middle]);
        TreeNode left = minimalTreeHelper(values, start, middle - 1);
        TreeNode right = minimalTreeHelper(values, middle + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        Question2 question2 = new Question2();
        TreeNode root = question2.minimalTree(new int[] {1,2,3,4,5,6,7,8});
        root.printTreeNodes();
    }

}
