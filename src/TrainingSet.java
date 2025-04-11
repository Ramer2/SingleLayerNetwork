import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrainingSet {
    final ArrayList<Vector> trainingVectors;

    public TrainingSet(String fileName) {
        // file where the training set was saved
        trainingVectors = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                String lang = switch (parts[0].trim()) {
                    case "English" -> "eng";
                    case "Polish" -> "pol";
                    case "German" -> "ger";
                    case "Spanish" -> "esp";
                    default -> "";
                };

                String text = parts[1].toLowerCase().replaceAll("[^a-z]", "");

                double[] components = new double[26];
                char[] letter = text.toCharArray();
                for (char c : letter) {
                    components[c - 'a']++;
                }

                trainingVectors.add(new Vector(components, lang));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error reading file.");
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Vector> getTrainingVectors() {
        return trainingVectors;
    }
}