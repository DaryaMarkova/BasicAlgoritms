public class Node {
    Node left;
    Node right;

    int value;
    int height;
    int size;

    public Node(int value) {
        this.value = value;
        this.height = 1;
        this.size = 1;
    }

    public Node insert(int data) {
        return insert(this, data);
    }

    public Node delete(int data) {
        return delete(this, data);
    }

    public Node get(int index) {
        return get(this, index);
    }

    public Node max() {
        return max(this);
    }

    private Node get(Node root, int index) {
        int leftSize = root.left == null ? 0 : root.left.size;

        if (index == leftSize + 1) {
            return root;
        }

        if (index < leftSize + 1) {
            return get(root.left, index);
        } else {
            return get(root.right, index - leftSize - 1);
        }
    }

    private Node max(Node root) {
        if (root.left == null && root.right == null) {
            return root;
        }

        return  (root.right == null ) ? max(root.left) : max(root.right);
    }

    private Node delete(Node root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.value)
            root.left = delete(root.left, data);
        else if (data > root.value)
            root.right = delete(root.right, data);
        else {
            // one child or no child
            if (root.left == null || root.right == null) {
                root = (root.left == null) ? root.right : root.left;
            } else {
                // both children are in place
                Node replace = root.findNodeToReplace();
                root.value = replace.value;
                root.left = delete(root.left, replace.value);
            }
        }

        if (root == null)
            return null;

        root.height = root.getNewHeight();

        int balance = root.balance();

        if (balance > 1) {
            if (height(root.left.left) >= height(root.left.right)){
                root = root.rightRotate();
            } else {
                root.left = root.left.leftRotate();
                root = root.rightRotate();
            }
        } else if (balance < -1) {
            if (height(root.right.right) >= height(root.right.left)){
                root = root.leftRotate();
            } else {
                root.right = root.right.rightRotate();
                root = root.leftRotate();
            }
        }

        root.size = root.getNewSize();
        return root;
    }

    private Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (root.value <= data) {
            root.right = insert(root.right, data);
        } else {
            root.left = insert(root.left, data);
        }

        int balance = root.balance();

        if (balance > 1) {
            if (height(root.left.left) >= height(root.left.right)){
                root = root.rightRotate();
            } else {
                root.left = root.left.leftRotate();
                root = root.rightRotate();
            }

        } else if (balance < -1) {
            if (height(root.right.right) >= height(root.right.left)){
                root = root.leftRotate();
            } else {
                root.right = root.right.rightRotate();
                root = root.leftRotate();
            }

        } else {
            root.height = root.getNewHeight();
            root.size = root.getNewSize();
        }

        return root;
    }

    public Node getRebalanced() {
        int balance = balance();

        if (balance > 1) {
            if (height(this.left.left) >= height(this.left.right)){
                return rightRotate();
            } else {
                this.left = this.left.leftRotate();
                return this.left.rightRotate();
            }
        } else if (balance < -1) {
            if (height(this.right.right) >= height(this.right.left)){
                return leftRotate();
            } else {
                this.right = this.right.rightRotate();
                return this.right.leftRotate();
            }
        } else {
            this.size = this.getNewSize();
            this.height = this.getNewHeight();
        }

        return this;
    }

    public void sort() {
        this.printInOrder();
    }

    private int height(Node root) {
        return (root == null) ? 0 : root.height;
    }

    private Node findNodeToReplace() {
        Node next = this.left;

        while (next.right != null) {
            next = next.right;
        }

        return next;
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }

        System.out.print(value + " ");

        if (right != null) {
            right.printInOrder();
        }
    }

    public int balance() {
        int leftHeight = this.left == null ? 0 : this.left.height;
        int rightHeight = this.right == null ? 0 : this.right.height;

        return leftHeight - rightHeight;
    }

    public int maxDepth(Node start) {
        if (start == null) {
            return 0;
        }

        return Math.max(maxDepth(start.left), maxDepth(start.right)) + 1;
    }

    protected Node leftRotate() {
        Node newRoot = this.right;
        this.right = this.right.left;
        newRoot.left = this;

        this.height = getNewHeight();
        this.size = getNewSize();

        newRoot.height = newRoot.getNewHeight();
        newRoot.size = newRoot.getNewSize();

        return newRoot;
    }

    protected Node rightRotate() {
        Node newRoot = this.left;
        this.left = this.left.right;
        newRoot.right = this;

        this.height = getNewHeight();
        this.size = getNewSize();
        newRoot.height = newRoot.getNewHeight();
        newRoot.size = newRoot.getNewSize();

        return newRoot;
    }

    protected int getNewHeight() {
        int rootLeft = this.left != null ? this.left.height : 0;
        int rootRight = this.right != null ? this.right.height : 0;

        return 1 + Math.max(rootLeft, rootRight);
    }

    protected int getNewSize() {
        int leftSize = this.left != null ? this.left.size : 0;
        int rightSize = this.right != null ? this.right.size : 0;

        return leftSize + rightSize + 1;
    }

    public void printMatrix() {
        int depth = maxDepth(this);
        int width = (int)Math.pow(2, depth) + 1;

        String[][] treeMatrix = new String[depth][width];

        for (int i = 0; i < depth; i++) {
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
