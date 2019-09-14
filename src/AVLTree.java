public class AVLTree {
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
        if (root == null) {
            return 0;
        }

        int rootLeft = root.left != null ? root.left.height : 0;
        int rootRight = root.right != null ? root.right.height : 0;

        return 1 + Math.max(rootLeft, rootRight);
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
        } else if (balance < -1) {
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
