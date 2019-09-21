public class MinimalEditDistance {
    String str1 = "sport";
    String str2 = "soaр";

    public static void main(String[] args) {
        MinimalEditDistance problem = new MinimalEditDistance();
        problem.calculate();
    }

    int rows = str2.length() + 1;
    int cols = str1.length() + 1;

    int[][] matrix = new int[rows][cols];
    Cell[][] history = new Cell[rows][cols];

    void calculate() {
        int val;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    matrix[i][0] = i;
                    continue;
                }

                if (i == 0) {
                    matrix[0][j] = j;
                    continue;
                }

                if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
                    val = matrix[i - 1][j - 1];
                    matrix[i][j] = val;
                    history[i][j] = new Cell(i - 1, j - 1, val);
                } else {
                    Cell min = getMinimumFromSquare(i, j);
                    matrix[i][j] = min.value + 1;
                    history[i][j] = min;
                }
            }
        }

        print();
        expand();
    }

    void expand() {
        int i = rows - 1;
        int j = cols - 1;

        Cell min;

        while (i > 0 && j > 0) {
            min = history[i][j];

            if (min.row + 1 == i && min.col + 1 == j) {
                i -= 1;
                j -= 1;

                if (min.value == matrix[i + 1][j + 1]) {
                    continue;
                }

                System.out.printf("REPLACE %c -> %c \n", str1.charAt(j), str2.charAt(i));
                continue;
            }

            if (min.row == i ) {
                j -= 1;
                System.out.printf("DELETE %c \n", str1.charAt(j));
                continue;
            }

            if (min.col == j) {
                i -= 1;
                System.out.printf("ADD %c \n", str2.charAt(i));
            }
        }
    }

    void print() {
        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix[0].length; j++) {
                System.out.printf("%2d", matrix[i][j]);
            }

            System.out.println();
        }
    }

    Cell getMinimumFromSquare(int i, int j) {
        Cell temp, min;

        Cell cell1 = new Cell(i - 1, j - 1, matrix[i - 1][j - 1]);
        Cell cell2 = new Cell(i - 1, j, matrix[i - 1][j]);
        Cell cell3 = new Cell(i, j - 1, matrix[i][j - 1]);

        temp = getMinimum(cell1, cell2);
        min = getMinimum(temp, cell3);

        return min;
    }

    Cell getMinimum(Cell a, Cell b) {
        return (a.value < b.value) ? a : b;
    }
}

class Cell {
    int row;
    int col;
    int value;

    Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
}
