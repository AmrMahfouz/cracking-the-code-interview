package Chapter2;

class LinkedList {

    public static LinkedListNode createList(int[] values) {
        LinkedListNode list = null;
        LinkedListNode p1 = null;
        for (int value : values) {
            if (list == null) {
                list = new LinkedListNode(value);
                p1 = list;
                continue;
            }
            p1.next = new LinkedListNode(value);
            p1 = p1.next;
        }
        return list;
    }

    public static void printList(LinkedListNode head) {
        LinkedListNode p = head;
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }

    public static LinkedListNode getNodeByIndex(LinkedListNode head, int index) {
        LinkedListNode p = head;
        int i = 0;
        while (p != null) {
            if (index == i) {
                return p;
            }
            i++;
            p = p.next;
        }
        return null;
    }

}
