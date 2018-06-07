package Chapter2;

import java.util.HashSet;
import java.util.Set;

/**
 * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
 * beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
 * as to make a loop in the linked list.
 * EXAMPLE
 * Input: A -> B -> C - > D -> E -> C [the same C as earlier)
 * Output: C
 */
class Question8 {

    public LinkedListNode loopDetectionS1(LinkedListNode list) {
        Set<LinkedListNode> prevNodes = new HashSet<>();
        while (list != null) {
            if (prevNodes.contains(list)) {
                return list;
            }
            prevNodes.add(list);
            list = list.next;
        }
        return null;
    }

    public LinkedListNode loopDetectionS2(LinkedListNode list) {
        LinkedListNode slow = list;
        LinkedListNode fast = list;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = list;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;

    }

    public static void main(String[] args) {
        Question8 question8 = new Question8();
        LinkedListNode list = LinkedList.createList(new int[]{1, 2, 3, 4, 5, 6, 7});
        LinkedList.getNodeByIndex(list, 6).next = LinkedList.getNodeByIndex(list, 3);
        System.out.println(question8.loopDetectionS1(list).val);
        System.out.println(question8.loopDetectionS2(list).val);
    }

}
