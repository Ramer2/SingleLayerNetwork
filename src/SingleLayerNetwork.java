import java.util.ArrayList;
import java.util.Scanner;

public class SingleLayerNetwork {

    private final ArrayList<Perceptron> perceptrons;

    public SingleLayerNetwork() {
        perceptrons = new ArrayList<>();
        double learningRate = (Math.random() * 0.1);

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
        TestingSet testingSet = new TestingSet("./src/lang.test.csv");
        Scanner sc = new Scanner(System.in);

        SingleLayerNetwork singleLayerNetwork = new SingleLayerNetwork();
        singleLayerNetwork.train(trainingSet, 100);

        System.out.println("Hello! Welcome to the best Single Layer Network in the world!!!");
        System.out.println("The system was already trained. Please, select the next action");
        while (true) {
            System.out.println("1. Evaluate the accuracy on a testing set");
            System.out.println("2. Evaluate the language of the text");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String input = sc.nextLine();
            if (input.equals("3")) {
                System.out.println("Byeeeee!");
                return;
            } else if (input.equals("1")) {
                System.out.println("The accuracy on the testing set: " + singleLayerNetwork.test(testingSet) * 100 + "%");
            } else if (input.equals("2")) {
                System.out.print("Please, enter your text: ");
                String text = sc.nextLine();
                text = text.toLowerCase().replaceAll("[^a-z]", "");

                double[] components = new double[26];
                char[] letter = text.toCharArray();
                for (char c : letter) {
                    components[c - 'a']++;
                }

                Vector check = new Vector(components, "none");
                if (singleLayerNetwork.predict(check).equals("eng")) System.out.println("The language of the given text is English.");
                else if (singleLayerNetwork.predict(check).equals("pol")) System.out.println("The language of the given text is Polish.");
                else if (singleLayerNetwork.predict(check).equals("ger")) System.out.println("The language of the given text is German.");
                else if (singleLayerNetwork.predict(check).equals("esp")) System.out.println("The language of the given text is Spanish.");
                else System.out.println("The network couldn't figure out the language of the given text... somehow...");
            } else {
                System.out.println("Invalid choice, please try again");
            }
        }
    }
}
