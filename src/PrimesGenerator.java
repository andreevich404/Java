import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для генерации последовательности простых чисел.
 * Реализует интерфейс Iterator<Integer> для последовательного перебора чисел.
 */
public class PrimesGenerator implements Iterator<Integer> {
    private final int count;
    private int generated;
    private int current;

    /**
     * Конструктор класса PrimesGenerator.
     * @param count количество простых чисел для генерации
     */
    public PrimesGenerator(int count) {
        this.count = count;
        this.generated = 0;
        this.current = 2;
    }

    /**
     * Проверяет, есть ли еще простые числа для генерации.
     * @return true если есть еще простые числа, false если достигнут лимит
     */
    @Override
    public boolean hasNext() {
        return generated < count;
    }

    /**
     * Возвращает следующее простое число в последовательности.
     * @return следующее простое число
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Достигнут лимит простых чисел");
        }

        while (!isPrime(current)) {
            current++;
        }

        int prime = current;
        current++;
        generated++;
        return prime;
    }

    /**
     * Проверяет, является ли число простым.
     * @param number число для проверки
     * @return true если число простое, false в противном случае
     */
    private boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Удаление элементов не поддерживается.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Удаление не поддерживается");
    }
}