import java.util.ArrayList;

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
            for (Vector vector : trainingSet.trainingVectors) {
                for (Perceptron perceptron : perceptrons) perceptron.learn(vector);
            }
        }
    }

    public String predict(Vector vector) {
        int[] results = new int[perceptrons.size()];
        for (int i = 0; i < perceptrons.size(); i++) {
            results[i] = perceptrons.get(i).predict(vector);
        }
        int index = 0;
        for (int i = 0; i < results.length; i++) {
            if (results[i] == 1) {
                index = i;
                break;
            }
        }

        return perceptrons.get(index).lang;
    }

    public double test(TestingSet testingSet) {
        int correct = 0;
        int total = testingSet.getTestingVectors().size();

        for (Vector vector : testingSet.getTestingVectors())
            if (predict(vector).equals(vector.lang)) correct++;

        return ((double) correct / total);
    }

    public static void main(String[] args) {
        TrainingSet trainingSet = new TrainingSet("./src/lang.train.csv");
        SingleLayerNetwork singleLayerNetwork = new SingleLayerNetwork();
        singleLayerNetwork.train(trainingSet, 5);

        int eng = 0;
        int pol = 0;
        int ger = 0;
        int esp = 0;
        for (Vector vector : trainingSet.trainingVectors) {
            if (singleLayerNetwork.predict(vector).equals("eng")) eng++;
            if (singleLayerNetwork.predict(vector).equals("pol")) pol++;
            if (singleLayerNetwork.predict(vector).equals("ger")) ger++;
            if (singleLayerNetwork.predict(vector).equals("esp")) esp++;
        }

        System.out.println("eng: " + eng);
        System.out.println("pol: " + pol);
        System.out.println("ger: " + ger);
        System.out.println("esp: " + esp);

        TestingSet testingSet = new TestingSet("./src/lang.test.csv");
        System.out.println(singleLayerNetwork.test(testingSet));
    }
}
