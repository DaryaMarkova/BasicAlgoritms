public class AVLTree {
    public Node merge(Node v1, Node v2) {
        Node max = v1.max();
        v1 = v1.delete(max.value);
        return AVLMergeWithRoot(v1, v2, max);
    }

    private Node AVLMergeWithRoot(Node v1, Node v2, Node root) {
        if (Math.abs(v1.height - v2.height) <= 1) {
            return mergeWithRoot(v1, v2, root);
        } else if (v1.height > v2.height) {
            Node newRoot = AVLMergeWithRoot(v1.right, v2, root);
            v1.right = newRoot;
            return v1.getRebalanced();
        } else {
            Node newRoot = AVLMergeWithRoot(v2.right, v1, root);
            v2.right = newRoot;
            return v2.getRebalanced();
        }
    }

    private Node mergeWithRoot(Node v1, Node v2, Node root) {
        root.left = v1;
        root.right = v2;

        root.height = root.getNewHeight();
        root.size = root.getNewSize();

        return root;
    }

    public static void main(String args[]) {

        int[] values1 = new int[]{1,4,2,3,5,6,8,7,9,11,10, 15, 25, 17};

        Node root1  = new Node(values1[0]);

        for (int i = 1; i < values1.length; i++) {
            root1 = root1.insert(values1[i]);
        }

        int[] values2 = new int[] {30, 40, 50};
        Node root2 = new Node(values2[0]);

        for (int i = 1; i < values2.length; i++) {
            root2 = root2.insert(values2[i]);
        }

        AVLTree tree = new AVLTree();
        Node merged = tree.merge(root1, root2);
        merged.printMatrix();
    }
}
