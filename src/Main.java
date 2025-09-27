import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Тестируем класс StudentGrades

        StudentGrades analyzer = new StudentGrades();

        System.out.println("=== Тест 1: Анализ файла с оценками ===");
        Map<String, Double> results = analyzer.analyze("students.txt");

        if (!results.isEmpty()) {
            System.out.println("Результаты анализа:");
            System.out.println("-------------------");
            analyzer.printResults();
        } else {
            System.out.println("Файл пуст или не содержит данных");
        }

        System.out.println("\n=== Тест 2: Файл не существует ===");
        Map<String, Double> results2 = analyzer.analyze("несуществующий_файл.txt");
        if (results2.isEmpty()) {
            System.out.println("Обработка несуществующего файла работает корректно");
        }

        System.out.println("\n=== Тест 3: Пустой файл ===");
        Map<String, Double> results3 = analyzer.analyze("empty_students.txt");
        if (results3.isEmpty()) {
            System.out.println("Пустой файл обработан - студентов нет");
            analyzer.printResults(); // Проверяем вывод для пустых данных
        }

        System.out.println("\n=== Тест 4: Файл с некорректными данными ===");
        Map<String, Double> results4 = analyzer.analyze("invalid_students.txt");
        if (!results4.isEmpty()) {
            System.out.println("Результаты (некорректные данные пропущены):");
            analyzer.printResults();
        }

        System.out.println("\n=== Тест 5: Множественные вызовы analyze() ===");
        // Первый файл
        Map<String, Double> firstResults = analyzer.analyze("students.txt");
        System.out.println("Первый файл:");
        analyzer.printResults();

        // Второй файл (перезаписывает результаты)
        Map<String, Double> secondResults = analyzer.analyze("students2.txt");
        System.out.println("\nВторой файл (перезаписанные результаты):");
        analyzer.printResults();

        System.out.println("\n=== Тест 6: Прямой вызов bestStudent() и worstStudent() ===");
        analyzer.analyze("students.txt");
        String best = analyzer.bestStudent();
        String worst = analyzer.worstStudent();

        System.out.println("Лучший студент: " + (best != null ? best : "нет данных"));
        System.out.println("Худший студент: " + (worst != null ? worst : "нет данных"));

        System.out.println("\n=== Тест 7: Работа с пустыми данными ===");
        StudentGrades emptyAnalyzer = new StudentGrades();
        String emptyBest = emptyAnalyzer.bestStudent();
        String emptyWorst = emptyAnalyzer.worstStudent();

        System.out.println("Лучший студент (пустой анализатор): " + emptyBest);
        System.out.println("Худший студент (пустой анализатор): " + emptyWorst);
        emptyAnalyzer.printResults();
    }
}