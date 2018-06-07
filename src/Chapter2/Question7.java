package Chapter2;

import java.util.HashSet;
import java.util.Set;

/**
 * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
 * intersecting node. Note that the intersectionS1 is defined based on reference, not value. That is, if the
 * kth node of the first linked list is the exact same node (by reference) as the jth node of the second
 * linked list, then they are intersecting.
 */
class Question7 {

    public LinkedListNode intersectionS1(LinkedListNode list1, LinkedListNode list2) {
        Set<LinkedListNode> set = new HashSet<>();
        while (list1 != null) {
            set.add(list1);
            list1 = list1.next;
        }
        while (list2 != null) {
            if (set.contains(list2)) {
                return list2;
            }
            list2 = list2.next;
        }
        return null;
    }

    private int length1;
    private int length2;

    public LinkedListNode intersectionS2(LinkedListNode list1, LinkedListNode list2) {
        boolean isIntersect = isIntersect(list1, list2);
        if (!isIntersect) {
            return null;
        }

        int diff = Math.abs(length1 - length2);
        if (length2 > length1) {
            list2 = LinkedList.getNodeByIndex(list2,diff - 1);
        } else if (length1 > length2) {
            list1 = LinkedList.getNodeByIndex(list1,diff - 1);
        }

        while (list1 != null && list2 != null) {
            if (list1 == list2) {
                return list1;
            }
            list1 = list1.next;
            list2 = list2.next;
        }

        return null;
    }

    private boolean isIntersect(LinkedListNode list1, LinkedListNode list2) {
        while(list1 != null) {
            length1++;
            list1 = list1.next;
        }
        while (list2 != null) {
            length2++;
            list2 = list2.next;
        }
        return list1 == list2;
    }

    public static void main(String[] args) {
        Question7 question7 = new Question7();
        LinkedListNode list1 = LinkedList.createList(new int[]{1, 2, 3, 4, 5, 6, 7});
        LinkedListNode list2 = LinkedList.createList(new int[]{1, 2, 3, 4});
        LinkedList.getNodeByIndex(list2, 3).next = LinkedList.getNodeByIndex(list1, 4);
        System.out.println(question7.intersectionS1(list1, list2).val);
        System.out.println(question7.intersectionS2(list1, list2).val);
    }

}
