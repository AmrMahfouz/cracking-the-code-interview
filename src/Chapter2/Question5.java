package Chapter2;

import java.util.Stack;

/**
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
 * function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .Thatis,617 + 295.
 * Output: 2 - > 1 - > 9. That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).Thatis,617 + 295.
 * Output: 9 - > 1 - > 2. That is, 912.
 * SOLUTION
 */
class Question5 {

    public LinkedListNode sumNumsReversed(LinkedListNode num1, LinkedListNode num2) {
        LinkedListNode sumHead = null;
        LinkedListNode sumP = null;

        int carry = 0;

        while (num1 != null && num2 != null) {
            int sum = num1.val + num2.val + carry;
            carry = sum / 10;
            if (sumHead == null) {
                sumHead = new LinkedListNode(sum % 10);
                sumP = sumHead;
            } else {
                sumP.next = new LinkedListNode(sum % 10);
                sumP = sumP.next;
            }
            num1 = num1.next;
            num2 = num2.next;
        }

        while (num1 != null) {
            int sum = num1.val + carry;
            carry = sum / 10;
            sumP.next = new LinkedListNode(sum % 10);
            sumP = sumP.next;
            num1 = num1.next;
        }

        while (num2 != null) {
            int sum = num2.val + carry;
            carry = sum / 10;
            sumP.next = new LinkedListNode(sum % 10);
            sumP = sumP.next;
            num2 = num2.next;
        }

        if (carry == 1) {
            sumP.next = new LinkedListNode(carry);
        }

        return sumHead;
    }

    public LinkedListNode sumNumsForward(LinkedListNode num1, LinkedListNode num2) {

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (num1 != null) {
            stack1.push(num1.val);
            num1 = num1.next;
        }

        while (num2 != null) {
            stack2.push(num2.val);
            num2 = num2.next;
        }

        LinkedListNode sumHead = null;
        int carry = 0;

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int sum = stack1.pop() + stack2.pop() + carry;
            carry = sum / 10;
            if (sumHead == null) {
                sumHead = new LinkedListNode(sum % 10);
            } else {
                LinkedListNode node = new LinkedListNode(sum % 10);
                node.next = sumHead;
                sumHead = node;
            }
        }

        while (!stack1.isEmpty()) {
            int sum = stack1.pop() + carry;
            carry = sum / 10;
            LinkedListNode node = new LinkedListNode(sum % 10);
            node.next = sumHead;
            sumHead = node;
        }

        while (!stack2.isEmpty()) {
            int sum = stack2.pop() + carry;
            carry = sum / 10;
            LinkedListNode node = new LinkedListNode(sum % 10);
            node.next = sumHead;
            sumHead = node;
        }

        if (carry == 1) {
            LinkedListNode node = new LinkedListNode(carry);
            node.next = sumHead;
            sumHead = node;
        }

        return sumHead;

    }

    public LinkedListNode sumNumsForwardRecursive(LinkedListNode num1, LinkedListNode num2) {
        // padding the shorter list with zeros at beginning
        int length1 = getListLength(num1);
        int length2 = getListLength(num2);
        int diff = Math.abs(length2 - length1);
        if (length1 < length2) {
            num1 = paddingList(num1, diff);
        } else if (length1 > length2) {
            num2 = paddingList(num2, diff);
        }

        SumNode sumNode = sumNumsForwardRecursiveHelper(num1, num2);

        if (sumNode.carry == 1) {
            LinkedListNode node = new LinkedListNode(sumNode.carry);
            node.next = sumNode.node;
            sumNode.node = node;
        }

        return sumNode.node;
    }

    private class SumNode {
        LinkedListNode node;
        int carry;
    }

    private SumNode sumNumsForwardRecursiveHelper(LinkedListNode num1, LinkedListNode num2) {
        if (num1 == null && num2 == null) {
            return new SumNode();
        }
        SumNode sumNode = sumNumsForwardRecursiveHelper(num1.next, num2.next);
        int sum = num1.val + num2.val + sumNode.carry;
        LinkedListNode node = new LinkedListNode(sum % 10);
        node.next = sumNode.node;
        sumNode.node = node;
        sumNode.carry = sum / 10;
        return sumNode;
    }

    private int getListLength(LinkedListNode num) {
        int length1 = 0;
        LinkedListNode p = num;
        while (p != null) {
            length1++;
            p = p.next;
        }
        return length1;
    }

    private LinkedListNode paddingList(LinkedListNode num, int diff) {
        for (int i = 0; i < diff; i++) {
            LinkedListNode node = new LinkedListNode(0);
            node.next = num;
            num = node;
        }
        return num;
    }

    public static void main(String[] args) {
        Question5 question5 = new Question5();
//        LinkedListNode sum = question5.sumNumsReversed(LinkedList.createList(new int[]{7, 1, 6}), LinkedList.createList(new int[]{5, 9, 2}));
//        LinkedListNode sum = question5.sumNumsReversed(LinkedList.createList(new int[]{9, 9, 9, 9}), LinkedList.createList(new int[]{9, 9, 9}));
//        LinkedListNode sum = question5.sumNumsForward(LinkedList.createList(new int[]{6, 1, 7}), LinkedList.createList(new int[]{2, 9, 5}));
//        LinkedListNode sum = question5.sumNumsForwardRecursive(LinkedList.createList(new int[]{6, 1, 7}), LinkedList.createList(new int[]{2, 9, 5}));
        LinkedListNode sum = question5.sumNumsForwardRecursive(LinkedList.createList(new int[]{9, 9, 9}), LinkedList.createList(new int[]{9, 9}));
        LinkedList.printList(sum);
    }

}
