import java.util.*;

/**
 * Демонстрационный класс для работы с различными коллекциями объектов Human.
 */
public class HumanCollectionsDemo {

    private HumanCollectionsDemo() {
        throw new UnsupportedOperationException("Это утилитный класс и не может быть инстанциирован");
    }
    /**
     * Выполняет демонстрацию работы с коллекциями объектов Human.
     * @param humanList список объектов Human для тестирования
     */
    public static void execute(List<Human> humanList) {
        System.out.println("Исходный список:");
        printCollection(humanList);

        Set<Human> hashSet = new HashSet<>(humanList);
        System.out.println("\nHashSet:");
        printCollection(hashSet);

        Set<Human> linkedHashSet = new LinkedHashSet<>(humanList);
        System.out.println("\nLinkedHashSet:");
        printCollection(linkedHashSet);

        Set<Human> treeSet = new TreeSet<>(humanList);
        System.out.println("\nTreeSet (сортировка по Comparable - фамилия, имя):");
        printCollection(treeSet);

        Set<Human> treeSetByLastName = new TreeSet<>(new HumanComparatorByLastName());
        treeSetByLastName.addAll(humanList);
        System.out.println("\nTreeSet с HumanComparatorByLastName (только по фамилии):");
        printCollection(treeSetByLastName);

        Set<Human> treeSetByAge = new TreeSet<>(Comparator.comparingInt(Human::getAge));
        treeSetByAge.addAll(humanList);
        System.out.println("\nTreeSet с анонимным компаратором по возрасту:");
        printCollection(treeSetByAge);
    }

    /**
     * Выводит коллекцию в форматированном виде.
     * @param collection коллекция для вывода
     */
    private static void printCollection(Collection<Human> collection) {
        int index = 1;
        for (Human human : collection) {
            System.out.println("  " + index++ + ". " + human);
        }
        System.out.println("  Всего элементов: " + collection.size());
    }

    /**
     * Возвращает готовый список людей для демонстрации.
     * @return список объектов Human
     */
    public static List<Human> getDefaultHumans() {
        return Arrays.asList(
                new Human("Иван", "Петров", 25),
                new Human("Мария", "Иванова", 30),
                new Human("Алексей", "Сидоров", 22),
                new Human("Ольга", "Петрова", 28),
                new Human("Дмитрий", "Иванов", 35),
                new Human("Елена", "Смирнова", 26),
                new Human("Иван", "Петров", 25)
        );
    }
}