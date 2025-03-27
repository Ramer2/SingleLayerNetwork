import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SingleLayerNetwork {

    private final ArrayList<Perceptron> perceptrons;

    public SingleLayerNetwork() {
        perceptrons = new ArrayList<>();
        double learningRate = Math.random();

        for (String lang : new String[]{"eng", "pol", "ger", "esp"}) {
            double[] weights = new double[26];
            for (int i = 0; i < 26; i++) {
                weights[i] = Math.random();
            }

            double bias = Math.random();
            perceptrons.add(new Perceptron(weights, bias, learningRate, lang));
        }
    }

    public void train(TrainingSet trainingSet, int epochs) {
        for (int i = 0; i< epochs; i++) {
            Collections.shuffle(trainingSet.trainingVectors);
            for (Vector vector : trainingSet.trainingVectors) {
                for (Perceptron perceptron : perceptrons) perceptron.learn(vector);
            }
        }
    }

    public static void main(String[] args) {
        TrainingSet trainingSet = new TrainingSet("./src/lang.train.csv");
        SingleLayerNetwork singleLayerNetwork = new SingleLayerNetwork();
        singleLayerNetwork.train(trainingSet, 1000);

        for (Perceptron perceptron : singleLayerNetwork.perceptrons) System.out.println(Arrays.toString(perceptron.weights));
        System.out.println();

        int eng = 0;
        int pol = 0;
        int ger = 0;
        int esp = 0;
        for (Vector vector : trainingSet.trainingVectors) {
            for (Perceptron perceptron : singleLayerNetwork.perceptrons) {
                if (perceptron.predict(vector) >= 0.5) {
                    if (perceptron.lang.equals("eng")) eng++;
                    if (perceptron.lang.equals("pol")) eng++;
                    if (perceptron.lang.equals("ger")) eng++;
                    if (perceptron.lang.equals("esp")) eng++;
                }
            }
        }

        System.out.println("eng: " + eng);
        System.out.println("pol: " + pol);
        System.out.println("ger: " + ger);
        System.out.println("esp: " + esp);
    }
}
