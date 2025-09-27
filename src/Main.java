public class Main {

    public static void main(String[] args) {
        System.out.println("=== Тест 1: Создание таблицы и базовые операции ===");
        Table table1 = new Table(3, 4);
        System.out.println("Таблица 3x4 (заполнена нулями):");
        System.out.println(table1.toString());
        System.out.println("Количество строк: " + table1.rows());
        System.out.println("Количество столбцов: " + table1.cols());
        System.out.println("Среднее значение: " + table1.average());

        System.out.println("\n=== Тест 2: Установка и получение значений ===");
        table1.setValue(0, 0, 5);
        table1.setValue(1, 1, 10);
        table1.setValue(2, 2, 15);
        table1.setValue(0, 3, 20);

        System.out.println("После установки значений:");
        System.out.println(table1.toString());

        System.out.println("Значение в [0,0]: " + table1.getValue(0, 0)); // 5
        System.out.println("Значение в [1,1]: " + table1.getValue(1, 1)); // 10
        System.out.println("Значение в [2,3]: " + table1.getValue(2, 3)); // 0
        System.out.println("Среднее значение: " + table1.average());

        System.out.println("\n=== Тест 3: Таблица 2x2 с заполнением ===");
        Table table2 = new Table(2, 2);
        table2.setValue(0, 0, 1);
        table2.setValue(0, 1, 2);
        table2.setValue(1, 0, 3);
        table2.setValue(1, 1, 4);

        System.out.println("Таблица 2x2:");
        System.out.println(table2.toString());
        System.out.println("Количество строк: " + table2.rows());
        System.out.println("Количество столбцов: " + table2.cols());
        System.out.println("Среднее значение: " + table2.average()); // 2.5

        System.out.println("\n=== Тест 4: Таблица 1x1 ===");
        Table table3 = new Table(1, 1);
        table3.setValue(0, 0, 100);
        System.out.println("Таблица 1x1:");
        System.out.println(table3.toString());
        System.out.println("Среднее значение: " + table3.average()); // 100.0

        System.out.println("\n=== Тест 5: Большая таблица с вычислениями ===");
        Table table4 = new Table(2, 3);
        table4.setValue(0, 0, 1);
        table4.setValue(0, 1, 2);
        table4.setValue(0, 2, 3);
        table4.setValue(1, 0, 4);
        table4.setValue(1, 1, 5);
        table4.setValue(1, 2, 6);

        System.out.println("Таблица 2x3:");
        System.out.println(table4.toString());
        System.out.println("Среднее значение: " + table4.average()); // 3.5

        System.out.println("\n=== Тест 6: Проверка границ ===");
        try {
            Table table5 = new Table(0, 5); // Должно вызвать исключение
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании: " + e.getMessage());
        }

        try {
            table1.setValue(5, 5, 100); // Выход за границы
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при установке значения: " + e.getMessage());
        }

        try {
            table1.getValue(10, 2); // Выход за границы
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при получении значения: " + e.getMessage());
        }

        System.out.println("\n=== Тест 7: Обновление значений ===");
        System.out.println("Исходная таблица:");
        System.out.println(table2.toString());
        System.out.println("Значение в [1,1] до обновления: " + table2.getValue(1, 1)); // 4

        table2.setValue(1, 1, 99);
        System.out.println("Значение в [1,1] после обновления: " + table2.getValue(1, 1)); // 99
        System.out.println("Таблица после обновления:");
        System.out.println(table2.toString());
        System.out.println("Новое среднее значение: " + table2.average()); // 26.25
    }
}