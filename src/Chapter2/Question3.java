package Chapter2;

/**
 * Delete Middle Node: Implement an algorithm to delete a node in the middle (Le., any node but
 * the fi rst and last node, not necessarily the exact middle) of a singly linked list, given only access to
 * that node.
 * EXAMPLE
 * Input: the node c from the linked list a - >b- >c - >d - >e- >f
 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 */
public class Question3 {

    public void deleteMiddleNodeGivenList(LinkedListNode head) {
        LinkedListNode current = head;
        LinkedListNode runner = head;
        LinkedListNode prev = null;
        while (current != null && runner != null && runner.next != null) {
            runner = runner.next.next;
            prev = current;
            current = current.next;
        }

        // current is in the middle -> delete it
        prev.next = current.next;
    }

    public void deleteMiddleNodeGivenNode(LinkedListNode node) {
        if (node == null) {
            return;
        }
        LinkedListNode next = node.next;
        if (next != null) {
            node.val = next.val;
            node.next = next.next;
        }
    }

    public static void main(String[] args) {
        LinkedListNode list = LinkedList.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        Question3 question3 = new Question3();
//        question3.deleteMiddleNodeGivenList(list);
        question3.deleteMiddleNodeGivenNode(LinkedList.getNodeByIndex(list, 3));
        LinkedList.printList(list);
    }

}
