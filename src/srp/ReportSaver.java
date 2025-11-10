package srp;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReportSaver {
    public void saveToFile(int sum, double avg, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("=== Report ===\n");
            writer.write("Sum: " + sum + "\n");
            writer.write("Average: " + avg + "\n");
            writer.write("Generated at: " + LocalDateTime.now() + "\n");
            System.out.println("Report saved to " + filename);
        }
        catch (IOException e) {
            System.out.println("Error writing report file: " + e.getMessage());
        }
    }
}