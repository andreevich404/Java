public class Main {

    public static void main(String[] args) {
        System.out.println("=== Тест 1: Равновесие ===");
        Balance balance1 = new Balance();
        balance1.addLeft(10);
        balance1.addRight(10);
        balance1.result(); // =

        System.out.println("\n=== Тест 2: Перевес левой чаши ===");
        Balance balance2 = new Balance();
        balance2.addLeft(15);
        balance2.addRight(10);
        balance2.result(); // L

        System.out.println("\n=== Тест 3: Перевес правой чаши ===");
        Balance balance3 = new Balance();
        balance3.addLeft(5);
        balance3.addRight(20);
        balance3.result(); // R

        System.out.println("\n=== Тест 4: Постепенное добавление весов ===");
        Balance balance4 = new Balance();
        balance4.addLeft(10);
        balance4.result(); // L

        balance4.addRight(10);
        balance4.result(); // =

        balance4.addRight(5);
        balance4.result(); // R

        balance4.addLeft(5);
        balance4.result(); // =

        System.out.println("\n=== Тест 5: Проверка на отрицательный вес ===");
        try {
            Balance balance5 = new Balance();
            balance5.addLeft(-5); // Должно вызвать исключение
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
    }
}