import java.util.*;

/**
 * Класс для анализа текста и подсчета частоты встречаемости слов.
 */
public class WordFrequencyCounter {

    private WordFrequencyCounter() {
        throw new UnsupportedOperationException("Это утилитный класс и не может быть инстанциирован");
    }
    /**
     * Выполняет анализ текста и выводит результаты подсчета частоты слов.
     * @param text текст для анализа
     */
    public static void execute(String text) {
        System.out.println("Исходный текст:");
        System.out.println(text);

        Map<String, Integer> wordFrequency = countWordFrequency(text);

        System.out.println("\nРезультаты подсчета частоты слов:");
        printWordFrequency(wordFrequency);
    }

    /**
     * Подсчитывает частоту встречаемости каждого слова в тексте.
     * @param text текст для анализа
     * @return Map со словами и их частотой встречаемости
     */
    public static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> wordFrequency = new HashMap<>();

        if (text == null || text.trim().isEmpty()) {
            return wordFrequency;
        }

        String cleanedText = text.toLowerCase()
                .replaceAll("[^a-zA-Z\\s]", "")
                .replaceAll("\\s+", " ")
                .trim();

        String[] words = cleanedText.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        return wordFrequency;
    }

    /**
     * Выводит таблицу частоты слов в отсортированном виде.
     * @param wordFrequency Map со словами и их частотой
     */
    private static void printWordFrequency(Map<String, Integer> wordFrequency) {
        if (wordFrequency.isEmpty()) {
            System.out.println("Текст не содержит слов.");
            return;
        }

        List<String> sortedWords = new ArrayList<>(wordFrequency.keySet());
        Collections.sort(sortedWords);

        for (String word : sortedWords) {
            System.out.printf("%-15s -> %d%n", word, wordFrequency.get(word));
        }

        System.out.println("\nВсего различных слов: " + wordFrequency.size());
    }

    /**
     * Возвращает готовый пример текста для демонстрации.
     * @return пример текста на английском языке
     */
    public static String getDefaultText() {
        return "The quick brown fox jumps over the lazy dog. " +
                "The fox is quick and the dog is lazy. " +
                "Quick brown foxes jump over lazy dogs in summer. " +
                "Java is great and Java is fun to learn and practice.";
    }
}