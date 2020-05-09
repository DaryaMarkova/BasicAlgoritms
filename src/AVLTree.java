public class AVLTree {
    private Node root = null;

    private Node leftRotate(Node root) {
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);

        return newRoot;
    }

    private Node rightRotate(Node root) {
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);

        return newRoot;
    }

    private int balance(Node rootLeft, Node rootRight) {
        return height(rootLeft) - height(rootRight);
    }

    private int setHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int rootLeft = root.left != null ? root.left.height : 0;
        int rootRight = root.right != null ? root.right.height : 0;

        return 1 + Math.max(rootLeft, rootRight);
    }

    private int setSize(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSize = root.left != null ? root.left.size : 0;
        int rightSize = root.right != null ? root.right.size : 0;

        return leftSize + rightSize + 1;
    }

    int height(Node root) {
        return (root == null) ? 0 : root.height;
    }

    public Node get(Node root, int index) {
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

    public Node max(Node root) {
        if (root.left == null && root.right == null) {
            return root;
        }

        return  (root.right == null ) ? max(root.left) : max(root.right);
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

        // root.height = Math.max(height(root.left), height(root.right)) + 1;
        root.height = setHeight(root);

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

        root.size = setSize(root);
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
            root.size = setSize(root);
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

    public Node merge(Node v1, Node v2) {
       Node max = max(v1);
       v1 = delete(v1, max.value); // delete works wrong
       return AVLMergeWithRoot(v1, v2, max);
    }

    public Node AVLMergeWithRoot(Node v1, Node v2, Node root) {
        if (Math.abs(v1.height - v2.height) <= 1) {
            return mergeWithRoot(v1, v2, root);
        } else if (v1.height > v2.height) {
            Node newRoot = AVLMergeWithRoot(v1.right, v2, root);
            v1.right = newRoot;
            return getRebalanced(v1);
        } else {
            Node newRoot = AVLMergeWithRoot(v2.right, v1, root);
            v2.right = newRoot;
            return getRebalanced(v2);
        }
    }

    private Node getRebalanced(Node v) {
        int balance = balance(v.left, v.right);

        if (balance > 1) {
            if (height(v.left.left) >= height(v.left.right)){
                v = rightRotate(v);
            } else {
                v.left = leftRotate(v.left);
                v = rightRotate(v);
            }
        } else if (balance < -1) {
            if (height(v.right.right) >= height(v.right.left)){
                v = leftRotate(v);
            } else {
                v.right = rightRotate(v.right);
                v = leftRotate(v);
            }
        } else {
            v.size = setSize(v);
            v.height = setHeight(v);
        }


        return v;
    }

    private Node mergeWithRoot(Node v1, Node v2, Node root) {
        root.left = v1;
        root.right = v2;

        root.height = setHeight(root);
        root.size = setSize(root);

        return root;
    }

    public void sort() {
        if (root == null)
            return;

        root.printInOrder();
    }

    public static void main(String args[]) {
//        AVLTree avlTree = new AVLTree();
//        int[] values = new int[]{2,4,7,8,10,12,14,20,40,53,61,6};
//
//        for (int i = 0; i < values.length; i++) {
//            avlTree.insert(values[i]);
//        }
//
//        System.out.println(avlTree.max(avlTree.root).value);

        AVLTree avlTree1 = new AVLTree();
        int[] values1 = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};

        for (int i = 0; i < values1.length; i++) {
            avlTree1.insert(values1[i]);
        }

        AVLTree avlTree2 = new AVLTree();
        int[] values2 = new int[]{18,19,20};

        for (int i = 0; i < values2.length; i++) {
            avlTree2.insert(values2[i]);
        }
    }
}
