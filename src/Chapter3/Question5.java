package Chapter3;

import java.util.Stack;

/**
 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
 */
public class Question5 {

    private static class SortStack {

        private Stack<Integer> stack;
        private Stack<Integer> buffer;

        public SortStack() {
            stack = new Stack<>();
            buffer = new Stack<>();
        }

        public void push(int value) {
            if (stack.isEmpty()) {
                stack.push(value);
            } else {
                while (!stack.isEmpty() && stack.peek() > value) {
                    buffer.push(stack.pop());
                }
                stack.push(value);
                while (!buffer.isEmpty()) {
                    stack.push(buffer.pop());
                }
            }
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }

    }

    public void sortStack(Stack<Integer> stack) {

        Stack<Integer> buffer = new Stack<>();

        while (!stack.isEmpty()) {
            int value = stack.pop();
            while (!buffer.isEmpty() && buffer.peek() > value) {
                stack.push(buffer.pop());
            }
            buffer.push(value);
        }

        while (!buffer.isEmpty()) {
            stack.push(buffer.pop());
        }

    }

}
