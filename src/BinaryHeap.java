/*
* Задача - построение минимальной двоичной кучи
* Формат входа. Первая строка содержит число n. Следующая строка задаёт массив чисел A[0], . . . , A[n − 1].
* Формат выхода. Первая строка выхода должна содержать число обменов m, которое должно удовлетворять
* неравенству 0 ≤ m ≤ 4n. Каждая из последующих m строк должна задавать обмен двух элементов массива A. Каждый обмен задаётся парой различных индексов 0 ≤ i ̸= j ≤ n − 1.
* После применения всех обменов в указанном порядке массив должен превратиться в мин-кучу
*/
public class BinaryHeap {
    private int[] values;
    private int counter = 0;

    public BinaryHeap(int[] values) {
        this.values = values;
    }

    public int buildHeapFromArray(StringBuilder output) {
        for (int i = values.length - 1; i >= 0; i--) {
            siftDown(i, output);
        }

        return counter;
    }

    void siftDown(int index, StringBuilder output) {
        int left, right, swap, temp;

        while (index < values.length) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            swap = left;

            if (right < values.length) {
                swap = values[left] < values[right] ? left : right;
            }

            if (swap >= values.length) {
                return; // elem has no children
            }

            if (values[index] > values[swap]) {
                temp = values[index];
                values[index] = values[swap];
                values[swap] = temp;
                counter++;

                output.append(String.format("%d %d\n", index, swap));
            }

            index = swap;
        }
    }

    void siftUp(int index, StringBuilder output) {
        int parent = (index - 1) / 2, value, temp;

        while (parent >= 0 && index > 0) {
            value = values[index];

            if (values[index] < values[parent]) {
                temp = values[parent];
                values[parent] = value;
                values[index] = temp;

                output.append(String.format("%d %d\n", parent, index));
            }

            index = parent;
            parent = (parent - 1) / 2;
        }
    }
}
