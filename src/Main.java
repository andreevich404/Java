public class Main {

    public static void main(String[] args) {
        System.out.println("=== Тест 1: Последовательные вызовы ===");
        Bell bell = new Bell();

        System.out.print("1-й вызов: ");
        bell.sound(); // ding

        System.out.print("2-й вызов: ");
        bell.sound(); // dong

        System.out.print("3-й вызов: ");
        bell.sound(); // ding

        System.out.print("4-й вызов: ");
        bell.sound(); // dong

        System.out.print("5-й вызов: ");
        bell.sound(); // ding

        System.out.println("\n=== Тест 2: Второй колокол (независимость) ===");
        Bell bell2 = new Bell();

        System.out.print("1-й вызов второго колокола: ");
        bell2.sound(); // ding

        System.out.print("6-й вызов первого колокола: ");
        bell.sound(); // dong (продолжение с того же места)

        System.out.print("2-й вызов второго колокола: ");
        bell2.sound(); // dong

        System.out.print("3-й вызов второго колокола: ");
        bell2.sound(); // ding

        System.out.println("\n=== Тест 3: Многократные вызовы ===");
        Bell bell3 = new Bell();

        for (int i = 1; i <= 10; i++) {
            System.out.print(i + "-й вызов: ");
            bell3.sound();
        }
    }
}