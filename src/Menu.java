import java.util.*;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            printMenu();
            int choice = getIntInput("Выберите задание (0 для выхода): ");

            if (choice == 0) {
                System.out.println("Выход из программы...");
                break;
            }

            executeTask(choice);
            System.out.println("\n" + "=".repeat(50) + "\n");
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("=== МЕНЮ ВЫБОРА ЗАДАНИЙ ===");
        System.out.println("1. Button - счетчик нажатий");
        System.out.println("2. Balance - весы с двумя чашами");
        System.out.println("3. Bell - колокол с чередованием звуков");
        System.out.println("4. OddEvenSeparator - разделитель четных/нечетных чисел");
        System.out.println("5. Table - двумерная таблица целых чисел");
        System.out.println("6. Geometry - 2D/3D фигуры и исключения");
        System.out.println("7. FileAnalyzer - анализ текстовых файлов");
        System.out.println("8. StudentGrades - анализ успеваемости студентов");
        System.out.println("0. Выход");
    }

    private void executeTask(int choice) {
        switch (choice) {
            case 1 -> task1();
            case 2 -> task2();
            case 3 -> task3();
            case 4 -> task4();
            case 5 -> task5();
            case 6 -> task6();
            case 7 -> task7();
            case 8 -> task8();
            default -> System.out.println("Неверный выбор! Попробуйте снова.");
        }
    }

    private void task1() {
        System.out.println("\n=== ЗАДАНИЕ 1: Button ===");
        Button button = new Button();

        System.out.println("Тестирование кнопки. Вводите числа для нажатий (0 для выхода):");
        while (true) {
            int clicks = getIntInput("Сколько раз нажать кнопку? ");
            if (clicks == 0) break;

            for (int i = 0; i < clicks; i++) {
                button.click();
            }
        }
    }

    private void task2() {
        System.out.println("\n=== ЗАДАНИЕ 2: Balance ===");
        Balance balance = new Balance();

        System.out.println("Управление весами. Команды:");
        System.out.println("L <вес> - добавить на левую чашу");
        System.out.println("R <вес> - добавить на правую чашу");
        System.out.println("S - показать состояние");
        System.out.println("C - сбросить весы");
        System.out.println("Q - выйти");

        while (true) {
            System.out.print("Введите команду: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Q")) break;
            if (input.equals("S")) {
                System.out.println("Состояние: ");
                balance.result();
                continue;
            }
            if (input.equals("C")) {
                balance = new Balance();
                System.out.println("Весы сброшены");
                continue;
            }

            String[] parts = input.split(" ");
            if (parts.length == 2) {
                try {
                    int weight = Integer.parseInt(parts[1]);
                    if (parts[0].equals("L")) {
                        balance.addLeft(weight);
                        System.out.println("Добавлено " + weight + " на левую чашу");
                    } else if (parts[0].equals("R")) {
                        balance.addRight(weight);
                        System.out.println("Добавлено " + weight + " на правую чашу");
                    }
                    System.out.println("Текущее состояние: " );
                    balance.result();
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат веса!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
        }
    }

    private void task3() {
        System.out.println("\n=== ЗАДАНИЕ 3: Bell ===");
        Bell bell = new Bell();

        System.out.println("Нажимайте Enter для звука колокола (q для выхода):");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q")) break;
            bell.sound();
        }
    }

    private void task4() {
        System.out.println("\n=== ЗАДАНИЕ 4: OddEvenSeparator ===");
        OddEvenSeparator separator = new OddEvenSeparator();

        System.out.println("Добавляйте числа в разделитель:");
        System.out.println("Число - добавить число");
        System.out.println("E - показать четные");
        System.out.println("O - показать нечетные");
        System.out.println("A - показать все");
        System.out.println("C - очистить");
        System.out.println("Q - выйти");

        while (true) {
            System.out.print("Введите команду: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Q")) break;
            if (input.equalsIgnoreCase("E")) {
                System.out.print("Четные числа: ");
                separator.even();
                continue;
            }
            if (input.equalsIgnoreCase("O")) {
                System.out.print("Нечетные числа: ");
                separator.odd();
                continue;
            }
            if (input.equalsIgnoreCase("A")) {
                System.out.print("Четные: ");
                separator.even();
                System.out.print("Нечетные: ");
                separator.odd();
                continue;
            }
            if (input.equalsIgnoreCase("C")) {
                separator = new OddEvenSeparator();
                System.out.println("Разделитель очищен");
                continue;
            }

            try {
                int number = Integer.parseInt(input);
                separator.addNumber(number);
                System.out.println("Добавлено число: " + number);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа!");
            }
        }
    }

    private void task5() {
        System.out.println("\n=== ЗАДАНИЕ 5: Table ===");

        int rows = getIntInput("Введите количество строк: ");
        int cols = getIntInput("Введите количество столбцов: ");

        try {
            Table table = new Table(rows, cols);
            System.out.println("Таблица создана: " + rows + "x" + cols);

            tableMenu(table);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка создания таблицы: " + e.getMessage());
        }
    }

    private void tableMenu(Table table) {
        System.out.println("Команды для таблицы:");
        System.out.println("S <строка> <столбец> <значение> - установить значение");
        System.out.println("G <строка> <столбец> - получить значение");
        System.out.println("P - показать таблицу");
        System.out.println("I - информация о таблице");
        System.out.println("Q - выйти");

        while (true) {
            System.out.print("Введите команду: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Q")) break;
            if (input.equals("P")) {
                System.out.println("Таблица:");
                System.out.println(table.toString());
                continue;
            }
            if (input.equals("I")) {
                System.out.println("Строк: " + table.rows());
                System.out.println("Столбцов: " + table.cols());
                System.out.println("Среднее значение: " + table.average());
                continue;
            }

            String[] parts = input.split(" ");
            try {
                if (parts[0].equals("S") && parts.length == 4) {
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    int value = Integer.parseInt(parts[3]);
                    table.setValue(row, col, value);
                    System.out.println("Установлено значение " + value + " в [" + row + "," + col + "]");
                } else if (parts[0].equals("G") && parts.length == 3) {
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    int value = table.getValue(row, col);
                    System.out.println("Значение в [" + row + "," + col + "]: " + value);
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат чисел!");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void task6() {
        System.out.println("\n=== ЗАДАНИЕ 6: Geometry ===");

        // Создаем тестовые сценарии для геометрии
        try {
            System.out.println("1. Круг и цилиндр");
            geometry2d.Circle circle = new geometry2d.Circle(5.0);
            geometry3d.Cylinder cylinder = new geometry3d.Cylinder(circle, 10.0);
            System.out.println("Круг: " + circle);
            System.out.println("Площадь: " + circle.area());
            System.out.println("Цилиндр: " + cylinder);
            System.out.println("Объем: " + cylinder.volume());

            System.out.println("\n2. Прямоугольник и цилиндр");
            geometry2d.Rectangle rect = new geometry2d.Rectangle(3.0, 4.0);
            geometry3d.Cylinder cylinder2 = new geometry3d.Cylinder(rect, 7.0);
            System.out.println("Прямоугольник: " + rect);
            System.out.println("Площадь: " + rect.area());
            System.out.println("Цилиндр: " + cylinder2);
            System.out.println("Объем: " + cylinder2.volume());

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void task7() {
        System.out.println("\n=== ЗАДАНИЕ 7: FileAnalyzer ===");
        FileAnalyzer analyzer = new FileAnalyzer();

        System.out.print("Введите имя файла для анализа: ");
        String filename = scanner.nextLine().trim();

        analyzer.analyze(filename);
    }

    private void task8() {
        System.out.println("\n=== ЗАДАНИЕ 8: StudentGrades ===");
        StudentGrades analyzer = new StudentGrades();

        System.out.print("Введите имя файла с оценками: ");
        String filename = scanner.nextLine().trim();

        Map<String, Double> results = analyzer.analyze(filename);
        if (results != null && !results.isEmpty()) {
            analyzer.printResults();
        } else {
            System.out.println("Файл не содержит данных или не найден");
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат! Введите целое число.");
            }
        }
    }
}