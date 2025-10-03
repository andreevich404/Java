import java.util.Objects;

/**
 * Класс представляет человека с именем, фамилией и возрастом.
 * Реализует интерфейс Comparable для сравнения объектов по фамилии и имени.
 */
public class Human implements Comparable<Human> {
    private final String firstName;
    private final String lastName;
    private final int age;

    /**
     * Конструктор класса Human.
     * @param firstName имя человека
     * @param lastName фамилия человека
     * @param age возраст человека
     */
    public Human(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Возвращает фамилию человека.
     * @return фамилия
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Возвращает возраст человека.
     * @return возраст
     */
    public int getAge() {
        return age;
    }

    /**
     * Сравнивает текущий объект с другим объектом Human.
     * @param other другой объект Human для сравнения
     * @return результат сравнения
     */
    @Override
    public int compareTo(Human other) {
        int lastNameCompare = this.lastName.compareTo(other.lastName);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }
        return this.firstName.compareTo(other.firstName);
    }

    /**
     * Проверяет равенство текущего объекта с другим объектом.
     * @param obj объект для сравнения
     * @return true если объекты равны
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Human human = (Human) obj;
        return age == human.age &&
                Objects.equals(firstName, human.firstName) &&
                Objects.equals(lastName, human.lastName);
    }

    /**
     * Возвращает хэш-код объекта.
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    /**
     * Возвращает строковое представление объекта Human.
     * @return строковое представление
     */
    @Override
    public String toString() {
        return String.format("%s %s (%d лет)", lastName, firstName, age);
    }
}