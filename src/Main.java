import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("books.json")) {
            Type visitorListType = new TypeToken<List<Visitor>>(){}.getType();
            List<Visitor> visitors = gson.fromJson(reader, visitorListType);

            Menu menu = new Menu(visitors);
            menu.show();
        }
        catch (NoSuchFileException e) {
            System.out.println("Файл не найден!");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}