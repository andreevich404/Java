public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        try {
            menu.show();
        }
        finally {
            menu.close();
        }

        System.out.println("Программа завершена.");
    }
}