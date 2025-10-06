import java.util.*;

/**
 * Класс для управления меню выбора заданий.
 * Обеспечивает взаимодействие с пользователем через консольный ввод.
 */
public class Menu {
    private final Scanner scanner;

    /**
     * Конструктор класса Menu.
     * Инициализирует Scanner для чтения ввода пользователя.
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Отображает главное меню и обрабатывает выбор пользователя.
     * Циклически отображает меню до выбора выхода из программы.
     */
    public void show() {
        System.out.println("Добро пожаловать в программу работы с коллекциями!");

        while (true) {
            printMainMenu();
            int choice = getIntInput("Выберите задание (1-5) или 0 для выхода: ");

            switch (choice) {
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                case 1:
                    executeTask1();
                    break;
                case 2:
                    executeTask2();
                    break;
                case 3:
                    executeTask3();
                    break;
                case 4:
                    executeTask4();
                    break;
                case 5:
                    executeTask5();
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
            waitForEnter();
        }
    }

    /**
     * Выводит главное меню на экран.
     */
    private void printMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("               Главное меню");
        System.out.println("=".repeat(50));
        System.out.println("1. Задание №1 - Методы Collections");
        System.out.println("2. Задание №2 - Генератор простых чисел");
        System.out.println("3. Задание №3 - Коллекции с объектами Human");
        System.out.println("4. Задание №4 - Подсчет частоты слов");
        System.out.println("5. Задание №5 - Обмен ключей и значений в Map");
        System.out.println("0. Выход");
        System.out.println("=".repeat(50));
    }

    /**
     * Выполняет задание №1 - Методы Collections.
     */
    private void executeTask1() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание №1 - Методы Collections");
        System.out.println("=".repeat(50));

        int arrayLength = getIntInput("Введите длину массива: ");

        CollectionsTask task = new CollectionsTask(arrayLength);

        task.executeAllOperations();
    }

    /**
     * Выполняет задание №2 - Генератор простых чисел.
     */
    private void executeTask2() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание №2 - Генератор простых чисел");
        System.out.println("=".repeat(50));

        int primesCount = getIntInput("Введите количество простых чисел: ");
        PrimesGeneratorTest.execute(primesCount);
    }

    /**
     * Выполняет задание №3 - Коллекции с объектами Human.
     */
    private void executeTask3() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание №3 - Коллекции с объектами Human");
        System.out.println("=".repeat(50));

        System.out.println("Выберите вариант:");
        System.out.println("1 - Использовать готовый список людей");
        System.out.println("2 - Ввести данные людей вручную");

        int choice = getIntInput("Ваш выбор: ");

        List<Human> humanList;

        if (choice == 1) {
            humanList = HumanCollectionsDemo.getDefaultHumans();
        } else {
            humanList = createHumansManually();
        }

        HumanCollectionsDemo.execute(humanList);
    }

    /**
     * Создает список людей на основе ручного ввода.
     * @return список объектов Human
     */
    private List<Human> createHumansManually() {
        List<Human> humans = new ArrayList<>();

        System.out.println("Введите данные людей (для завершения введите пустую строку в имени):");

        while (true) {
            System.out.println("\nЧеловек #" + (humans.size() + 1));
            String firstName = getStringInput("Имя: ");

            if (firstName.isEmpty()) {
                break;
            }

            String lastName = getStringInput("Фамилия: ");
            int age = getIntInput("Возраст: ");

            try {
                humans.add(new Human(firstName, lastName, age));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        return humans;
    }

    /**
     * Выполняет задание №4 - Подсчет частоты слов.
     */
    private void executeTask4() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание №4 - Подсчет частоты слов");
        System.out.println("=".repeat(50));

        System.out.println("Выберите вариант:");
        System.out.println("1 - Ввести текст вручную");
        System.out.println("2 - Использовать готовый текст");

        int choice = getIntInput("Ваш выбор: ");

        String text;

        if (choice == 1) {
            System.out.println("Введите текст (для завершения введите пустую строку):");
            text = readMultiLineText();
        } else {
            text = WordFrequencyCounter.getDefaultText();
        }

        WordFrequencyCounter.execute(text);
    }

    /**
     * Выполняет задание №5 - Обмен ключей и значений в Map.
     */
    private void executeTask5() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Задание №5 - Обмен ключей и значений в Map");
        System.out.println("=".repeat(50));

        MapUtils.demonstrateUniqueValuesSwap();
        MapUtils.demonstrateDuplicateValuesSwap();
        MapUtils.demonstrateSafeSwapWithDuplicates();
        MapUtils.demonstrateIntegrationWithWordFrequency();
    }

    /**
     * Читает многострочный текст от пользователя.
     * @return введенный текст
     */
    private String readMultiLineText() {
        StringBuilder text = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).isEmpty()) {
            text.append(line).append(" ");
        }

        return text.toString().trim();
    }

    /**
     * Получает целочисленный ввод от пользователя.
     * @param prompt приглашение для ввода
     * @return введенное число
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число!");
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    /**
     * Получает строковый ввод от пользователя.
     * @param prompt приглашение для ввода
     * @return введенная строка
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
        return scanner.nextLine();
    }

    /**
     * Ожидает нажатия Enter для продолжения.
     */
    private void waitForEnter() {
        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
        scanner.nextLine();
    }
}