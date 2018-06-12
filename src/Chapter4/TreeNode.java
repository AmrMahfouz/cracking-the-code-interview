package Chapter4;

import java.util.*;

class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    private void printNode() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.value);
        if (this.left != null) {
            stringBuilder.append("(left:" + this.left.value + ")");
        } else {
            stringBuilder.append("(left:null)");
        }
        if (this.right != null) {
            stringBuilder.append("(right:" + this.right.value + ")");
        } else {
            stringBuilder.append("(right:null)");
        }
        System.out.println(stringBuilder.toString());
    }

    public void printTreeNodes() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                node.printNode();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    public static TreeNode createBalancedBinaryTree(int[] values) {
        return createBinaryTreeHelper(values, 0, values.length - 1);
    }

    private static TreeNode createBinaryTreeHelper(int[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(values[start]);
        }
        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(values[middle]);
        TreeNode left = createBinaryTreeHelper(values, start, middle - 1);
        TreeNode right = createBinaryTreeHelper(values, middle + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    public static TreeNode createRandomBinaryTree(int[] values) {
        List<TreeNode> nodes = new ArrayList<>();
        for (int value : values) {
            nodes.add(new TreeNode(value));
        }
        Random random = new Random();
        TreeNode root = nodes.remove(random.nextInt(nodes.size()));
        TreeNode prevNode = root;
        while (!nodes.isEmpty()) {
            int index = random.nextInt(nodes.size());
            if (index % 2 == 0) {
                prevNode.right = nodes.remove(random.nextInt(nodes.size()));
                prevNode = prevNode.right;
            } else {
                prevNode.left = nodes.remove(index);
                prevNode = prevNode.left;
            }
        }
        return root;
    }

}
