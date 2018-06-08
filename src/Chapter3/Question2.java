package Chapter3;

import java.util.Stack;

/**
 * Stack Min: How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 */
class Question2 {

    private class MinStackS1 {

        private class MinStackNode {

            int value;
            int min;

            public MinStackNode(int value, int min) {
                this.value = value;
                this.min = min;
            }
        }

        private int min;
        private Stack<MinStackNode> stack;

        public MinStackS1() {
            stack = new Stack<>();
        }

        public void push(int val) {
            min = Math.min(min, val);
            stack.push(new MinStackNode(val, min));
        }

        public int min() throws Exception {
            if (stack.isEmpty()) {
                throw new Exception("Stack is empty");
            }
            return stack.peek().min;
        }

        public int pop() throws Exception {
            if (stack.isEmpty()) {
                throw new Exception("Stack is empty");
            }
            return stack.pop().value;
        }

    }

    private class MinStackS2 {

        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStackS2() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int value) throws Exception {
            if (value <= min()) {
                minStack.push(value);
            }
            stack.push(value);
        }

        public int pop() throws Exception {
            int value = stack.pop();
            if (value == min()) {
                minStack.pop();
            }
            return value;
        }

        private int min() throws Exception {
            if (minStack.isEmpty()) {
                throw new Exception("Stack is empty");
            }
            return minStack.peek();
        }

    }

}
