public class MaxQueue {
    private Stack input;
    private Stack output;

    public MaxQueue() {
        input = new Stack();
        output = new Stack();
    }

    public void push(int value) {
        input.push(value);
    }

    public Integer pop() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        return output.pop();
    }

    public Integer max() {
        Integer inputMax = input.max();
        Integer outputMax = output.max();

        if (inputMax != null && outputMax != null) {
            return Math.max(inputMax, outputMax);
        }

        return inputMax != null ? inputMax : outputMax;
    }
}
