public class Queue {
    private class Node {
        private int value;
        private  Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * получить значение из "головы", но не удалять
     * @return
     */
    public Integer peek() {
        if (this.isEmpty()) {
            return null;
        }

        return head.value;
    };

    /**
     * добавить значение в "хвост" очереди
     * @param value
     */
    public void push(int value) {
        Node node = new Node(value);

        if (this.tail != null) {
            tail.next = node;
        }

        tail = node;

        if (head == null) {
            head = node;
        }
    }

    /**
     * получить значение и удалить из "головы" очереди
     */
    public Number pop() {
        if (head == null) {
            return null;
        }

        int value = head.value;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        return value;
    }
}
