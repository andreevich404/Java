import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private final List<Visitor> visitors;

    public Menu(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Меню лабораторной работы ===");
            System.out.println("1. Список посетителей и их количество");
            System.out.println("2. Список всех уникальных книг");
            System.out.println("3. Книги, отсортированные по году издания");
            System.out.println("4. Проверка наличия книг Jane Austen");
            System.out.println("5. Максимальное число книг у одного посетителя");
            System.out.println("6. Генерация SMS сообщений");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт меню: ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    taskVisitor();
                    break;
                case 2:
                    uniqueBooks();
                    break;
                case 3:
                    sortBooksByYear();
                    break;
                case 4:
                    hasBookByJaneAusten();
                    break;
                case 5:
                    maxBooksVisitor();
                    break;
                case 6:
                    smsGenerator();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

            if (choice != 0) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void taskVisitor() {
        System.out.println("Задание 1: Список посетителей и их количество");
        System.out.println("Количество посетителей: " + visitors.size());
        System.out.println("Список посетителей:");
        visitors.forEach(v -> System.out.println(" - " + v.getName() + " " + v.getSurname()));
    }

    private void uniqueBooks() {
        System.out.println("Задание 2: Список всех уникальных книг");

        List<Book> uniqueBooks = visitors.stream()
                .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Количество уникальных книг: " + uniqueBooks.size());
        System.out.println("Список уникальных книг:");
        uniqueBooks.forEach(book -> System.out.println(" - \"" + book.getName() + "\" by " + book.getAuthor()));
    }

    private void sortBooksByYear () {
        System.out.println("ЗАДАНИЕ 3: Книги, отсортированные по году издания");

        List<Book> sortedBooks = visitors.stream()
                .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                .distinct()
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .collect(Collectors.toList());

        sortedBooks.forEach(book ->
                System.out.println(" - " + book.getPublishingYear() + ": \"" + book.getName() + "\""));
    }

    private void hasBookByJaneAusten() {
        System.out.println("Задание 4: Проверка наличия книг Jane Austen");

        boolean hasJaneAusten = visitors.stream()
                .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                .anyMatch(book -> "Jane Austen".equals(book.getAuthor()));

        if (hasJaneAusten) {
            System.out.println("В избранных книгах посетителей есть произведения Jane Austen");

            List<Visitor> austenLovers = visitors.stream()
                    .filter(visitor -> visitor.getFavoriteBooks().stream().anyMatch(book -> "Jane Austen".equals(book.getAuthor())))
                    .collect(Collectors.toList());

            System.out.println("Книги Jane Austen есть у:");
            austenLovers.forEach(v -> System.out.println(" - " + v.getName() + " " + v.getSurname()));
        } else {
            System.out.println("В избранных книгах посетителей нет произведений Jane Austen");
        }
    }

    private void maxBooksVisitor() {
        System.out.println("Задание 5: Максимальное число книг у одного посетителя");

        OptionalInt maxBooks = visitors.stream()
                .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                .max();

        if (maxBooks.isPresent()) {
            int max = maxBooks.getAsInt();
            System.out.println("Максимальное количество книг у одного посетителя: " + max);

            List<Visitor> maxBookVisitors = visitors.stream()
                    .filter(visitor -> visitor.getFavoriteBooks().size() == max)
                    .collect(Collectors.toList());

            System.out.println("Посетители с максимальным количеством книг:");
            maxBookVisitors.forEach(v -> System.out.println(" - " + v.getName() + " " + v.getSurname() + " (" + max + " книг)"));
        }
    }

    private void smsGenerator() {
        System.out.println("Задание 6: Генерация SMS сообщений");

        List<Visitor> subscribedVisitors = visitors.stream()
                .filter(Visitor::isSubscribed)
                .collect(Collectors.toList());

        if (subscribedVisitors.isEmpty()) {
            System.out.println("Нет подписанных пользователей для рассылки");
            return;
        }

        double averageBooks = visitors.stream()
                .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                .average()
                .orElse(0.0);

        System.out.printf("Среднее количество книг на посетителя: %.2f%n", averageBooks);

        List<SmsMessage> smsMessages = subscribedVisitors.stream()
                .map(visitor -> {
                    int bookCount = visitor.getFavoriteBooks().size();
                    String message;

                    if (bookCount > averageBooks) {
                        message = "you are a bookworm";
                    }
                    else if (bookCount < averageBooks) {
                        message = "read more";
                    }
                    else {
                        message = "fine";
                    }

                    return new SmsMessage(visitor.getPhone(), message);
                })
                .collect(Collectors.toList());

        System.out.println("Сгенерированные SMS сообщения:");
        smsMessages.forEach(System.out::println);
    }
}