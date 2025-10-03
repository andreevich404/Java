import java.util.*;

/**
 * Класс для демонстрации работы со статическими методами класса Collections.
 * Выполняет различные операции с массивом и списком.
 */
public class CollectionsTask {
    private final int arrayLength;
    private final Integer[] array;
    private List<Integer> list;
    private final Random random;

    /**
     * Конструктор класса CollectionsTask.
     * @param arrayLength длина массива для создания
     */
    public CollectionsTask(int arrayLength) {
        this.arrayLength = arrayLength;
        this.array = new Integer[arrayLength];
        this.list = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Создает массив случайных чисел от 0 до 100.
     */
    public void createArray() {
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101);
        }
        System.out.println("1. Исходный массив: " + Arrays.toString(array));
    }

    /**
     * Создает список на основе массива.
     */
    public void createListFromArray() {
        list = new ArrayList<>(Arrays.asList(array));
        System.out.println("2. Список из массива: " + list);
    }

    /**
     * Сортирует список по возрастанию.
     */
    public void sortAscending() {
        Collections.sort(list);
        System.out.println("3. Отсортированный по возрастанию: " + list);
    }

    /**
     * Сортирует список по убыванию.
     */
    public void sortDescending() {
        list.sort(Collections.reverseOrder());
        System.out.println("4. Отсортированный в обратном порядке: " + list);
    }

    /**
     * Перемешивает элементы списка.
     */
    public void shuffleList() {
        Collections.shuffle(list);
        System.out.println("5. Перемешанный список: " + list);
    }

    /**
     * Выполняет циклический сдвиг списка на 1 элемент.
     */
    public void rotateList() {
        Collections.rotate(list, 1);
        System.out.println("6. После циклического сдвига на 1: " + list);
    }

    /**
     * Оставляет в списке только уникальные элементы.
     */
    public void getUniqueElements() {
        Set<Integer> uniqueSet = new LinkedHashSet<>(list);
        List<Integer> uniqueList = new ArrayList<>(uniqueSet);
        System.out.println("7. Только уникальные элементы: " + uniqueList);
    }

    /**
     * Оставляет в списке только дублирующиеся элементы.
     */
    public void getDuplicateElements() {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicatesSet = new HashSet<>();

        for (Integer num : list) {
            if (!seen.add(num)) {
                duplicatesSet.add(num);
            }
        }

        List<Integer> duplicates = new ArrayList<>(duplicatesSet);
        Collections.sort(duplicates);
        System.out.println("8. Только дублирующиеся элементы: " + duplicates);
    }

    /**
     * Преобразует список обратно в массив.
     */
    public void convertListToArray() {
        Integer[] newArray = list.toArray(new Integer[0]);
        System.out.println("9. Массив из списка: " + Arrays.toString(newArray));
    }

    /**
     * Подсчитывает частоту встречаемости каждого числа.
     */
    public void countFrequency() {
        System.out.println("10. Количество вхождений каждого числа:");
        Map<Integer, Integer> frequencyMap = new TreeMap<>();

        for (Integer num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("   Число " + entry.getKey() + ": " + entry.getValue() + " раз(а)");
        }
    }

    /**
     * Выполняет все операции последовательно.
     */
    public void executeAllOperations() {
        createArray();
        createListFromArray();
        sortAscending();
        sortDescending();
        shuffleList();
        rotateList();
        getUniqueElements();
        getDuplicateElements();
        convertListToArray();
        countFrequency();
    }
}