package Chapter2;

/**
 * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
 * before all nodes greater than or equal to x. If x is contained within the list, the values of x only need
 * to be after the elements less than x (see below). The partition element x can appear anywhere in the
 * "right partition"; it does not need to appear between the left and right partitions.
 * EXAMPLE
 * Input: 3 -) 5 -) 8 -) 5 -) 113 -) 2 -) 1 [partition = 5]
 * Output: 3 -) 1 -) 2 -) 113 -) 5 -) 5 -) 8
 */
class Question4 {

    public LinkedListNode partition(LinkedListNode head, int val) {
        LinkedListNode p = head;
        LinkedListNode left = null;
        LinkedListNode right = null;
        LinkedListNode pL = null;
        LinkedListNode pR = null;
        while (p != null) {
            if (p.val < val) {
                if (left == null) {
                    left = new LinkedListNode(p.val);
                    pL = left;
                } else {
                    LinkedListNode node = new LinkedListNode(p.val);
                    pL.next = node;
                    pL = pL.next;
                }
            } else {
                if (right == null) {
                    right = new LinkedListNode(p.val);
                    pR = right;
                } else {
                    LinkedListNode node = new LinkedListNode(p.val);
                    pR.next = node;
                    pR = pR.next;
                }
            }
            p = p.next;
        }

        // merge two lists
        if (left == null) {
            return right;
        }
        pL.next = right;

        return left;
    }

    public LinkedListNode partitionWithoutCreatingNodes(LinkedListNode head, int val) {
        LinkedListNode p = head;
        LinkedListNode left = null;
        LinkedListNode right = null;
        LinkedListNode leftPointer = null;
        LinkedListNode rightPointer = null;
        while (p != null) {
            LinkedListNode next = p.next;
            p.next = null;
            if (p.val < val) {
                if (left == null) {
                    left = p;
                    leftPointer = left;
                } else {
                    LinkedListNode node = p;
                    leftPointer.next = node;
                    leftPointer = leftPointer.next;
                }
            } else {
                if (right == null) {
                    right = p;
                    rightPointer = right;
                } else {
                    LinkedListNode node = p;
                    rightPointer.next = node;
                    rightPointer = rightPointer.next;
                }
            }
            p = next;
        }

        // merge two lists
        if (left == null) {
            return right;
        }
        leftPointer.next = right;

        return left;
    }

    public static void main(String[] args) {
        LinkedListNode list = LinkedList.createList(new int[]{3, 5, 8, 5, 10, 2, 1});
        Question4 question4 = new Question4();
//        LinkedList.printList(question4.partition(list, 5));
        LinkedList.printList(question4.partitionWithoutCreatingNodes(list, 5));
    }

}
