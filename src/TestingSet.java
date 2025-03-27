import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestingSet {
    final ArrayList<Vector> testingVectors;

    public TestingSet(String fileName) {
        // file where the testing set was saved
        testingVectors = new ArrayList<>();

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

                double[] components = new double[26];
                char[] letter = parts[1].toCharArray();
                for (char c : letter) {
                    switch (c) {
                        case 'a': components[0]++; break;
                        case 'b': components[1]++; break;
                        case 'c': components[2]++; break;
                        case 'd': components[3]++; break;
                        case 'e': components[4]++; break;
                        case 'f': components[5]++; break;
                        case 'g': components[6]++; break;
                        case 'h': components[7]++; break;
                        case 'i': components[8]++; break;
                        case 'j': components[9]++; break;
                        case 'k': components[10]++; break;
                        case 'l': components[11]++; break;
                        case 'm': components[12]++; break;
                        case 'n': components[13]++; break;
                        case 'o': components[14]++; break;
                        case 'p': components[15]++; break;
                        case 'q': components[16]++; break;
                        case 'r': components[17]++; break;
                        case 's': components[18]++; break;
                        case 't': components[19]++; break;
                        case 'u': components[20]++; break;
                        case 'v': components[21]++; break;
                        case 'w': components[22]++; break;
                        case 'x': components[23]++; break;
                        case 'y': components[24]++; break;
                        case 'z': components[25]++; break;
                    }
                }

                testingVectors.add(new Vector(components, lang));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error reading file.");
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Vector> getTestingVectors() {
        return testingVectors;
    }
}