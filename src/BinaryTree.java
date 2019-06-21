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

        public Node findNode(int searchValue) {
            if (value == searchValue) {
                return this;
            } else if (searchValue < value) {
                if (left == null) {
                    return null;
                } else {
                    return left.findNode(searchValue);
                }
            } else {
                if (right == null) {
                    return null;
                } else {
                    return right.findNode(searchValue);
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

    public Node findNode(int searchValue) {
        return this.top.findNode(searchValue);
    }

    public int maxDepth(Node start) {
        if (start == null) {
            return 0;
        }

        return Math.max(maxDepth(start.left), maxDepth(start.right)) + 1;
    }

    public void printMatrix() {
        int depth = maxDepth(this.top);
        int width = (int)Math.pow(2, depth) + 1;

        String[][] treeMatrix = new String[depth][width];

        for(int i = 0; i < depth; i++) {
            for(int j=0; j < width; j++) {
                treeMatrix[i][j] = "";
            }
        }

        fullMatrixIn(this.top, treeMatrix, 0, width / 2, width / 4);

        for(int i = 0; i < depth; i++) {
            for(int j=0; j < width; j++) {
                System.out.print(treeMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    private void fullMatrixIn(Node top, String[][] matrix, int row, int col, int delta) {
        matrix[row][col] =  String.valueOf(top.value);

        if (top.left != null) {
            fullMatrixIn(top.left, matrix, row + 1, col - delta, delta / 2);
        }

        if (top.right != null) {
            fullMatrixIn(top.right, matrix, row + 1, col + delta, delta / 2);
        }
    }
}
