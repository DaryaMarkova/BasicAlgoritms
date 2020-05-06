public class AVLTree {
    private Node root = null;

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

    int height(Node root) {
        return (root == null) ? 0 : root.height;
    }

    public Node insert(int data) {
        root = insert(root, data);
        return root;
    }

    public Node delete(int data) {
        root = delete(root, data);
        return root;
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
                Node replace = findNodeToReplace(root);
                root.value = replace.value;
                root.left = delete(root.left, replace.value);
            }
        }

        if (root == null)
            return null;

        // reordering
        root.height = Math.max(height(root.left), height(root.right)) + 1;
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
        }

        return root;
    }

    private Node insert(Node root, int data) {
        if (root == null) {
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

    private Node findNode(Node root, int data) {
        if (root == null) {
            return null;
        }

        if (root.value == data) {
            return root;
        }

        if (root.value >= data) {
            return findNode(root.left, data);
        } else {
            return findNode(root.right, data);
        }
    }

    private Node findNodeToReplace(Node current) {
        Node next = current.left;

        while (next.right != null) {
            next = next.right;
        }

        return next;
    }


    public void sort() {
        if (root == null)
            return;

        root.printInOrder();
    }

    public static void main(String args[]){
        AVLTree avlTree = new AVLTree();
        int[] values = new int[]{2,4,7,8,10,12,14,20,40,53,61,6};

        for (int i = 0; i < values.length; i++) {
            avlTree.insert(values[i]);
        }

        avlTree.delete(2);
        avlTree.delete(7);
        avlTree.delete(4);
        Node root = avlTree.delete(6);
        avlTree.sort();
    }
}
