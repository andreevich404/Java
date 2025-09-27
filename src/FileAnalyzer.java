import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class FileAnalyzer {
    public void analyze(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            int lines = 0;
            int words = 0;
            int chars = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                lines++;

                String[] wordsInLine = line.split(" ");
                words += wordsInLine.length;

                chars += line.replace(" ", "").length();
            }

            System.out.println("Строк: " + lines);
            System.out.println("Слов: " + words);
            System.out.println("Символов: " + chars);

        }
        catch (NoSuchFileException e) {
            System.out.println("Файл не найден!");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
