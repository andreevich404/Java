import java.util.Comparator;

/**
 * Компаратор для сравнения объектов Human только по фамилии.
 */
public class HumanComparatorByLastName implements Comparator<Human> {

    /**
     * Сравнивает два объекта Human только по фамилии.
     * @param h1 первый объект Human для сравнения
     * @param h2 второй объект Human для сравнения
     * @return результат сравнения фамилий
     */
    @Override
    public int compare(Human h1, Human h2) {
        return h1.getLastName().compareTo(h2.getLastName());
    }
}