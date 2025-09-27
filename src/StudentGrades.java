import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentGrades {
    private Map<String, Double> studentAverages;

    public StudentGrades() {
        studentAverages = new HashMap<>();
    }

    public Map<String, Double> analyze(String filename) {
        studentAverages.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                processStudentLine(line);
            }

        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return new HashMap<>(studentAverages);
    }

    private void processStudentLine(String line) {
        String[] parts = line.split(" ");

        if (parts.length < 2) {
            System.out.println("Некорректная строка: " + line);
            return;
        }

        String studentName = parts[0];
        List<Integer> grades = new ArrayList<>();

        for (int i = 1; i < parts.length; i++) {
            try {
                int grade = Integer.parseInt(parts[i]);
                grades.add(grade);
            }
            catch (NumberFormatException e) {
                System.out.println("Некорректная оценка у студента " + studentName + ": " + parts[i]);
            }
        }

        if (!grades.isEmpty()) {
            double average = calculateAverage(grades);
            studentAverages.put(studentName, average);
        }
    }

    private double calculateAverage(List<Integer> grades) {
        int sum = 0;

        for (int grade : grades) {
            sum += grade;
        }

        return (double) sum / grades.size();
    }

    public String bestStudent() {
        if (studentAverages.isEmpty()) {
            return null;
        }

        String bestStudent = null;
        double maxAverage = -1;

        for (Map.Entry<String, Double> entry : studentAverages.entrySet()) {
            if (entry.getValue() > maxAverage) {
                maxAverage = entry.getValue();
                bestStudent = entry.getKey();
            }
        }

        return bestStudent;
    }

    public String worstStudent() {
        if (studentAverages.isEmpty()) {
            return null;
        }

        String worstStudent = null;
        double minAverage = Double.MAX_VALUE;

        for (Map.Entry<String, Double> entry : studentAverages.entrySet()) {
            if (entry.getValue() < minAverage) {
                minAverage = entry.getValue();
                worstStudent = entry.getKey();
            }
        }

        return worstStudent;
    }

    public void printResults() {
        if (studentAverages.isEmpty()) {
            System.out.println("Нет данных для отображения");
            return;
        }

        for (Map.Entry<String, Double> entry : studentAverages.entrySet()) {
            System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue());
        }

        System.out.println("Лучший студент: " + bestStudent());
        System.out.println("Худший студент: " + worstStudent());
    }
}