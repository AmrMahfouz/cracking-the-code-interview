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

}
