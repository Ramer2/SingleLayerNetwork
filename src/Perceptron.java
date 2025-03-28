import java.util.Arrays;

public class Perceptron {
    double[] weights;
    double bias;
    double learningRate;
    String lang; // eng, pol, ger or esp

    public Perceptron(double[] weights, double bias, double learningRate, String lang) {
        this.weights = weights;
        this.bias = bias;
        this.learningRate = learningRate;
        this.lang = lang;
    }

    public int predict(Vector testVector) {
        double product = 0;
        for (int i = 0; i < testVector.components.length; i++) {
            product += testVector.components[i] * weights[i];
        }

        if (product - bias >= 0) return 1;
        else return 0;
    }

    public void updateWeights(Vector trainVector, double label) {
        double prediction = predict(trainVector);

        for (int j = 0; j < weights.length; j++) {
            weights[j] += learningRate * (label - prediction) * trainVector.components[j];
        }
        bias -= learningRate * (label - prediction);
    }

    public void learn(Vector vector) {
        int language = vector.lang.equals(lang) ? 1 : 0;
        updateWeights(vector, language);
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "weights=" + Arrays.toString(weights) +
                ", bias=" + bias +
                ", learningRate=" + learningRate +
                ", lang='" + lang + '\'' +
                '}';
    }
}
