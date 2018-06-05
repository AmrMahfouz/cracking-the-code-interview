package Chapter2;

import java.util.HashSet;
import java.util.Set;

/**
 * Remove Dups: Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 */
class Question1 {

    public void removeDuplicatesS1(LinkedListNode list) {
        Set<Integer> prevValues = new HashSet<>();
        LinkedListNode p1 = list;
        LinkedListNode prev = list;
        while (p1 != null) {
            if (prevValues.contains(p1.val)) {
                prev.next = p1.next;
            }
            prevValues.add(p1.val);
            prev = p1;
            p1 = p1.next;
        }
    }

    public void removeDuplicatesS2(LinkedListNode list) {
        LinkedListNode current = list;
        while (current != null) {
            LinkedListNode runner = current.next;
            LinkedListNode prev = current;
            while (runner != null) {
                if (current.val == runner.val) {
                    prev.next = runner.next;
                }
                prev = runner;
                runner = runner.next;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Question1 question1 = new Question1();
        LinkedListNode list = LinkedList.createList(new int[]{1, 2, 3, 4, 5, 3, 5, 7});
        //question1.removeDuplicatesS1(list);
        question1.removeDuplicatesS2(list);
        LinkedList.printList(list);
    }

}
