public class Main {

    public static void main(String[] args) {
        FileAnalyzer analyzer = new FileAnalyzer();

        System.out.println("=== Тест 1: Анализ существующего файла ===");
        analyzer.analyze("test.txt");

        System.out.println("\n=== Тест 2: Файл не существует ===");
        analyzer.analyze("несуществующий_файл.txt");

        System.out.println("\n=== Тест 3: Пустой файл ===");
        analyzer.analyze("empty.txt");

        System.out.println("\n=== Тест 4: Файл с одной строкой ===");
        analyzer.analyze("single_line.txt");

        System.out.println("\n=== Тест 5: Специальные символы ===");
        analyzer.analyze("special.txt");
    }
}