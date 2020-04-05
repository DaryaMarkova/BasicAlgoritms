import java.util.PriorityQueue;
import java.util.Scanner;

/*
У вас имеется n процессоров и последовательность из m задач. Для каждой задачи дано время, необходимое на её обработку. Очеред- ная работа поступает к первому доступному процессору (то есть если доступных процессоров несколько, то доступный процессор с мини- мальным номером получает эту работу).
Формат входа. Первая строка входа содержит числа n и m. Вторая содержит числа t0, . . . , tm−1, где ti — время, необходимое на об- работку i-й задачи. Считаем, что и процессоры, и задачи нуме- руются с нуля.
Формат выхода. Выход должен содержать ровно m строк: i-я (счи- тая с нуля) строка должна содержать номер процессора, который получит i-ю задачу на обработку, и время, когда это произойдёт.
* */
class Processor {
    long time; // time when processor is freed
    int number; // number of processor

    Processor(long time, int number) {
        this.time = time;
        this.number = number;
    }
}

class MultiProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Processor> pQueue;

        int i;
        int pCount = scanner.nextInt();
        int tCount = scanner.nextInt();

        pQueue = new PriorityQueue<>(pCount, (p1, p2) -> {
            if (p1.time == p2.time) {
                return p1.number - p2.number;
            }

            return (int)(p1.time - p2.time);
        });

        // initialization of processors
        for (i = 0; i < pCount; i++) {
            pQueue.add(new Processor(0, i));
        }

        StringBuilder report = new StringBuilder();
        Processor processor;
        long time;
        i = 0;

        // emulating tasks processing
        while (i++ < tCount) {
            time = scanner.nextLong();
            processor = pQueue.poll();
            report.append(String.format("%d %d\n", processor.number, processor.time));
            processor.time += time;
            pQueue.add(processor);
        }

        System.out.print(report);
    }
}
