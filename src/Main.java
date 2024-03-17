import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\gajda\\Downloads\\python-3.12.2-amd64.exe";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        HashingManager hashingManager = new HashingManager();
        try {
            Map<String, String> hashTimes = hashingManager.hashFileWithAllAlgorithms(file);
            System.out.println("Hashing completed:");
            for (Map.Entry<String, String> entry : hashTimes.entrySet()) {
                System.out.println("Algorithm: " + entry.getKey() + ", Time: " + entry.getValue() + " ms");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}