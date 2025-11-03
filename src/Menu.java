import java.util.Scanner;

/**
 * Класс для отображения меню выбора заданий лабораторной работы
 */
public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Отображает главное меню и обрабатывает выбор пользователя
     */
    public void show() {
        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    runTask1();
                    break;
                case 2:
                    runTask2();
                    break;
                case 3:
                    runBothTasks();
                    break;
                case 0:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }

            pause();
        }
    }

    /**
     * Отображает главное меню
     */
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       ЛАБОРАТОРНАЯ РАБОТА №6");
        System.out.println("Рефлексия, аннотации и работа с файловой системой");
        System.out.println("=".repeat(50));
        System.out.println("1. Задание 1 - Рефлексия и аннотации");
        System.out.println("2. Задание 2 - Работа с файловой системой");
        System.out.println("3. Оба задания последовательно");
        System.out.println("0. Выход");
        System.out.println("=".repeat(50));
        System.out.print("Выберите задание: ");
    }

    /**
     * Получает и проверяет выбор пользователя
     */
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Выполняет Задание 1 - Рефлексия и аннотации
     */
    private void runTask1() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАДАНИЕ 1: Рефлексия и аннотации");
        System.out.println("=".repeat(50));

        try {
            Invoker.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении задания 1: " + e.getMessage());
        }
    }

    /**
     * Выполняет Задание 2 - Работа с файловой системой
     */
    private void runTask2() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАДАНИЕ 2: Работа с файловой системой");
        System.out.println("=".repeat(50));

        try {
            FileSystemManager.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении задания 2: " + e.getMessage());
        }
    }

    /**
     * Выполняет оба задания последовательно
     */
    private void runBothTasks() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ВЫПОЛНЕНИЕ ОБОИХ ЗАДАНИЙ");
        System.out.println("=".repeat(50));

        runTask1();
        System.out.println("\n" + "-".repeat(50));
        runTask2();
    }

    /**
     * Пауза перед возвратом в меню
     */
    private void pause() {
        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }

    /**
     * Закрывает ресурсы
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}