import java.nio.file.*;
import java.io.IOException;
import java.util.Comparator;

public class FileSystemManager {
    private static final String FILE_CREATED_MSG = "Создан файл: ";

    public static void main(String[] args) {
        String surname = "Tolkachev";
        String name = "Ivan";

        try {
            // 1. Создаем основную директорию
            Path mainDir = Paths.get(surname);
            if (!Files.exists(mainDir)) {
                Files.createDirectories(mainDir);
                System.out.println("Создана директория: " + mainDir.toAbsolutePath());
            }

            // 2. Создаем файл с именем
            Path nameFile = mainDir.resolve(name + ".txt");
            createFileWithMessage(nameFile, "Это файл " + name);

            // 3. Создаем вложенные директории и копируем файл
            Path nestedDir = mainDir.resolve("dir1/dir2/dir3");
            Files.createDirectories(nestedDir);
            System.out.println("Созданы вложенные директории: dir1/dir2/dir3");

            Path copiedFile = nestedDir.resolve(name + ".txt");
            Files.copy(nameFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован в: " + copiedFile);

            // 4. Создаем file1 в dir1
            Path file1 = mainDir.resolve("dir1/file1.txt");
            createFileWithMessage(file1, "Содержимое file1");

            // 5. Создаем file2 в dir2
            Path file2 = mainDir.resolve("dir1/dir2/file2.txt");
            createFileWithMessage(file2, "Содержимое file2");

            // 6. Рекурсивный обход директории
            performRecursiveWalk(mainDir, surname);

            // 7. Удаляем директорию dir1 со всем содержимым
            deleteDirectory(mainDir);

            // Финальный обход для демонстрации
            System.out.println("\n=== Финальное состояние директории ===");
            performRecursiveWalk(mainDir, surname);

        }
        catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createFileWithMessage(Path filePath, String content) throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            System.out.println(FILE_CREATED_MSG + filePath.getFileName());
        }
        Files.writeString(filePath, content);
    }

    private static void performRecursiveWalk(Path mainDir, String surname) throws IOException {
        System.out.println("\n=== Рекурсивный обход директории " + surname + " ===");
        try (var stream = Files.walk(mainDir)) {
            stream.forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("D: " + mainDir.relativize(path));
                }
                else {
                    System.out.println("F: " + mainDir.relativize(path));
                }
            });
        }
    }

    /**
     * Удаляет директорию dir1 со всем содержимым
     */
    private static void deleteDirectory(Path mainDir) throws IOException {
        System.out.println("\n=== Удаление директории dir1 ===");
        Path dir1 = mainDir.resolve("dir1");
        if (Files.exists(dir1)) {
            try (var stream = Files.walk(dir1)) {
                stream.sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                                System.out.println("Удалено: " + mainDir.relativize(path));
                            } catch (IOException e) {
                                System.err.println("Ошибка при удалении: " + path);
                            }
                        });
            }
            System.out.println("Директория dir1 успешно удалена");
        }
    }
}