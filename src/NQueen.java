
public class NQueen {
    int size = 8;
    int[][] matrix = new int[size][size];

    boolean isSafe(int row, int col) {
        int i;

        for (i = 0; i < size; i++) {
            if (isBusy(row - i, col - i))
                return false;

            if (isBusy(row - i, col + i))
                return false;

            if (isBusy(row - i, col))
                return false;
        }

        return true;
    }

    private boolean isBusy(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && matrix[x][y] == 1;
    }

    public static void main(String[] args) {
        new NQueen().setQueen(0);
    }

    private void print() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    void setQueen(int row) {
        if (row == size) {
            System.out.println("FOUND:");
            print();
            return;
        }

        // print();

        for (int i = 0; i < size; i++) {
            if (isSafe(row, i)) {
                matrix[row][i] = 1;
                setQueen(row + 1);
                matrix[row][i] = 0;
            }
        }
    }
}
