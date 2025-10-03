import java.util.*;

/**
 * Класс с утилитами для работы с Map.
 */
public class MapUtils {

    private MapUtils() {
        throw new UnsupportedOperationException("Это утилитный класс и не может быть инстанциирован");
    }
    /**
     * Метод для обмена ключей и значений в Map.
     * @param <K> тип ключей исходной Map
     * @param <V> тип значений исходной Map
     * @param originalMap исходная Map
     * @return новая Map, где ключи и значения поменяны местами
     */
    public static <K, V> Map<V, K> swapKeysAndValues(Map<K, V> originalMap) {
        Map<V, K> swappedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();

            if (swappedMap.containsKey(value)) {
                throw new IllegalArgumentException("Невозможно поменять ключи и значения: " + "значение '" + value + "' встречается несколько раз.");
            }

            swappedMap.put(value, key);
        }

        return swappedMap;
    }

    /**
     * Безопасная версия метода обмена ключей и значений.
     * @param <K> тип ключей исходной Map
     * @param <V> тип значений исходной Map
     * @param originalMap исходная Map
     * @return новая Map, где значения -> список ключей
     */
    public static <K, V> Map<V, List<K>> swapKeysAndValuesSafe(Map<K, V> originalMap) {
        Map<V, List<K>> swappedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();

            swappedMap.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }

        return swappedMap;
    }

    /**
     * Демонстрирует обмен ключей и значений с уникальными значениями.
     */
    public static void demonstrateUniqueValuesSwap() {
        System.out.println("ТЕСТ 1: Map с уникальными значениями");
        Map<String, Integer> testMap1 = new HashMap<>();
        testMap1.put("one", 1);
        testMap1.put("two", 2);
        testMap1.put("three", 3);
        testMap1.put("four", 4);

        printMap(testMap1, "Исходная Map:");

        try {
            Map<Integer, String> swappedMap1 = swapKeysAndValues(testMap1);
            printMap(swappedMap1, "После обмена ключей и значений:");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует попытку обмена при дублирующихся значениях.
     */
    public static void demonstrateDuplicateValuesSwap() {
        System.out.println("\nТЕСТ 2: Map с дублирующимися значениями");
        Map<String, String> testMap2 = new HashMap<>();
        testMap2.put("Москва", "Россия");
        testMap2.put("Париж", "Франция");
        testMap2.put("Берлин", "Германия");
        testMap2.put("Санкт-Петербург", "Россия");

        printMap(testMap2, "Исходная Map:");

        try {
            Map<String, String> swappedMap2 = swapKeysAndValues(testMap2);
            printMap(swappedMap2, "После обмена ключей и значений:");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует безопасный обмен при дублирующихся значениями.
     */
    public static void demonstrateSafeSwapWithDuplicates() {
        System.out.println("\nТЕСТ 3: Безопасный метод с дублирующимися значениями");
        Map<String, String> testMap3 = new HashMap<>();
        testMap3.put("яблоко", "фрукт");
        testMap3.put("апельсин", "фрукт");
        testMap3.put("морковь", "овощ");
        testMap3.put("картофель", "овощ");
        testMap3.put("банан", "фрукт");

        printMap(testMap3, "Исходная Map:");

        Map<String, List<String>> swappedMap3 = swapKeysAndValuesSafe(testMap3);
        System.out.println("После безопасного обмена:");
        for (Map.Entry<String, List<String>> entry : swappedMap3.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    /**
     * Демонстрирует интеграцию с заданием №4.
     */
    public static void demonstrateIntegrationWithWordFrequency() {
        System.out.println("\nИнтеграция с заданием №4:");

        String sampleText = "java is great and java is fun";
        Map<String, Integer> wordFreq = WordFrequencyCounter.countWordFrequency(sampleText);

        printMap(wordFreq, "Частота слов:");

        try {
            Map<Integer, String> swappedWordFreq = swapKeysAndValues(wordFreq);
            printMap(swappedWordFreq, "После обмена (частота -> слово):");
        } catch (IllegalArgumentException e) {
            System.out.println("Невозможно выполнить прямой обмен: " + e.getMessage());

            Map<Integer, List<String>> safeSwapped = swapKeysAndValuesSafe(wordFreq);
            System.out.println("Безопасный обмен (частота -> список слов):");
            for (Map.Entry<Integer, List<String>> entry : safeSwapped.entrySet()) {
                System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    /**
     * Вспомогательный метод для вывода Map.
     * @param map Map для вывода
     * @param title заголовок для вывода
     */
    public static <K, V> void printMap(Map<K, V> map, String title) {
        System.out.println(title);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
    }
}