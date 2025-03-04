public class NeuralNet {

    Layer[] layers;

    public static int indexOfMax(double[] array) {
        int maxAt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[maxAt])
                maxAt = i;
        }
        return maxAt;
    }

    public static double square(double num) {
        return num * num;
    }

    public NeuralNet(int[] layerSizes) {

        // the first "layer" is just the array of inputs so we only need to create 2 new
        // ones
        layers = new Layer[layerSizes.length - 1];

        // for each layer, use its size and the size of the one after it
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(layerSizes[i], layerSizes[i + 1]);
        }

    }

    public static void updateInputs(double[] inputs, int xPosition, int yPosition, double state) {
        int index = xPosition + (yPosition * GridOfSquares.rows);
        // 1 for occupied, 0 for unoccupied
        inputs[index] = state;
        System.out.println(index);
    }

    public double[] calculateOutputs(double[] inputs) {
        for (int i = 0; i < layers.length; i++) {
            inputs = layers[i].calculateOutput(inputs);
        }
        return inputs;
    }

    public int Classify(double[] inputs) {
        double[] outputs = calculateOutputs(inputs);
        return indexOfMax(outputs);
    }

    // returns cost of one output node
    public double nodeCost(double input, double key) {
        return square(input - key);
    }

    // adds up cost of all output nodes
    public double cost(double[] input, int key) {
        double[] expectedOutput = new double[10];

        // put MNIST key into array of zeros
        for (int i = 0; i < 10; i++) {
            if (i != key) {
                expectedOutput[i] = 0;
            } else {
                expectedOutput[i] = key;
            }
        }
        double totalCost = 0;
        for (int i = 0; i < input.length; i++) {
            totalCost += nodeCost(input[i], expectedOutput[i]);
        }
        return totalCost / input.length;
    }
}