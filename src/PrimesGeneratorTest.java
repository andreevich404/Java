import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Тестовый класс для проверки работы PrimesGenerator.
 * Демонстрирует генерацию простых чисел и их вывод в прямом и обратном порядке.
 */
public class PrimesGeneratorTest {

    private PrimesGeneratorTest() {
        throw new UnsupportedOperationException("Это утилитный класс и не может быть инстанциирован");
    }
    /**
     * Выполняет тестирование генератора простых чисел.
     * @param primesCount количество простых чисел для генерации
     */
    public static void execute(int primesCount) {
        System.out.println("Генерация первых " + primesCount + " простых чисел:");

        PrimesGenerator generator = new PrimesGenerator(primesCount);

        System.out.println("Прямой порядок:");
        int[] primes = new int[primesCount];
        int index = 0;

        while (generator.hasNext()) {
            int prime = generator.next();
            primes[index++] = prime;
            System.out.print(prime + " ");
        }
        System.out.println("\n");

        System.out.println("Обратный порядок:");
        for (int i = primes.length - 1; i >= 0; i--) {
            System.out.print(primes[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Альтернативный способ с массивом:");
        Integer[] primesArray = getPrimesArray(primesCount);

        System.out.println("Прямой порядок: " + Arrays.toString(primesArray));

        List<Integer> primesList = Arrays.asList(primesArray);
        Collections.reverse(primesList);
        System.out.println("Обратный порядок: " + primesList);
    }

    /**
     * Возвращает массив простых чисел.
     * @param count количество простых чисел
     * @return массив простых чисел
     */
    private static Integer[] getPrimesArray(int count) {
        Integer[] primes = new Integer[count];
        PrimesGenerator generator = new PrimesGenerator(count);

        for (int i = 0; i < count; i++) {
            primes[i] = generator.next();
        }

        return primes;
    }
}