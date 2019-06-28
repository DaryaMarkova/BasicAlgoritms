public class AVLTree {
    class Node {
        Node left;
        Node right;

        int value;
        int height;

        public Node (int value) {
           this.value = value;
           this.height = 1;
        }
        //TODO общий интерфейс печати
        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }

            System.out.println(value);

            if (right != null) {
                right.printInOrder();
            }
        }

        public int maxDepth(AVLTree.Node start) {
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

        private void fullMatrixIn(AVLTree.Node top, String[][] matrix, int row, int col, int delta) {
            matrix[row][col] =  String.valueOf(top.value);

            if (top.left != null) {
                fullMatrixIn(top.left, matrix, row + 1, col - delta, delta / 2);
            }

            if (top.right != null) {
                fullMatrixIn(top.right, matrix, row + 1, col + delta, delta / 2);
            }
        }
    }

    private Node leftRotate(Node root) {
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);
        return newRoot;
    }

    private Node rightRotate(Node root) {
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);
        return newRoot;
    }

    private int balance(Node rootLeft, Node rootRight) {
        return height(rootLeft) - height(rootRight);
    }

    private int setHeight(Node root){
        if(root == null) {
            return 0;
        }

        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
    }

    private int height(Node root) {
        return (root == null) ? 0 : root.height;
    }

    public Node insert(Node root, int data){
        if (root == null){
            return new Node(data);
        }

        if (root.value <= data) {
            root.right = insert(root.right,data);
        } else {
            root.left = insert(root.left,data);
        }

        int balance = balance(root.left, root.right);

        if (balance > 1) {
            if (height(root.left.left) >= height(root.left.right)){
                root = rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        } else if (balance < -1){
            if (height(root.right.right) >= height(root.right.left)){
                root = leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        } else {
            root.height = setHeight(root);
        }

        return root;
    }

    public void sort(Node root) {
        root.printInOrder();
    }
}
