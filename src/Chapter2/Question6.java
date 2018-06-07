package Chapter2;

import java.util.Stack;

class Question6 {

    public boolean isPalindromeS1(LinkedListNode head) {
        LinkedListNode reversedHead = reverse(head);
        while (reversedHead != null && head != null) {
            if (reversedHead.val != head.val) {
                return false;
            }
            reversedHead = reversedHead.next;
            head = head.next;
        }
        if (reversedHead != null || head != null) {
            return false;
        }
        return true;
    }

    private LinkedListNode reverse(LinkedListNode head) {

        LinkedListNode p = head;
        LinkedListNode reversedHead = null;

        while (p != null) {
            LinkedListNode node = new LinkedListNode(p.val);
            if (reversedHead == null) {
                reversedHead = node;
            } else {
                node.next = reversedHead;
                reversedHead = node;
            }
            p = p.next;
        }

        return reversedHead;
    }

    public boolean isPalindromeS2(LinkedListNode list) {

        Stack<Integer> stack = new Stack<>();
        int length = getListLength(list);
        for (int i = 0; i < length / 2; i++) {
            stack.push(list.val);
            list = list.next;
        }

        if (length % 2 == 1) {
            list = list.next;
        }

        while (!stack.isEmpty() && list != null) {
            if (list.val != stack.pop()) {
                return false;
            }
            list = list.next;
        }

        return true;

    }

    private int getListLength(LinkedListNode head) {
        int length = 0;
        LinkedListNode p = head;
        while (p != null) {
            length++;
            p = p.next;
        }
        return length;
    }

    private class Result {
        LinkedListNode node;
        boolean equal;

        public Result(LinkedListNode node, boolean equal) {
            this.node = node;
            this.equal = equal;
        }
    }

    public boolean isPalindromeS3(LinkedListNode list) {
        int length = getListLength(list);
        Result result = isPalindromeHelper(list, length);
        return result.equal;
    }

    private Result isPalindromeHelper(LinkedListNode list, int length) {
       if (list == null || length <= 0) {
           return new Result(list, true);
       } else if (length == 1) {
           return new Result(list.next, true);
       }

       Result result = isPalindromeHelper(list.next, length - 2);

       if (!result.equal || result.node == null) {
           return result;
       }

       result.equal = (list.val == result.node.val);

       result.node = result.node.next;

       return result;

    }


    public static void main(String[] args) {
        Question6 question6 = new Question6();
//        System.out.println(question6.isPalindromeS1(LinkedList.createList(new int[]{})));
//        System.out.println(question6.isPalindromeS1(LinkedList.createList(new int[]{1, 2})));
//        System.out.println(question6.isPalindromeS1(LinkedList.createList(new int[]{1, 1})));
//        System.out.println(question6.isPalindromeS1(LinkedList.createList(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1})));
//        System.out.println(question6.isPalindromeS1(LinkedList.createList(new int[]{1, 2, 5, 3, 4, 5, 4, 3, 2, 1})));
//        System.out.println(question6.isPalindromeS2(LinkedList.createList(new int[]{})));
//        System.out.println(question6.isPalindromeS2(LinkedList.createList(new int[]{1, 2})));
//        System.out.println(question6.isPalindromeS2(LinkedList.createList(new int[]{1, 1})));
//        System.out.println(question6.isPalindromeS2(LinkedList.createList(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1})));
//        System.out.println(question6.isPalindromeS2(LinkedList.createList(new int[]{1, 2, 5, 3, 4, 5, 4, 3, 2, 1})));
        System.out.println(question6.isPalindromeS3(LinkedList.createList(new int[]{})));
        System.out.println(question6.isPalindromeS3(LinkedList.createList(new int[]{1, 2})));
        System.out.println(question6.isPalindromeS3(LinkedList.createList(new int[]{1, 1})));
        System.out.println(question6.isPalindromeS3(LinkedList.createList(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1})));
        System.out.println(question6.isPalindromeS3(LinkedList.createList(new int[]{1, 2, 5, 3, 4, 5, 4, 3, 2, 1})));
    }

}
