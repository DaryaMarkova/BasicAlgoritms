// https://neerc.ifmo.ru/wiki/index.php?title=%D0%94%D0%B5%D1%80%D0%B5%D0%B2%D0%BE_%D0%BF%D0%BE%D0%B8%D1%81%D0%BA%D0%B0,_%D0%BD%D0%B0%D0%B8%D0%B2%D0%BD%D0%B0%D1%8F_%D1%80%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F#.D0.A0.D0.B5.D0.B0.D0.BB.D0.B8.D0.B7.D0.B0.D1.86.D0.B8.D1.8F_.D0.B1.D0.B5.D0.B7_.D0.B8.D1.81.D0.BF.D0.BE.D0.BB.D1.8C.D0.B7.D0.BE.D0.B2.D0.B0.D0.BD.D0.B8.D1.8F_.D0.B8.D0.BD.D1.84.D0.BE.D1.80.D0.BC.D0.B0.D1.86.D0.B8.D0.B8_.D0.BE_.D1.80.D0.BE.D0.B4.D0.B8.D1.82.D0.B5.D0.BB.D0.B5
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

        public int maxDepth(Node start) {
            if (start == null) {
                return 0;
            }

            return Math.max(maxDepth(start.left), maxDepth(start.right)) + 1;
        }

        public void printMatrix() {
            int depth = maxDepth(this);
            int width = (int)Math.pow(2, depth) + 1;

            String[][] treeMatrix = new String[depth][width];

            for(int i = 0; i < depth; i++) {
                for(int j=0; j < width; j++) {
                    treeMatrix[i][j] = "";
                }
            }

            fullMatrixIn(this, treeMatrix, 0, width / 2, width / 4);

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

    private Node minimum(Node start) {
        if (start.left == null) {
            return start;
        }
        return minimum(start.left);
    }

    public Node delete(Node start, int deletedValue) {
        if (start == null) {
            return start;
        }

        if (deletedValue < start.value) {
            start.left = delete(start.left, deletedValue);
        } else if (deletedValue > start.value) {
            start.right = delete(start.right, deletedValue);
        } else if (start.left != null && start.right != null) {
            start.value = minimum(start.right).value;
            start.right = delete(start.right, start.value);
        } else {
            if (start.left != null)
                start = start.left;
            else
                start = start.right;
        }

        return start;
    }
}
