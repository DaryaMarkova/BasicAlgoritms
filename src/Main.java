import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] row = new int[n];
        int i = -1;

        while (++i < n) {
            int value = scanner.nextInt();
            row[i] = value;
        }

        RowTree tree = new RowTree(n, row);
        int height = tree.getMaxHeight();
        System.out.println(height);
    }
}
