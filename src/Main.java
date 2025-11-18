import model.Book;
import service.BookService;
import service.MusicService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        MusicService musicService = new MusicService();
        BookService bookService = new BookService(new dao.BookDao());

        musicService.createMusicTable();
        musicService.insertMusicInitialData();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Показать все композиции");
            System.out.println("2. Показать композиции без букв 'm' и 't'");
            System.out.println("3. Добавить любимую композицию");
            System.out.println("4. Импортировать посетителей и книги из JSON");
            System.out.println("5. Показать книги, отсортированные по году");
            System.out.println("6. Показать книги младше 2000 года");
            System.out.println("7. Добавить информацию о себе и любимые книги");
            System.out.println("8. Удалить таблицы посетителей и книг");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1 -> {
                    System.out.println("Все композиции:");
                    musicService.getAllMusic().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Композиции без букв 'm' и 't':");
                    musicService.getMusicWithoutMAndT().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Введите название композиции: ");
                    String name = scanner.nextLine();
                    musicService.addMusic(name);
                    System.out.println("Композиция добавлена.");
                }
                case 4 -> {
                    bookService.importFromJson("./books.json");
                    System.out.println("Данные из JSON импортированы.");
                }
                case 5 -> {
                    System.out.println("Книги, отсортированные по году:");
                    List<Book> sorted = bookService.getBooksSortedByYear();
                    sorted.forEach(System.out::println);
                }
                case 6 -> {
                    System.out.println("Книги младше 2000 года:");
                    List<Book> before2000 = bookService.getBooksBefore2000();
                    before2000.forEach(System.out::println);
                }
                case 7 -> System.out.println("Добавьте информацию о себе в JSON и повторите пункт 4.");
                case 8 -> {
                    bookService.dropTables();
                    System.out.println("Таблицы посетителей и книг удалены.");
                }
                case 0 -> {
                    System.out.println("Выход.");
                    running = false;
                    System.exit(0);
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}