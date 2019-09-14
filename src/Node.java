public class Node {
    Node left;
    Node right;

    int value;
    int height;

    public Node(int value) {
        this.value = value;
        this.height = 1;
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
