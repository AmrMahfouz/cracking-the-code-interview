package Chapter4;

/**
 * First Common Ancestor: Design an algorithm and write code to find the first common ancestor
 * of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
 * necessarily a binary search tree.
 */
class Question8 {

    public TreeNodeWithParent commonAncestorS1(TreeNodeWithParent n1, TreeNodeWithParent n2) {
        int n1Depth = getDepth(n1);
        int n2Depth = getDepth(n2);
        int diff = n2Depth - n1Depth;
        if (diff > 0) {
            n1 = goUpBy(n1, Math.abs(diff));
        } else {
            n2 = goUpBy(n2, Math.abs(diff));
        }
        while (n1 != n2 && n1 != null && n2 != null) {
            n1 = n1.parent;
            n2 = n2.parent;
        }
        if (n1 == null || n2 == null) {
            return null;
        }
        return n1;
    }

    private TreeNodeWithParent goUpBy(TreeNodeWithParent node, int depth) {
        while (node != null && depth != 0) {
            depth--;
            node = node.parent;
        }
        return node;
    }

    private int getDepth(TreeNodeWithParent node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }

    private TreeNodeWithParent commonAncestorS2(TreeNodeWithParent root, TreeNodeWithParent p, TreeNodeWithParent q) {
        if (!covers(root, p) || !covers(root, q)) {//check if nodes p and q not in the tree
            return null;
        } else if (covers(p, q)) {//check if p covers q
            return p;
        } else if (covers(q, p)) {//check if q covers p
            return q;
        }

        TreeNodeWithParent sibling = getSibling(p);
        TreeNodeWithParent parent = p.parent;
        while (!covers(sibling, q)) {
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    private TreeNodeWithParent getSibling(TreeNodeWithParent node) {
        if (node == null || node.parent == null) {
            return null;
        }
        TreeNodeWithParent parent = node.parent;
        return parent.left == node? parent.right : parent.left;
    }

    private boolean covers(TreeNodeWithParent root, TreeNodeWithParent p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        }
        return covers(root.left, p) || covers(root.right, p);
    }

    public TreeNodeWithParent commonAncestorS3(TreeNodeWithParent root, TreeNodeWithParent p, TreeNodeWithParent q) {
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return ancestorHelper(root, p, q);
    }

    private TreeNodeWithParent ancestorHelper(TreeNodeWithParent root, TreeNodeWithParent p, TreeNodeWithParent q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);

        if (pIsOnLeft != qIsOnLeft) {// p and q are on different sides
            return root;
        }

        TreeNodeWithParent childSide = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q);
    }

    private TreeNodeWithParent commonAncestorS4(TreeNodeWithParent root, TreeNodeWithParent p, TreeNodeWithParent q) {
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return commonAncestorS4Helper(root, p, q);
    }

    private TreeNodeWithParent commonAncestorS4Helper(TreeNodeWithParent root, TreeNodeWithParent p, TreeNodeWithParent q) {
        if (root == null) {
            return null;
        }
        if (root == p && root == q) {
            return root;
        }

        TreeNodeWithParent x = commonAncestorS4Helper(root.left, p, q);
        if (x != null && x != p && x != q) {
            return x;
        }

        TreeNodeWithParent y = commonAncestorS4Helper(root.right, p, q);
        if (y != null && y != p && y != q) {
            return y;
        }

        if (x != null && y != null) {
            return root;
        } else if (root == p || root == q) {
            return root;
        } else {
            return x == null ? y : x;
        }
    }

    public static void main(String[] args) {
        Question8 question8 = new Question8();
        TreeNodeWithParent root = TreeNodeWithParent.createBalancedBinaryTree(new int[]{1, 2, 3, 4, 5, 6});
//        TreeNodeWithParent commonAncestor = question8.commonAncestorS1(root.left, root.right);
//        TreeNodeWithParent commonAncestor = question8.commonAncestorS2(root, root.left, root.right);
//        TreeNodeWithParent commonAncestor = question8.commonAncestorS3(root, root.left, root.right);
        TreeNodeWithParent commonAncestor = question8.commonAncestorS4(root, root.left, root.right);
        if (commonAncestor != null) {
            System.out.println(commonAncestor.value);
        } else {
            System.out.println(commonAncestor);
        }
    }

}
