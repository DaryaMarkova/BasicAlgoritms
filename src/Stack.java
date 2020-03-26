public class Stack {
    private Node top;
    private class Node {
        private int value;
        private int max;
        private  Node next;

        Node (int value) {
            this.value = value;
            this.max = Integer.MIN_VALUE;
        }
    }

    public boolean isEmpty() {
       return top == null;
    }

    public Integer peek() {
        if (this.isEmpty()) {
            return null;
        }

        return top.value;
    }

    public void push(int value) {
        Node node = new Node(value);

        if (isEmpty()) {
            node.max = value;
        } else {
            node.max = value > top.max ? value : top.max;
        }

        node.next = top;
        top = node;
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }

        Node last = this.top;
        top = last.next;

        return last.value;
    }

    public Integer max() {
        if (top == null) {
            return null;
        }

        return top.max;
    }
}
