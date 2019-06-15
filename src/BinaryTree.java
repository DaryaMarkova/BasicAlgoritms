public class BinaryTree {
    class Node {
        Node left, right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        public void insert(int _value) {
            if (_value <= value) {
                if (left == null) {
                    left = new Node(_value);
                } else {
                    left.insert(_value);
                }
            } else {
                if (right == null) {
                    right = new Node(_value);
                } else {
                    right.insert(_value);
                }
            }
        }

        public boolean contains(int _value) {
            if (value == _value) {
                return true;
            } else if (value < _value) {
                if (left == null) {
                    return false;
                } else {
                    return left.contains(value);
                }
            } else {
                if (right == null) {
                    return false;
                } else {
                    return right.contains(_value);
                }
            }
        }

        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }

            System.out.println(value);

            if (right != null) {
                right.printInOrder();
            }
        }
    }

    Node top;

    public void insert(int value) {
        if (this.top == null) {
            this.top = new Node(value);
        } else {
            this.top.insert(value);
        }
    }

    public void sort() {
        this.top.printInOrder();
    }
}
