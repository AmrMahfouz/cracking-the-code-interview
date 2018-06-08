package Chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks. push () and SetOfStacks. pop() should behave identically to a single stack
 * (that is, pop ( ) should return the same values as it would if there were just a single stack).
 * FOLLOW UP
 * Implement a function popAt (int index) which performs a pop operation on a specific substack.
 */
class Question3 {

    private static class SetOfStacks {

        private List<Stack<Integer>> stacks;
        private int stackMaxSize;

        public SetOfStacks(int stackMaxSize) {
            this.stackMaxSize = stackMaxSize;
            stacks = new ArrayList<>();
        }

        public void push(int v) {
            Stack<Integer> last = getLastStack();
            if (last != null && !isFull(last)) {
                last.push(v);
            } else {
                Stack<Integer> stack = new Stack<>();
                stack.push(v);
                stacks.add(stack);
            }
        }

        private boolean isFull(Stack<Integer> stack) {
            return stack.size() == stackMaxSize;
        }

        public Stack getLastStack() {
            if (stacks.isEmpty()) {
                return null;
            }
            return stacks.get(stacks.size() - 1);
        }

        public int pop() throws Exception {
            Stack<Integer> last = getLastStack();
            if (last == null) {
                throw new Exception("Stack is empty");
            }
            int v = last.pop();
            if (last.size() == 0) {
                stacks.remove(stacks.size() - 1);
            }
            return v;
        }

        public boolean isEmpty() {
            return stacks.isEmpty();
        }

        public int popAt(int index) throws Exception {
            if (index == stacks.size() - 1) {
                return stacks.get(index).pop();
            }
            if (index < 0 || index >= stacks.size()) {
                throw new Exception("Invalid stack index");
            }
            Stack<Integer> stack = stacks.get(index);
            int removedElement = stack.pop();
            for (int i = index; i < stacks.size() - 1; i++) {
                Stack<Integer> buffer = new Stack<>();
                while (!stacks.get(i).isEmpty()) {
                    buffer.push(stacks.get(i).pop());
                }
                buffer.push(stacks.get(i + 1).pop());
                if (stacks.get(i + 1).isEmpty()) {
                    stacks.remove(i + 1);
                }
                stacks.remove(i);
                stacks.add(i, buffer);
            }
            return removedElement;
        }

    }

    public static void main(String[] args) throws Exception {
        SetOfStacks setOfStacks = new SetOfStacks(3);
        setOfStacks.push(1);
        setOfStacks.push(2);
        setOfStacks.push(3);
        setOfStacks.push(4);
        System.out.println(setOfStacks.popAt(0));
        System.out.println(setOfStacks.popAt(0));
    }

}
