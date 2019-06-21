public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        tree.insert(32);
        tree.insert(65);
        tree.insert(75);
        tree.insert(85);
        tree.insert(34);
        tree.insert(36);

        // tree.sort();
        // System.out.println(tree.maxDepth(tree.top));
        tree.printMatrix();
    }
}
