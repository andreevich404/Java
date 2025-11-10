package srp;

import java.time.LocalDateTime;

public class ReportPrinter {
    public void print(int sum, double avg) {
        System.out.println("=== Report ===");
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + avg);
        System.out.println("Generated at: " + LocalDateTime.now());
    }
}