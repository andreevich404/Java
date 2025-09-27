public class Main {

    public static void main(String[] args) {
        System.out.println("=== Тест класса Button ===");

        Button button1 = new Button();
        System.out.println("Первая кнопка:");
        button1.click(); // 1
        button1.click(); // 2
        button1.click(); // 3

        System.out.println("\nВторая кнопка:");
        Button button2 = new Button();
        button2.click(); // 1
        button2.click(); // 2

        System.out.println("\nПервая кнопка снова:");
        button1.click(); // 4
    }
}