public class Main {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        Node root = null;

        root = avlTree.insert(root, -10);
        root = avlTree.insert(root, 2);
        root = avlTree.insert(root, 13);
        root = avlTree.insert(root, -13);
        root = avlTree.insert(root, -15);
        root = avlTree.insert(root, 15);
        root = avlTree.insert(root, 17);
        root = avlTree.insert(root, 20);

        root.printMatrix();
    }
}
