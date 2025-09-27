public class Main {

    public static void main(String[] args) {
        System.out.println("=== Тест 1: Базовое разделение ===");
        OddEvenSeparator separator1 = new OddEvenSeparator();

        separator1.addNumber(1);
        separator1.addNumber(2);
        separator1.addNumber(3);
        separator1.addNumber(4);
        separator1.addNumber(5);

        System.out.print("Четные числа: ");
        separator1.even(); // 2 4

        System.out.print("Нечетные числа: ");
        separator1.odd(); // 1 3 5

        System.out.println("\n=== Тест 2: Сохранение порядка ===");
        OddEvenSeparator separator2 = new OddEvenSeparator();

        separator2.addNumber(10);
        separator2.addNumber(3);
        separator2.addNumber(8);
        separator2.addNumber(15);
        separator2.addNumber(7);
        separator2.addNumber(2);

        System.out.print("Четные числа: ");
        separator2.even(); // 10 8 2

        System.out.print("Нечетные числа: ");
        separator2.odd(); // 3 15 7

        System.out.println("\n=== Тест 3: Только четные числа ===");
        OddEvenSeparator separator3 = new OddEvenSeparator();

        separator3.addNumber(2);
        separator3.addNumber(4);
        separator3.addNumber(6);
        separator3.addNumber(8);

        System.out.print("Четные числа: ");
        separator3.even(); // 2 4 6 8

        System.out.print("Нечетные числа: ");
        separator3.odd(); // (пусто)

        System.out.println("\n=== Тест 4: Только нечетные числа ===");
        OddEvenSeparator separator4 = new OddEvenSeparator();

        separator4.addNumber(1);
        separator4.addNumber(3);
        separator4.addNumber(5);
        separator4.addNumber(7);

        System.out.print("Четные числа: ");
        separator4.even(); // (пусто)

        System.out.print("Нечетные числа: ");
        separator4.odd(); // 1 3 5 7

        System.out.println("\n=== Тест 5: Пустой список ===");
        OddEvenSeparator separator5 = new OddEvenSeparator();

        System.out.print("Четные числа: ");
        separator5.even(); // (пусто)

        System.out.print("Нечетные числа: ");
        separator5.odd(); // (пусто)

        System.out.println("\n=== Тест 6: Отрицательные числа ===");
        OddEvenSeparator separator6 = new OddEvenSeparator();

        separator6.addNumber(-5);
        separator6.addNumber(-4);
        separator6.addNumber(-3);
        separator6.addNumber(-2);
        separator6.addNumber(0);
        separator6.addNumber(1);

        System.out.print("Четные числа: ");
        separator6.even(); // -4 -2 0

        System.out.print("Нечетные числа: ");
        separator6.odd(); // -5 -3 1

        System.out.println("\n=== Тест 7: Последовательное добавление ===");
        OddEvenSeparator separator7 = new OddEvenSeparator();

        System.out.println("После добавления 10:");
        separator7.addNumber(10);
        System.out.print("Четные: ");
        separator7.even(); // 10
        System.out.print("Нечетные: ");
        separator7.odd(); // (пусто)

        System.out.println("После добавления 7:");
        separator7.addNumber(7);
        System.out.print("Четные: ");
        separator7.even(); // 10
        System.out.print("Нечетные: ");
        separator7.odd(); // 7

        System.out.println("После добавления 4:");
        separator7.addNumber(4);
        System.out.print("Четные: ");
        separator7.even(); // 10 4
        System.out.print("Нечетные: ");
        separator7.odd(); // 7
    }
}