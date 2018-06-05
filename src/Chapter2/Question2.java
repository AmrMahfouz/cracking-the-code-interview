package Chapter2;

/**
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 */
class Question2 {

    public LinkedListNode kthToLast(LinkedListNode list, int k) {

        LinkedListNode currentPlusKth = list;
        for (int i = 0; i < k; i++) {
            currentPlusKth = currentPlusKth.next;
        }

        LinkedListNode current = list;
        while (currentPlusKth != null) {
            currentPlusKth = currentPlusKth.next;
            current = current.next;
        }

        return current;

    }

    public LinkedListNode kthToLastWithListLength(LinkedListNode list, int k, int l) {
        int length = l - k;
        LinkedListNode p1 = list;
        for (int i = 0; i < length; i++) {
            p1 = p1.next;
        }
        return p1;
    }

    public int printKthToLast(LinkedListNode head, int k) {
        if (head == null) {
            return 0;
        }
        int index = printKthToLast(head.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.val);
        }
        return index;
    }

    private int index;

    public LinkedListNode returnKthToLast(LinkedListNode head, int k) {
        if (head == null) {
            return null;
        }
        LinkedListNode node = returnKthToLast(head.next, k);
        index++;
        if (index == k) {
            return head;
        }
        return node;
    }

    private static class Index {
        int value;
    }

    public LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
        if (head == null) {
            return null;
        }
        LinkedListNode node = kthToLast(head.next, k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
            return head;
        }
        return node;
    }

    public static void main(String[] args) {
        LinkedListNode list = LinkedList.createList(new int[]{1, 2, 3, 4, 5, 6, 7});
        Question2 question2 = new Question2();
//        LinkedListNode kthToLast = question2.kthToLast(list, 4);
//        question2.printKthToLast(list, 4);
//        LinkedList kthToLast = question2.kthToLastWithListLength(list, 4, 7);
//        LinkedListNode kthToLast = question2.returnKthToLast(list, 4);
        LinkedListNode kthToLast = question2.kthToLast(list, 4, new Index());
        LinkedList.printList(kthToLast);

    }

}
