import java.util.Scanner;

class LongNode {
    long value;
    LongNode left;
    LongNode right;

    public LongNode(long value) {
        this.value = value;
    }
}

public class TreeTraversal {

    private LongNode getNode(long[][] array, int index) {
        LongNode node = new LongNode(array[index][0]);

        int leftIndex = (int)array[index][1];
        int rightIndex = (int)array[index][2];

        if (leftIndex > -1) {
            node.left = getNode(array, leftIndex);
        }

        if (rightIndex > -1) {
            node.right = getNode(array, rightIndex);
        }

        return node;
    }

    public LongNode getTree(long[][] array) {
        if (array.length < 1) {
            return null;
        }

        return getNode(array, 0);
    }

    public void inOrderTraverse(Node root, StringBuilder buffer) {
        if (root.left != null)
            inOrderTraverse(root.left, buffer);

        buffer.append(root.value).append(" ");

        if (root.right != null)
            inOrderTraverse(root.right, buffer);
    }

    public void preOrderTraverse(Node root, StringBuilder buffer) {
        buffer.append(root.value).append(" ");

        if (root.left != null)
            preOrderTraverse(root.left, buffer);

        if (root.right != null)
            preOrderTraverse(root.right, buffer);
    }

    public void postOrderTraverse(Node root, StringBuilder buffer) {
        if (root.left != null)
            postOrderTraverse(root.left, buffer);

        if (root.right != null)
            postOrderTraverse(root.right, buffer);

        buffer.append(root.value).append(" ");
    }

    public boolean isBST(LongNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.value < min || root.value > max ) {
            return false;
        }

        return isBST(root.left, min, root.value) && isBST(root.right, root.value, max);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[][] array = new long[n][];
        int i = 0;

        while (i < n) {
           array[i++] = new long[]{scanner.nextInt(), scanner.nextInt(), scanner.nextInt()};
        }

        TreeTraversal traversal = new TreeTraversal();
        boolean ok = traversal.isBST(traversal.getTree(array), Long.MIN_VALUE, Long.MAX_VALUE);
        String message = ok ? "CORRECT" : "INCORRECT";
        System.out.println(message);
    }
}
