import java.util.ArrayList;
import java.util.List;

public class CoinProblem {
    int[] coins = {3,5,6,8};
    int totalSum = 15;

    public void calculateDynamic() {
        int rows = coins.length;
        int cols = totalSum + 1;
        int[] array = new int[cols];
        int i,j;

        array[0] = 0;

        for (j = 1; j < cols; j++)
            array[j] = Integer.MAX_VALUE;

        for (i = 0; i < rows; i++) {
            for (j = 1; j < cols; j++) {
                array[j] = Math.min(array[j], j / coins[i] + array[j % coins[i]]);
            }
        }

        System.out.printf("Min cost is %d \n", array[cols - 1]);
        print(array);
    }

    private void backTrack(int[][] matrix) {
        int i;
        int j = totalSum;

        while (j > 0) {
            for (i = 0; i + 1 < coins.length && matrix[i][j] > matrix[i + 1][j]; i++);
            System.out.printf("%2dx(%d)", coins[i], j / coins[i]);
            j %= coins[i];
        }
    }

    public List<Integer> getBestCombination(int[] coins, int restSum, List<Integer> combination) {
        if (restSum == 0) {
            combination.forEach(number -> System.out.printf("%d ", number));
            System.out.println();
            return combination;
        }

        List<Integer> nextCombination;
        List<Integer> betterCombination = new ArrayList<>();

        for (int i = 0; i < coins.length; i++) {
            if (restSum - coins[i] >= 0) {
                combination.add(coins[i]);

                nextCombination = getBestCombination(coins, restSum - coins[i], combination);

                if (betterCombination.size() == 0
                        || (nextCombination.size() > 0 && nextCombination.size() < betterCombination.size())) {
                    betterCombination = new ArrayList<>(nextCombination);
                }

                combination.remove(combination.size() - 1);
            }
        }

        return betterCombination;
    }

    public void calculateRecursive(int[] coins, int totalSum, List<Integer> selected) {
        if (totalSum == 0) {
            selected.forEach(number -> System.out.printf("%d ", number));
            System.out.println();
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            if (totalSum - coins[i] >= 0) {
                selected.add(coins[i]);
                calculateRecursive(coins, totalSum - coins[i], selected);
                selected.remove(selected.size() - 1);
            }
        }
    }

    private void print(int[] array) {
            for (int j=0; j < array.length; j++) {
                System.out.printf("%2d", array[j]);
            }

            System.out.println();
    }

    private void print(int[][] matrix) {
        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix[0].length; j++) {
                System.out.printf("%2d", matrix[i][j]);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        CoinProblem problem = new CoinProblem();
        // problem.calculateDynamic();
        // problem.calculateRecursive(new int[] {2, 4, 5, 6, 8}, 15, new ArrayList<>());
       List<Integer> list = problem.getBestCombination(new int[] {2, 4, 5, 6, 8}, 16, new ArrayList<>());
       System.out.println("----------------------------------------");
       System.out.println("Min result is " + list.toString() + " with size = " + list.size() + ".");
    }
}
