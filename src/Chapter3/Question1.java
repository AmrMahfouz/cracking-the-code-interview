package Chapter3;

/**
 * Three in One: Describe how you could use a single array to implement three stacks.
 */
class Question1 {

    private static class MultiStackFixed {

        private final int numOfStacks = 3;
        private int stackCapacity;
        private int[] values;
        private int[] sizes;

        public MultiStackFixed(int stackCapacity) {
            this.stackCapacity = stackCapacity;
            values = new int[stackCapacity * numOfStacks];
            sizes = new int[numOfStacks];
        }

        public void push(int stackNum, int value) throws Exception {
            if (isFull(stackNum)) {
                throw new Exception("Stack is full");
            }
            sizes[stackNum]++;
            values[indexOfTop(stackNum)] = value;
        }

        public int pop(int stackNum) throws Exception {
            if (isEmpty(stackNum)) {
                throw new Exception("Stack is empty");
            }
            int topIndex = indexOfTop(stackNum);
            int value = values[topIndex];
            values[topIndex] = 0;
            sizes[stackNum]--;
            return value;
        }

        public int peek(int stackNum) throws Exception {
            if (isEmpty(stackNum)) {
                throw new Exception("Stack is empty");
            }
            return values[indexOfTop(stackNum)];
        }

        private boolean isEmpty(int stackNum) {
            return sizes[stackNum] == 0;
        }

        private int indexOfTop(int stackNum) {
            int offset = stackNum * stackCapacity;
            int size = sizes[stackNum];
            return size + offset - 1;
        }

        private boolean isFull(int stackNum) {
            return sizes[stackNum] == stackCapacity;
        }

    }

    private static class MultiStackFlexible {

        private class StackInfo {
            public int start, size, capacity;

            public StackInfo(int start, int capacity) {
                this.start = start;
                this.capacity = capacity;
            }

            public boolean isWithinStackCapacity(int index) {
                if (index < 0 || index >= values.length) {
                    return false;
                }

                int contiguousIndex = index < start ? index + values.length : index;
                int end = start + capacity;
                return start <= contiguousIndex && contiguousIndex < end;
            }

            public int lastCapacityIndex() {
                return adjustIndex(start + size - 1);
            }

            public int lastElementIndex() {
                return adjustIndex(start + size - 1);
            }

            public boolean isFull() {
                return size == capacity;
            }

            public boolean isEmpty() {
                return size == 0;
            }

        }

        private StackInfo[] info;
        private int[] values;

        public MultiStackFlexible(int numberOfStacks, int defaultSize) {
            info = new StackInfo[numberOfStacks];
            for (int i = 0; i < numberOfStacks; i++) {
                info[i] = new StackInfo(defaultSize * i, defaultSize);
            }
            values = new int[numberOfStacks * defaultSize];
        }

        public void push(int stackNum, int value) throws Exception {
            if (allStacksAreFull()) {
                throw new Exception("Stack is full");
            }
            StackInfo stackInfo = info[stackNum];
            if (stackInfo.isFull()) {
                expand(stackNum);
            }
            stackInfo.size++;
            values[stackInfo.lastElementIndex()] = value;
        }

        public int pop(int stackNum) throws Exception {
            StackInfo stackInfo = info[stackNum];
            if (stackInfo.isEmpty()) {
                throw new Exception("Stack is empty");
            }
            int value = values[stackInfo.lastElementIndex()];
            values[stackInfo.lastElementIndex()] = 0;
            stackInfo.size--;
            return value;
        }

        public int peek(int stackNum) {
            StackInfo stackInfo = info[stackNum];
            return values[stackInfo.lastElementIndex()];
        }

        private void expand(int stackNum) {
            shift((stackNum + 1) % info.length);
            info[stackNum].capacity++;
        }

        private void shift(int stackNum) {
            StackInfo stackInfo = info[stackNum];
            if (stackInfo.size >= stackInfo.capacity) {
                int nextStack = (stackNum + 1) % info.length;
                shift(nextStack);
                stackInfo.capacity++;
            }
            int index = stackInfo.lastCapacityIndex();
            while (stackInfo.isWithinStackCapacity(index)) {
                values[index] = values[previousIndex(index)];
                index = previousIndex(index);
            }
            values[stackInfo.start] = 0;
            stackInfo.start = nextIndex(stackInfo.start);
            stackInfo.capacity--;
        }

        public int numberOfElements() {
            int size = 0;
            for (StackInfo sd : info) {
                size += sd.size;
            }
            return size;
        }

        private boolean allStacksAreFull() {
            return numberOfElements() == values.length;
        }

        private int adjustIndex(int index) {
            int max = values.length;
            return ((index % max) + max) % max;
        }

        private int nextIndex(int index) {
            return adjustIndex(index + 1);
        }

        private int previousIndex(int index) {
            return adjustIndex(index - 1);
        }

    }

}
