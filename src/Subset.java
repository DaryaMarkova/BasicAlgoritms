public class Subset {
    //string subset generation
    public static void main(String[] args) {
        char[] source = {'A', 'B', 'C'};
        int[] counts = {2,1,1};
        char[] output = new char[4];

        new Subset().generateSubsets(source, output, counts, 0, 0);
    }

    void generateSubsets(char[] source, char[] output, int[] counts, int index, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(output[i] + " ");
        }

        System.out.println();

        for (int i = index; i < counts.length; i++) {
            if (counts[i] > 0) {
                counts[i]--;

                output[len] = source[i];
                generateSubsets(source, output, counts, i, len + 1);
                counts[i]++;
            }
        }
    }
}
