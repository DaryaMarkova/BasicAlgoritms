public class Stack {
    private class Node {
        private int value;
        private  Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node top;
    /**
     *
     * @return
     */
    public boolean isEmpty() {
       return top == null;
    }

    /**
     * получить значение из "головы", но не удалять
     * @return
     */
    public Integer peek() {
        if (this.isEmpty()) {
            return null;
        }

        return top.value;
    };

    /**
     * добавить значение на вершину стека
     * @param value
     */
    public void push(int value) {
        Node node = new Node(value);
        node.next = top;
        top = node;
    }

    /**
     * получить значение и удалить из "головы" очереди
     */
    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        Node top = this.top;
        top = top.next;
        return top.value;
    }
}
