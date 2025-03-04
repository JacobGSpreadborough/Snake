
public class Layer {
    int nodesIn, nodesOut;
    double[][] weights;
    double[] biases;

    public Layer(int nodesIn, int nodesOut) {
        this.nodesIn = nodesIn;
        this.nodesOut = nodesOut;

        // weights and biases initialized as random
        weights = new double[nodesIn][nodesOut];
        biases = new double[nodesOut];
        for(int j=0;j<nodesOut;j++) {
            for(int k=0;k<nodesIn;k++) {
                weights[k][j] = Math.random();
            }
            biases[j] = Math.random();

        }
    }

    public double activationFunction(double input) {
        return 1 / (1 + Math.exp(-input));
    }

    public double[] calculateOutput(double[] inputs) {
        // incoming data
        double[] output = new double[nodesOut];
        // for each node in the layer, multiply the input data by the weight of its connection
        for (int i = 0; i < nodesOut; i++) {
            for (int c = 0; c < nodesIn; c++) {
                output[i] = (inputs[c] * weights[c][i]) + biases[i];
                output[i] = activationFunction(output[i]);
            }

        }
        return output;
    }

    public static void main(String[] args) {
        Layer testLayer = new Layer(2,3);
        double[] inputs = {1,2};
        double[] outputs = testLayer.calculateOutput(inputs);
        System.out.println(outputs[2]);

    }
}