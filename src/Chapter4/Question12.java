package Chapter4;

import java.util.Hashtable;

/**
 * Paths with Sum: You are given a binary tree in which each node contains an integer value (which
 * might be positive or negative). Design an algorithm to count the number of paths that sum to a
 * given value. The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to ch ild nodes).
 */
class Question12 {

    public int countPathsWithSumS1(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int pathsFromRoot = countPathsWithSumS1Helper(root, targetSum, 0);

        int pathsOnLeft = countPathsWithSumS1Helper(root.left, targetSum, 0);
        int pathsOnRight = countPathsWithSumS1Helper(root.right, targetSum, 0);

        return pathsFromRoot + pathsOnLeft + pathsOnRight;

    }

    private int countPathsWithSumS1Helper(TreeNode node, int targetSum, int currentSum) {
        if (node == null) {
            return 0;
        }

        currentSum += node.value;

        int totalPaths = 0;
        if (currentSum == targetSum) {
            totalPaths++;
        }

        totalPaths += countPathsWithSumS1Helper(node.left, targetSum, currentSum);
        totalPaths += countPathsWithSumS1Helper(node.right, targetSum, currentSum);
        return totalPaths;
    }

    public int countPathsWithSumS2(TreeNode root, int targetSum) {
        return countPathsWithSumS2Helper(root, targetSum, 0, new Hashtable<Integer, Integer>());
    }

    private int countPathsWithSumS2Helper(TreeNode node, int targetSum, int runningSum, Hashtable<Integer, Integer> pathCount) {
        if (node == null) {
            return 0;
        }

        runningSum += node.value;
        int sum = runningSum - targetSum;
        int totalPaths = pathCount.getOrDefault(sum, 0);

        if (runningSum == targetSum) {
            totalPaths++;
        }

        incrementHashTable(pathCount, runningSum, 1);
        totalPaths += countPathsWithSumS2Helper(node.left, targetSum, runningSum, pathCount);
        totalPaths += countPathsWithSumS2Helper(node.right, targetSum, runningSum, pathCount);
        incrementHashTable(pathCount, runningSum, -1);

        return totalPaths;
    }

    private void incrementHashTable(Hashtable<Integer, Integer> hashTable, int key, int delta) {
        int newCount = hashTable.getOrDefault(key, 0) + delta;
        if (newCount == 0) {
            hashTable.remove(key);
        } else {
            hashTable.put(key, newCount);
        }
    }

    public static void main(String[] args) {
        Question12 question12 = new Question12();
        TreeNode root = TreeNode.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(question12.countPathsWithSumS1(root, 9));
        System.out.println(question12.countPathsWithSumS1(root, 7));
    }

}
