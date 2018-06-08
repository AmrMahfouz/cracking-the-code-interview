package Chapter3;

import java.util.Stack;

/**
 * Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
 */
class Question4 {

    private static class MyQueue {

        private Stack<Integer> enqueueStack;
        private Stack<Integer> dequeueStack;

        public MyQueue() {
            enqueueStack = new Stack<>();
            dequeueStack = new Stack<>();
        }

        public void enqueue(int value) {
            while (!dequeueStack.isEmpty()) {
                enqueueStack.push(dequeueStack.pop());
            }
            enqueueStack.push(value);
        }

        public int dequeue() {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
            return dequeueStack.pop();
        }

        public int size() {
            if (!enqueueStack.isEmpty()) {
                return enqueueStack.size();
            }
            return dequeueStack.size();
        }

        public int peek() {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
            return dequeueStack.peek();
        }

    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.size());
    }

}
